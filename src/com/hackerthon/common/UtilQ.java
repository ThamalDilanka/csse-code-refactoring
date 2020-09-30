package com.hackerthon.common;

import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.TransformerException;
import java.io.File;
import org.xml.sax.SAXException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.NodeList;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Element;
import javax.xml.transform.TransformerConfigurationException;

public class UtilQ extends UtilC {
	
	public static String Q(String id){
		final  Logger log = Logger.getLogger(UtilTRANSFORM.class.getName());
		try {
			NodeList nodeList; Element element = null;
			nodeList = DocumentBuilderFactory.newInstance().newDocumentBuilder()
					.parse(new File(CommonConstants.SRC_EMOPLOYEE_QUERY_XML))
					.getElementsByTagName(CommonConstants.TAG_NAME);
			for (int x = 0; x < nodeList.getLength(); x++) {
				element = (Element) nodeList.item(x);
				if (element.getAttribute(CommonConstants.ATTRIBUTE_ID).equals(id))
					break;
			}
			return element.getTextContent().trim();
		}catch (SAXException e) {
			log.log(Level.SEVERE, e.getMessage());
		} catch (IOException e) {
			log.log(Level.SEVERE, e.getMessage());
		} catch (ParserConfigurationException e) {
			log.log(Level.SEVERE, e.getMessage());
		}
		return id;
		 
		
	}
}
