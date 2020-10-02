package com.hackerthon.main;

import com.hackerthon.common.TransformUtil;
import com.hackerthon.service.EmployeeService;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.transform.TransformerFactoryConfigurationError;

/**
 * The class contains main method
 * 
 * @author Thamal Dilanka
 * @version 1.1
 */

public class ExecuteMain {

	public static void main(String[] args) {
/**
 * entry point
 * with instance to EmployeeServiceImpl
 */
		final Logger log = Logger.getLogger(TransformUtil.class.getName());
		
		EmployeeService employeeService = new EmployeeService();
		
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
