package org.spo.tst.xlaccess.test;

import java.io.File;
import java.net.URL;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.spo.svc.xlaccess.service.XlsReader;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class TestXlsReader {

	File f = null;
	@Before
	public void setUp() throws Exception{
		URL resourceUrl = getClass().getResource("/DraftOne.xml");
		String resourcePath = resourceUrl.toURI().getPath();
		f= new File(resourcePath);
	}
	

	@After
	public void tearDown(){
		
		
	}
	
	
	//@Test //TODO impl of original method suspended
	public void testReadXlsAsXml() {
		try {
		URL resourceUrl = getClass().getResource("/DraftOne.xml");
		String resourcePath = resourceUrl.toURI().getPath();
		f= new File(resourcePath);
		XlsReader reader = new XlsReader(f, "DraftOne.xml");
		
			//reader.readXlsAsXml();
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail();
		}
	}
	@Test
	public void testGetHeaderRows() throws Exception {
		XlsReader reader = new XlsReader(f, "DraftOne.xml");
		NodeList list = reader.getHeaderRows();
		for(int i=0;i<list.getLength();i++){
			Node n = list.item(i);
			System.out.println(n.getTextContent());
		}
			Assert.assertTrue(list.getLength()==20);
		
	}
	
	@Test
	public void getRecordRows() throws Exception {
		XlsReader reader = new XlsReader(f, "DraftOne.xml");
		NodeList list = reader.getRecordRows();
		for(int i=0;i<list.getLength();i++){
			Node n = list.item(i);
			System.out.println(n.getTextContent());
		}
			Assert.assertTrue(list.getLength()==6);
		
	}
	
	@Test
	public void testGetSimpleXmlFromXls() {
		XlsReader reader = new XlsReader(f, "DraftOne.xml");
		Document doc=null;
		try {
			doc = reader.getSimpleXmlFromXls();	
		
			NodeList list = (NodeList) doc.getChildNodes();
			for(int i=0;i<list.getLength();i++){
				Node n = list.item(i);
				System.out.println(n.getTextContent());
			}
			} catch (Exception e) {
				e.printStackTrace();
			}
		Assert.assertTrue(doc!=null);
	

	}
	
	@Test
	public void testQueryAbstractElementDoc() {
		XlsReader reader = new XlsReader(f);
		String result="";
		try {
			result = reader.queryAbstractElementDoc("data","vendor=michelin","price" );
		} catch (Exception e) {
			e.printStackTrace();
		}	
		Assert.assertTrue(result.equals("6000"));


	}
	

	@Test
	public void testQueryAbstractElementDocComplex() {
		XlsReader reader = new XlsReader(f);
		String result="";
		try {
			result = reader.queryAbstractElementDoc("DraftOne","vendor=michelin","price" );
		} catch (Exception e) {
			e.printStackTrace();
		}	
		Assert.assertTrue(result.equals("6000,6000"));


	}

}
