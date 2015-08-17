package org.trs.itf.handler



import javax.jms.Connection
import javax.jms.DeliveryMode
import javax.jms.Destination
import javax.jms.JMSException
import javax.jms.Message
import javax.jms.MessageConsumer
import javax.jms.MessageListener
import javax.jms.MessageProducer
import javax.jms.Session
import javax.jms.TextMessage
import javax.xml.bind.JAXBContext
import javax.xml.bind.JAXBException
import javax.xml.bind.Unmarshaller

import org.apache.activemq.ActiveMQConnectionFactory
import org.trs.itf.model.DomainMessage





class GroovyListener extends Thread implements MessageListener{
	MessageProducer replyProducer
	String outText=""
	public void run() {
		// Create a ConnectionFactory
		ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://localhost:61616");

		// Create a Connection
		Connection connection = connectionFactory.createConnection();
		connection.start();

		//connection.setExceptionListener(this);

		// Create a Session
		Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

		// Create the destination (Topic or Queue)
		Destination destination = session.createQueue("FOO");

		// Create a MessageConsumer from the Session to the Topic or Queue
		MessageConsumer consumer = session.createConsumer(destination);
		Message message = consumer.receive(50000);
		while(true){
			TextMessage response =null
			try {
			
				//println 'idle'
				
				// Wait for a message
				message = consumer.receive(50000);
				
				response = session.createTextMessage();
				if (message instanceof TextMessage) {
					TextMessage txtMsg = (TextMessage) message;
					String inMessageText = txtMsg.getText();
					println("Received: " + inMessageText);
					//sleep(500)					
					outText=processMessage(inMessageText)
				
					response.setText(outText);
					response.setJMSCorrelationID(message.getJMSCorrelationID());
	
					//Send the response to the Destination specified by the JMSReplyTo field ofERROR the received message,
					//this is presumably a temporary queue created by the client
					this.replyProducer = session.createProducer(null);
					this.replyProducer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
					this.replyProducer.send(message.getJMSReplyTo(), response);
					println("outext "+outText)
				
				}
				
				

			}catch (Exception e) {
				System.out.println("Caught: " + e);
				e.printStackTrace();
				response.setText("ERROR");
				//response.setText("RESULT is XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
				//Set the correlation ID from the received message to be the correlation id of the response message
				//this lets the client identify which message this is a response to if it has more than
				//one outstanding message to the server
				response.setJMSCorrelationID(message.getJMSCorrelationID());

				//Send the response to the Destination specified by the JMSReplyTo field ofERROR the received message,
				//this is presumably a temporary queue created by the client
				this.replyProducer = session.createProducer(null);
				this.replyProducer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
				this.replyProducer.send(message.getJMSReplyTo(), response);
				continue;
			}

			sleep(500);
		}


	}
	
	public String processMessage(String inMessageText){
			String topic =""
					DomainMessage domainMessage
					try {
						JAXBContext jaxbContext = JAXBContext.newInstance(DomainMessage.class);

						Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
						StringReader reader = new StringReader(inMessageText)
						domainMessage= (DomainMessage) jaxbUnmarshaller.unmarshal(reader);
						topic = domainMessage.getHandler()


					} catch (JAXBException e) {
						e.printStackTrace();
					}
					
					return TopicDispatcher.handle(topic, domainMessage.getFileName(), domainMessage.getMeta())
				 
	}
	public void onMessage(Message message) {}
	public synchronized void onException(JMSException ex) {
		System.out.println("JMS Exception occured.  Shutting down client.");
	}
}



