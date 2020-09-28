package com.hackerthon.main;

import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactoryConfigurationError;

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
			employeeService.eMPLOYEEtABLEcREATE();
			employeeService.eMPLOYEESaDD();
//			employeeService.eMPLOYEEGETBYID("EMP10004");
//			employeeService.EMPLOYEEDELETE("EMP10001");
			employeeService.eMPLOYEEdISPLAY();
		} catch (Exception e) {
		}

	}

}
