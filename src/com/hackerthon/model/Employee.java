package com.hackerthon.model;

import com.hackerthon.common.CommonConstants;
/**
 * class stores details about the employees
 * 
 * @author Thamal Dilanka
 * @version 1.1
 */
public class Employee {

	public String employeeId;
	public String fullName;
	public String address;
	public String facultyName;
	public String department;
	public String designation;

	public String getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getFacultyName() {
		return facultyName;
	}

	public void setFacultyName(String facultyName) {
		this.facultyName = facultyName;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	/**
	 * This returns a string represenataion all inctance types
	 * 
	 * @return String
	 */
	@Override
	public String toString() {
		return String.format(CommonConstants.EMPLOYEE_TO_STRING_FORMAT,
				employeeId, fullName, address, facultyName, department, designation);
	}
}
