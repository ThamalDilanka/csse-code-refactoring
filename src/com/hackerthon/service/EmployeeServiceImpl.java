package com.hackerthon.service;

import com.hackerthon.common.CommonConstants;
import com.hackerthon.common.CommonUtil;
import com.hackerthon.common.DBConnectionUtil;
import com.hackerthon.common.QueryUtil;
import com.hackerthon.common.TransformUtil;
import com.hackerthon.model.Employee;
import java.sql.BatchUpdateException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLTimeoutException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.Map;

public class EmployeeServiceImpl extends CommonUtil {

	private final ArrayList<Employee> employeeList = new ArrayList<Employee>();
	private final Logger log = Logger.getLogger(DBConnectionUtil.class.getName());

	public void employeesFromXML() {

		try {
			int size = TransformUtil.xmlxPaths().size();
			
			for (int i = 0; i < size; i++) {
				
				Map<String, String> employeesMap = TransformUtil.xmlxPaths().get(i);
				
				Employee employee = new Employee();
				
				employee.setEmployeeId(employeesMap.get(CommonConstants.XPATH_EMPLOYEE_ID_KEY));
				employee.setFullName(employeesMap.get(CommonConstants.XPATH_EMPLOYEE_NAME_KEY));
				employee.setAddress(employeesMap.get(CommonConstants.XPATH_EMPLOYEE_ADDRESS_KEY));
				employee.setFacultyName(employeesMap.get(CommonConstants.XPATH_EMPLOYEE_FACULTY_NAME_KEY));
				employee.setDepartment(employeesMap.get(CommonConstants.XPATH_EMPLOYEE_DEPARTMENT_KEY));
				employee.setDesignation(employeesMap.get(CommonConstants.XPATH_EMPLOYEE_DESIGNATION_KEY));
				
				employeeList.add(employee);
				
				System.out.println(employee.toString() + "\n");
			}
		} 
		catch (IndexOutOfBoundsException e) {
			log.log(Level.SEVERE, e.getMessage());
		}
		catch (ClassCastException e) {
			log.log(Level.SEVERE, e.getMessage());
		}
		catch (NullPointerException e) {
			log.log(Level.SEVERE, e.getMessage());
		}
	}

	public void createEmployeesTable() {
		
		Connection connection = null;
		Statement statement = null;
		
		try {
			connection = DBConnectionUtil.getDBConnection();	
			statement = connection.createStatement();
			statement.executeUpdate(QueryUtil.query(CommonConstants.EMPLOYEES_DROP_TABLE_QUERY));
			statement.executeUpdate(QueryUtil.query(CommonConstants.EMPLOYEES_CREATE_TABLE_QUERY));	
		} 
		catch (SQLTimeoutException e) {
			log.log(Level.SEVERE, e.getMessage());
		}
		catch (SQLException e) {
			log.log(Level.SEVERE, e.getMessage());
		} 
		catch (Exception e) {
			log.log(Level.SEVERE, e.getMessage());
		}
		finally {
			try {
				if (statement != null) {
					statement.close();
				}
				if (connection != null) {
					connection.close();
				}
			} 
			catch (SQLException e) {
				log.log(Level.SEVERE, e.getMessage());
			}
			catch (Exception e) {
				log.log(Level.SEVERE, e.getMessage());
			}
		}
	}

	public void addEmployee() {
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		try {
			connection = DBConnectionUtil.getDBConnection();			
			preparedStatement = connection.prepareStatement(QueryUtil.query(CommonConstants.EMPLOYEES_INSERT_DATA_QUERY));
			connection.setAutoCommit(false);
			
			for(int i = 0; i < employeeList.size(); i++){
				
				Employee employee = employeeList.get(i);
				
				preparedStatement.setString(CommonConstants.COLUMN_INDEX_ONE, employee.getEmployeeId());
				preparedStatement.setString(CommonConstants.COLUMN_INDEX_TWO, employee.getFullName());
				preparedStatement.setString(CommonConstants.COLUMN_INDEX_THREE, employee.getAddress());
				preparedStatement.setString(CommonConstants.COLUMN_INDEX_FOUR, employee.getFacultyName());
				preparedStatement.setString(CommonConstants.COLUMN_INDEX_FIVE, employee.getDepartment());
				preparedStatement.setString(CommonConstants.COLUMN_INDEX_SIX, employee.getDesignation());
				
				preparedStatement.addBatch();
			}
			
			preparedStatement.executeBatch();
			connection.commit();
		} 
		catch (BatchUpdateException e) {
			log.log(Level.SEVERE, e.getMessage());
		} 
		catch (IndexOutOfBoundsException e) {
			log.log(Level.SEVERE, e.getMessage());
		} 
		catch (SQLTimeoutException e) {
			log.log(Level.SEVERE, e.getMessage());
		} 
		catch (SQLException e) {
			log.log(Level.SEVERE, e.getMessage());
		} 
		catch (Exception e) {
			log.log(Level.SEVERE, e.getMessage());
		}
		finally {
			try {
				if (preparedStatement != null) {
					preparedStatement.close();
				}
				if (connection != null) {
					connection.close();
				}
			} 
			catch (SQLException e) {
				log.log(Level.SEVERE, e.getMessage());
			}
			catch (Exception e) {
				log.log(Level.SEVERE, e.getMessage());
			}
		}

	}

