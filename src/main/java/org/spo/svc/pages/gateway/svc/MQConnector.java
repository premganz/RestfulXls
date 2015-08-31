package org.spo.svc.pages.gateway.svc;

import java.io.StringWriter;
import java.io.Writer;
import java.util.Arrays;
import java.util.List;

import javax.jms.Connection;
import javax.jms.DeliveryMode;
import javax.jms.Destination;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.naming.TimeLimitExceededException;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.spo.svc.pages.gateway.model.QMessage;
import org.spo.svc.pages.thread.exch.ThreadExchanger;
import org.spo.svc.shared.TestResourceException;
import org.springframework.stereotype.Component;
@Component
public class MQConnector {

	private ThreadExchanger exchanger=ThreadExchanger.instance();
	private static final String URL="tcp://localhost:61616";
	private String response;
	public String getResponse( QMessage domainMessage) throws Exception{
		try {
			response="ERROR";
			// Create a ConnectionFactory
			ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(URL);

			// Create a Connection
			Connection connection = connectionFactory.createConnection();
			connection.start();

			// Create a Session
			Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

			// Create the destination (Topic or Queue)
			Destination destination = session.createQueue("MAIN.2");

			// Create a MessageProducer from the Session to the Topic or Queue
			MessageProducer producer = session.createProducer(destination);
			producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
			//Create a temporary queue that this client will listen for responses on then create a consumer
			//that consumes message from this temporary queue...for a real application a client should reuse
			//the same temp queue for each message to the server...one temp queue per client
			//Destination tempDest = session.createQueue("REPLY.FOO");
			Destination tempDest = session.createTemporaryQueue();
			MessageConsumer responseConsumer = session.createConsumer(tempDest);

			// Create a messages
			//   String text = "Hello world! From: " + Thread.currentThread().getName() + " : " + this.hashCode();
			String domainRequest= "";
			Writer writer = new StringWriter();
			try {


				JAXBContext jaxbContext = JAXBContext.newInstance(QMessage.class);
				Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

				// output pretty printed
				jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

				jaxbMarshaller.marshal(domainMessage, writer);


			} catch (JAXBException e) {
				e.printStackTrace();
			}
			domainRequest=writer.toString();
			TextMessage message = session.createTextMessage(domainRequest);
			message.setJMSReplyTo(tempDest);
			// Tell the producer to send the message
			System.out.println("Sent message: "+ message.hashCode() + " : " + Thread.currentThread().getName());
			message.setJMSCorrelationID(domainMessage.getFileName());
			producer.send(message, DeliveryMode.NON_PERSISTENT, 4, 10000);

			Message messageReply = responseConsumer.receive(50000);


			if (messageReply instanceof TextMessage) {
				TextMessage txtMsg = (TextMessage) messageReply;
				String messageText = txtMsg.getText();
				System.out.println("Received: " + messageText);
				response=messageText;
			}else{
				throw new TimeLimitExceededException();
			}

			responseConsumer.close();

			// Clean up
			session.close();
			connection.close();
		}
		catch (Exception e) {
			System.out.println("Caught: " + e);
			e.printStackTrace();
		}
		return response;
	}
	public List<String> getResponseAsList( QMessage domainMessage) throws Exception{
		String response = getResponse(domainMessage);
		//String[] array = response.split("\\*\\*\\*EOL\\*\\*\\*");
		//String[] array = response.split("[\r\n]");
		String[] array = response.split("</br>");
		return Arrays.asList(array);
	}

	public String pollResponse() {

		for(int i=0;i<5;i++){			
			response=exchanger.getResponse();
			if(!response.isEmpty()){
				break;
			}
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		return response;
	}

	public String getResponse() {
		return response;
	}

	public void setResponse(String response) {
		this.response = response;
	}

}
