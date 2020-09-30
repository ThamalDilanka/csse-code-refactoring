package com.hackerthon.common;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class QueryUtil extends CommonUtil {
	
	public static String query(String id){
		final Logger log = Logger.getLogger(TransformUtil.class.getName());
		try {
			NodeList nodeList; Element element = null;
			nodeList = DocumentBuilderFactory.newInstance().newDocumentBuilder()
					.parse(new File(CommonConstants.SRC_EMOPLOYEE_QUERY_XML))
					.getElementsByTagName(CommonConstants.TAG_NAME);
			
			for (int x = 0; x < nodeList.getLength(); x++) {
				element = (Element) nodeList.item(x);
				if(element.getAttribute(CommonConstants.ATTRIBUTE_ID).equals(id))
					break;
			}
			return element.getTextContent().trim();
		}
		catch (SAXException e) {
			log.log(Level.SEVERE, e.getMessage());
		} 
		catch (IOException e) {
			log.log(Level.SEVERE, e.getMessage());
		} 
		catch (ParserConfigurationException e) {
			log.log(Level.SEVERE, e.getMessage());
		}
		return id;
	}
}