	public void getEmployeeById(String employeeId) {
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		Employee employee = new Employee();
		ArrayList<Employee> employees = new ArrayList<Employee>();

		try {
			connection = DBConnectionUtil.getDBConnection();
			preparedStatement = connection.prepareStatement(QueryUtil.query(CommonConstants.EMPLOYEES_RETRIEVE_EMPLOYEE_QUERY));
			preparedStatement.setString(CommonConstants.COLUMN_INDEX_ONE, employeeId);
			
			ResultSet resultSet = preparedStatement.executeQuery();
			
			while(resultSet.next()) {
				employee.setEmployeeId(resultSet.getString(CommonConstants.COLUMN_INDEX_ONE));
				employee.setFullName(resultSet.getString(CommonConstants.COLUMN_INDEX_TWO));
				employee.setAddress(resultSet.getString(CommonConstants.COLUMN_INDEX_THREE));
				employee.setFacultyName(resultSet.getString(CommonConstants.COLUMN_INDEX_FOUR));
				employee.setDepartment(resultSet.getString(CommonConstants.COLUMN_INDEX_FIVE));
				employee.setDesignation(resultSet.getString(CommonConstants.COLUMN_INDEX_SIX));
			}
			
			employees.add(employee);
			printEmployee(employees);
		} 
		catch (SQLTimeoutException e) {
			log.log(Level.SEVERE, e.getMessage());
		} 
		catch (SQLException e) {
			log.log(Level.SEVERE, e.getMessage());
		} 
		catch (Exception e) {
			log.log(Level.SEVERE, e.getMessage());
		}
		finally {
			try {
				if (preparedStatement != null) {
					preparedStatement.close();
				}
				if (connection != null) {
					connection.close();
				}
			} 
			catch (SQLException e) {
				log.log(Level.SEVERE, e.getMessage());
			}
			catch (Exception e) {
				log.log(Level.SEVERE, e.getMessage());
			}
		}
	}

	public void deleteEmployee(String employeeId) {
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		try {
			connection = DBConnectionUtil.getDBConnection();
			preparedStatement = connection.prepareStatement(QueryUtil.query(CommonConstants.EMPLOYEES_RETRIEVE_DELETE_EMPLOYEE_QUERY));
			preparedStatement.setString(CommonConstants.COLUMN_INDEX_ONE, employeeId);
			preparedStatement.executeUpdate();
		} 
		catch (SQLTimeoutException e) {
			log.log(Level.SEVERE, e.getMessage());
		} 
		catch (SQLException e) {
			log.log(Level.SEVERE, e.getMessage());
		} 
		catch (Exception e) {
			log.log(Level.SEVERE, e.getMessage());
		}
		finally {
			try {
				if (preparedStatement != null) {
					preparedStatement.close();
				}
				if (connection != null) {
					connection.close();
				}
			} 
			catch (SQLException e) {
				log.log(Level.SEVERE, e.getMessage());
			}
			catch (Exception e) {
				log.log(Level.SEVERE, e.getMessage());
			}
		}

	}

	public void displayEmployee() {
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ArrayList<Employee> employees = new ArrayList<Employee>();
		
		try {
			connection = DBConnectionUtil.getDBConnection();
			preparedStatement = connection.prepareStatement(QueryUtil.query(CommonConstants.EMPLOYEES_RETRIEVE_ALL_EMPLOYEES_QUERY));
			
			ResultSet resultSet = preparedStatement.executeQuery();
			
			while (resultSet.next()) {
				
				Employee employee = new Employee();
				
				employee.setEmployeeId(resultSet.getString(CommonConstants.COLUMN_INDEX_ONE));
				employee.setFullName(resultSet.getString(CommonConstants.COLUMN_INDEX_TWO));
				employee.setAddress(resultSet.getString(CommonConstants.COLUMN_INDEX_THREE));
				employee.setFacultyName(resultSet.getString(CommonConstants.COLUMN_INDEX_FOUR));
				employee.setDepartment(resultSet.getString(CommonConstants.COLUMN_INDEX_FIVE));
				employee.setDesignation(resultSet.getString(CommonConstants.COLUMN_INDEX_SIX));
				
				employees.add(employee);
			}
		} 
		catch (SQLTimeoutException e) {
			log.log(Level.SEVERE, e.getMessage());
		} 
		catch (SQLException e) {
			log.log(Level.SEVERE, e.getMessage());
		} 
		catch (Exception e) {
			log.log(Level.SEVERE, e.getMessage());
		}
		finally {
			try {
				if (preparedStatement != null) {
					preparedStatement.close();
				}
				if (connection != null) {
					connection.close();
				}
			} 
			catch (SQLException e) {
				log.log(Level.SEVERE, e.getMessage());
			}
			catch (Exception e) {
				log.log(Level.SEVERE, e.getMessage());
			}
		}
		printEmployee(employees);
	}
	
	public final void employeeTableTransactions() {
		employeesFromXML();
		createEmployeesTable();
		addEmployee();
		displayEmployee();
	}
	
	public void printEmployee(ArrayList<Employee> employeeList){

		System.out.printf(CommonConstants.EMPLOYEE_TABLE_COLUMNS);
		System.out.println(CommonConstants.TABLE_COLUMNS_BREAK_LINE);
		
		for(int i = 0; i < employeeList.size(); i++){
			
			Employee employee = employeeList.get(i);
			
			System.out.printf(CommonConstants.EMPLOYEE_TABLE_COLUMN_ORDER_IN_ROW,
									employee.getEmployeeId(), 
									employee.getFullName(), 
									employee.getAddress(), 
									employee.getFacultyName(), 
									employee.getDepartment(), 
									employee.getDesignation());
			
			System.out.println(CommonConstants.TABLE_ROW_BREAK_LINE);
		}
	}
}