package com.hackerthon.main;

import com.hackerthon.common.UtilTRANSFORM;
import com.hackerthon.service.EmployeeServiceImpl;

public class ExecuteMain {

	public static void main(String[] args) {

		EmployeeServiceImpl employeeService = new EmployeeServiceImpl();
		try {
			UtilTRANSFORM.rEQUESTtRANSFORM();
			employeeService.employeesFromXML();
			employeeService.createEmployeesTable();
			employeeService.addEmployee();
			employeeService.displayEmployee();
		} catch (Exception e) {
		}
	}
}
