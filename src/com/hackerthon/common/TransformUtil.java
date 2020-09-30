package com.hackerthon.common;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.Map;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Result;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import javax.xml.transform.Source;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.TransformerFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathException;
import javax.xml.xpath.XPathFactory;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

public class TransformUtil extends CommonUtil {

	private static final ArrayList<Map<String, String>> employeeList = new ArrayList<Map<String, String>>();

	private static Map<String, String> employeeMap = null;
	
	public static void requestTransform()   {
		final Logger log = Logger.getLogger(TransformUtil.class.getName());
		try {
			Source requestSource = new StreamSource(new File(CommonConstants.SRC_EMPLOYEE_XML_REQUEST));
			Source modifiedSource = new StreamSource(new File(CommonConstants.SRC_EMPLOYEE_XSL_MODIFIED));
			Result responseSource = new StreamResult(new File(CommonConstants.SRC_EMPLOYEE_XML_RESPONSE));
			TransformerFactory.newInstance().newTransformer(modifiedSource).transform(requestSource, responseSource);
		} catch (NullPointerException e) {
			log.log(Level.SEVERE, e.getMessage());
		} catch (TransformerConfigurationException e) {
			
			log.log(Level.SEVERE, e.getMessage());
		} catch (TransformerException e) {
			
			log.log(Level.SEVERE, e.getMessage());
		} catch (TransformerFactoryConfigurationError e) {
			
			log.log(Level.SEVERE, e.getMessage());
		}		
	}

	public static ArrayList<Map<String, String>> xmlxPaths()   {
		final  Logger log = Logger.getLogger(DBConnectionUtil.class.getName());
		try {
			Document document = DocumentBuilderFactory
									.newInstance().newDocumentBuilder()
									.parse(CommonConstants.SRC_EMPLOYEE_XML_RESPONSE);
			XPath xPathInstance = XPathFactory
									.newInstance()
									.newXPath();
			int noOfEmployees = Integer.parseInt((String) xPathInstance
															.compile(CommonConstants.COMPLIE_COUNT_EMPLOYEE)
															.evaluate(document, XPathConstants.STRING));
			for (int i = 1; i <= noOfEmployees; i++) {
				employeeMap = new HashMap<String, String>();
				
				employeeMap.put(CommonConstants.XPATH_EMPLOYEE_ID_KEY, (String) xPathInstance.compile(CommonConstants.COMPLIE_EMPLOYEES_EMPLOYEES + i + CommonConstants.COMPILE_EMOPLYEES_EMOPLYEE_ID)
						.evaluate(document, XPathConstants.STRING));
				employeeMap.put(CommonConstants.XPATH_EMPLOYEE_NAME_KEY, (String) xPathInstance.compile(CommonConstants.COMPLIE_EMPLOYEES_EMPLOYEES + i + CommonConstants.COMPILE_EMOPLYEES_EMOPLYEE_NAME)
						.evaluate(document, XPathConstants.STRING));
				employeeMap.put(CommonConstants.XPATH_EMPLOYEE_ADDRESS_KEY, (String) xPathInstance.compile(CommonConstants.COMPLIE_EMPLOYEES_EMPLOYEES + i + CommonConstants.COMPILE_EMOPLYEES_EMOPLYEE_ADDRESS)
						.evaluate(document, XPathConstants.STRING));
				employeeMap.put(CommonConstants.XPATH_EMPLOYEE_FACULTY_NAME_KEY, (String) xPathInstance.compile(CommonConstants.COMPLIE_EMPLOYEES_EMPLOYEES + i + CommonConstants.COMPILE_EMOPLYEES_EMOPLYEE_FACULTY_NAME)
						.evaluate(document, XPathConstants.STRING));
				employeeMap.put(CommonConstants.XPATH_EMPLOYEE_DEPARTMENT_KEY, (String) xPathInstance.compile(CommonConstants.COMPLIE_EMPLOYEES_EMPLOYEES + i + CommonConstants.COMPILE_EMOPLYEES_EMOPLYEE_DEPARTMENT)
						.evaluate(document, XPathConstants.STRING));
				employeeMap.put(CommonConstants.XPATH_EMPLOYEE_DESIGNATION_KEY, (String) xPathInstance.compile(CommonConstants.COMPLIE_EMPLOYEES_EMPLOYEES + i + CommonConstants.COMPILE_EMOPLYEES_EMOPLYEE_DESIGNATION)
						.evaluate(document, XPathConstants.STRING));
				
				employeeList.add(employeeMap);
			}
			return employeeList;
			
		} catch (XPathException e) {
			 
			log.log(Level.SEVERE, e.getMessage());
		} catch (SAXException e) {
			 
			log.log(Level.SEVERE, e.getMessage());
		} catch (IOException e) {
			 
			log.log(Level.SEVERE, e.getMessage());
		} catch (ParserConfigurationException e) {
			 
			log.log(Level.SEVERE, e.getMessage());
		}
		return null;
	}
}
