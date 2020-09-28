package com.hackerthon.service;

import java.util.ArrayList;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Map;
import java.sql.PreparedStatement;
import com.hackerthon.common.UtilTRANSFORM;
import com.hackerthon.common.UtilQ;
import com.hackerthon.model.Employee;

import com.hackerthon.common.UtilC;

public class getEmpService extends UtilC {

	private final ArrayList<Employee> el = new ArrayList<Employee>();

	private static Connection c;

	private static Statement s;

	private PreparedStatement ps;

	public getEmpService() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			c = DriverManager.getConnection(p.getProperty("url"), p.getProperty("username"),
					p.getProperty("password"));
		} catch (Exception e) {
		} 
	}

	public void employeeFromXML() {

		try {
			int s = UtilTRANSFORM.XMLXPATHS().size();
			for (int i = 0; i < s; i++) {
				Map<String, String> l = UtilTRANSFORM.XMLXPATHS().get(i);
				Employee EMPLOYEE = new Employee();
				EMPLOYEE.setEmployeeId(l.get("XpathEmployeeIDKey"));
				EMPLOYEE.setFullName(l.get("XpathEmployeeNameKey"));
				EMPLOYEE.setAddress(l.get("XpathEmployeeAddressKey"));
				EMPLOYEE.setFacultyName(l.get("XpathFacultyNameKey"));
				EMPLOYEE.setDepartment(l.get("XpathDepartmentKey"));
				EMPLOYEE.setDesignation(l.get("XpathDesignationKey"));
				el.add(EMPLOYEE);
				System.out.println(EMPLOYEE.toString() + "\n");
			}
		} catch (Exception e) {
		}
	}

	public void createEmployeeTable() {
		try {
			s = c.createStatement();
			s.executeUpdate(UtilQ.Q("q2"));
			s.executeUpdate(UtilQ.Q("q1"));
		} catch (Exception e) {
		}
	}

	public void addEmployee() {
		try {
			ps = c.prepareStatement(UtilQ.Q("q3"));
			c.setAutoCommit(false);
			for(int i = 0; i < el.size(); i++){
				Employee employee = el.get(i);
				ps.setString(1, employee.getEmployeeId());
				ps.setString(2, employee.getFullName());
				ps.setString(3, employee.getAddress());
				ps.setString(4, employee.getFacultyName());
				ps.setString(5, employee.getDepartment());
				ps.setString(6, employee.getDesignation());
				ps.addBatch();
			}
			ps.executeBatch();
			c.commit();
		} catch (Exception e) {
		}
	}

	public void getEmployeeById(String employeeId) {

		Employee employee = new Employee();
		try {
			ps = c.prepareStatement(UtilQ.Q("q4"));
			ps.setString(1, employeeId);
			ResultSet R = ps.executeQuery();
			while (R.next()) {
				employee.setEmployeeId(R.getString(1));
				employee.setFullName(R.getString(2));
				employee.setAddress(R.getString(3));
				employee.setFacultyName(R.getString(4));
				employee.setDepartment(R.getString(5));
				employee.setDesignation(R.getString(6));
			}
			ArrayList<Employee> l = new ArrayList<Employee>();
			l.add(employee);
			printEmployee(l);
		} catch (Exception ex) {
		}
	}

	public void deleteEmployee(String employeeId) {

		try {
			ps = c.prepareStatement(UtilQ.Q("q6"));
			ps.setString(1, employeeId);
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void displayEmployee() {

		ArrayList<Employee> l = new ArrayList<Employee>();
		try {
			ps = c.prepareStatement(UtilQ.Q("q5"));
			ResultSet r = ps.executeQuery();
			while (r.next()) {
				Employee employee = new Employee();
				employee.setEmployeeId(r.getString(1));
				employee.setFullName(r.getString(2));
				employee.setAddress(r.getString(3));
				employee.setFacultyName(r.getString(4));
				employee.setDepartment(r.getString(5));
				employee.setDesignation(r.getString(6));
				l.add(employee);
			}
		} catch (Exception e) {
		}
		printEmployee(l);
	}
	
	public void printEmployee(ArrayList<Employee> l){

		System.out.printf("Employee ID\t\tFull Name\t\tAddress\t\tFaculty Name\t\tDepartment\t\tDesignation\n%n");

		System.out
				.println("================================================================================================================");
		for(int i = 0; i < l.size(); i++){
			Employee employee = l.get(i);
			System.out.printf("%s\t%s\t\t%s\t%s\t%s\t%s\n%n",
					employee.getEmployeeId(), employee.getFullName(), employee.getAddress(), employee.getFacultyName(), employee.getDepartment(), employee.getDesignation());
			System.out.println("----------------------------------------------------------------------------------------------------------------");
		}
		
	}
}
