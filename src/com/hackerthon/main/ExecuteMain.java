package com.hackerthon.main;

import com.hackerthon.common.UtilTRANSFORM;
import com.hackerthon.service.EmployeeServiceImpl;

import java.util.logging.Logger;

public class ExecuteMain {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		/******************** Mm danna log line add karanne mehema ***********************/
		Logger LOGGER = Logger.getLogger(ExecuteMain.class.getName());

//		LOGGER.info("Sample info msg");
//		LOGGER.warning("Sample warning msg");
		/********************************************/

		EmployeeServiceImpl employeeService = new EmployeeServiceImpl();
		try {
			UtilTRANSFORM.requestTransform();
			employeeService.employeesFromXML();
			employeeService.createEmployeesTable();
			employeeService.addEmployee();
//			employeeService.displayEmployee();
		} catch (Exception e) {
		}

	}

}
