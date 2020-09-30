package com.hackerthon.main;

import com.hackerthon.common.TransformUtil;
import com.hackerthon.service.EmployeeServiceImpl;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.transform.TransformerFactoryConfigurationError;

public class ExecuteMain {

	public static void main(String[] args) {

		final Logger log = Logger.getLogger(TransformUtil.class.getName());
		
		EmployeeServiceImpl employeeService = new EmployeeServiceImpl();
		
		try {
			TransformUtil.requestTransform();
			employeeService.employeeTableTransactions();
		} 
		catch (NullPointerException e) {
			log.log(Level.SEVERE, e.getMessage());
		} 
		catch (TransformerFactoryConfigurationError e) {
			
			log.log(Level.SEVERE, e.getMessage());
		}
		catch (Exception e) {
			log.log(Level.SEVERE, e.getMessage());
		}
	}
}
