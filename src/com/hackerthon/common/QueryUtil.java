package com.hackerthon.common;

/**
 * This class contains all the common constants for the project
 * 
 * @author Thamal Dilanka
 * @version 1.1
 */

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

	/**
	 * this method string type id and capeares it with the list of id values retrieved from the EmployeeQuery.xml
	 * @param id
	 * @return mathed id s
	 */
	
	public static String query(String id){

		/**
		 * get logger to return the name of the entity
		 * final - no need to change the logger over the lifetime of the class
		 */
		final Logger log = Logger.getLogger(TransformUtil.class.getName());
		try {
			/**
			 * this method read elements in employeeQuery.xml compares it with parameted passed to this class
			 */
			NodeList nodeList;
			Element element = null;
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
