package com.hackerthon.main;

import com.hackerthon.common.UtilTRANSFORM;
import com.hackerthon.service.getEmpService;

import java.util.logging.Logger;

public class ExecuteMain {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		/******************** Mm danna log line add karanne mehema ***********************/
		Logger LOGGER = Logger.getLogger(ExecuteMain.class.getName());

		LOGGER.info("Sample info msg");
		LOGGER.warning("Sample warning msg");
		/********************************************/

		getEmpService employeeService = new getEmpService();
		try {
			UtilTRANSFORM.rEQUESTtRANSFORM();
			employeeService.employeeFromXML();
			employeeService.createEmployeeTable();
			employeeService.addEmployee();
//			employeeService.eMPLOYEEGETBYID("EMP10004");
//			employeeService.EMPLOYEEDELETE("EMP10001");
			employeeService.displayEmployee();
		} catch (Exception e) {
		}

	}

}
