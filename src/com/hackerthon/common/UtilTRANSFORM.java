package com.hackerthon.common;

import javax.xml.transform.TransformerException;
import javax.xml.xpath.XPathFactory;
import java.util.HashMap;
import java.util.Map;
import java.io.File;
import java.io.IOException;
import java.text.MessageFormat;
import java.util.ArrayList;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.parsers.DocumentBuilderFactory;
import org.xml.sax.SAXException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import org.w3c.dom.Document;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import javax.xml.transform.TransformerFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;

public class UtilTRANSFORM extends UtilC {

	private static final ArrayList<Map<String, String>> l = new ArrayList<Map<String, String>>();

	private static Map<String, String> m = null;

	public static void requestTransform() throws Exception {

		Source requestSource = new StreamSource(new File(CommonConstants.SRC_EMPLOYEE_XML_REQUEST));
		Source modifiedSource = new StreamSource(new File(CommonConstants.SRC_EMPLOYEE_XSL_MODIFIED));
		Result responseSource = new StreamResult(new File(CommonConstants.SRC_EMPLOYEE_XML_RESPONSE));
		TransformerFactory.newInstance().newTransformer(modifiedSource).transform(requestSource, responseSource);
	}

	public static ArrayList<Map<String, String>> xmlxPaths() throws Exception {

		Document document = DocumentBuilderFactory.newInstance().newDocumentBuilder()
				.parse(CommonConstants.SRC_EMPLOYEE_XML_RESPONSE);
		XPath x = XPathFactory.newInstance().newXPath();
		int n = Integer.parseInt((String) x.compile("count(//Employees/Employee)").evaluate(document, XPathConstants.STRING));
		for (int i = 1; i <= n; i++) {
			m = new HashMap<String, String>();
			m.put(CommonConstants.XPATH_EMPLOYEE_KEY_ID, (String) x.compile("//Employees/Employee[" + i + "]/EmployeeID/text()")
					.evaluate(document, XPathConstants.STRING));
			m.put(CommonConstants.XPATH_EMPLOYEE_NAME_KEY, (String) x.compile("//Employees/Employee[" + i + "]/EmployeeFullName/text()")
					.evaluate(document, XPathConstants.STRING));
			m.put(CommonConstants.XPATH_EMPLOYEE_ADDRESS_KEY,
					(String) x.compile("//Employees/Employee[" + i + "]/EmployeeFullAddress/text()").evaluate(document,
							XPathConstants.STRING));
			m.put(CommonConstants.XPATH_EMPLOYEE_FACULTY_NAME, (String) x.compile("//Employees/Employee[" + i + "]/FacultyName/text()")
					.evaluate(document, XPathConstants.STRING));
			m.put(CommonConstants.XPATH_EMPLOYEE_DEPARTMENT_KEY, (String) x.compile("//Employees/Employee[" + i + "]/Department/text()")
					.evaluate(document, XPathConstants.STRING));
			m.put(CommonConstants.XPATH_EMPLOYEE_DESIGNATION_KEY, (String) x.compile("//Employees/Employee[" + i + "]/Designation/text()")
					.evaluate(document, XPathConstants.STRING));
			l.add(m);
		}
		return l;
	}
}
