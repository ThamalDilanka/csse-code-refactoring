package com.hackerthon.service;

import java.util.ArrayList;
import java.sql.BatchUpdateException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLTimeoutException;
import java.sql.Statement;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.PreparedStatement;
import com.hackerthon.common.UtilTRANSFORM;
import com.hackerthon.common.UtilQ;
import com.hackerthon.model.Employee;
import com.hackerthon.common.CommonConstants;
import com.hackerthon.common.DBConnectionUtil;
import com.hackerthon.common.UtilC;

public class EmployeeServiceImpl extends UtilC {

	private final ArrayList<Employee> employeeList = new ArrayList<Employee>();
	private final Logger log = Logger.getLogger(DBConnectionUtil.class.getName());

	public void employeesFromXML() {

		try {
			
			int size = UtilTRANSFORM.xmlxPaths().size();
			
			for (int i = 0; i < size; i++) {
				
				Map<String, String> l = UtilTRANSFORM.xmlxPaths().get(i);
				
				Employee employee = new Employee();
				
				employee.setEmployeeId(l.get("XpathEmployeeIDKey"));
				employee.setFullName(l.get("XpathEmployeeNameKey"));
				employee.setAddress(l.get("XpathEmployeeAddressKey"));
				employee.setFacultyName(l.get("XpathFacultyNameKey"));
				employee.setDepartment(l.get("XpathDepartmentKey"));
				employee.setDesignation(l.get("XpathDesignationKey"));
				
				employeeList.add(employee);
				
				System.out.println(employee.toString() + "\n");
			}
		} catch (Exception e) {
			log.log(Level.SEVERE, e.getMessage());
		}
	}

	public void createEmployeesTable() {
		
		Connection connection = null;
		Statement statement = null;
		
		try {
			connection = DBConnectionUtil.getDBConnection();	
			statement = connection.createStatement();
			statement.executeUpdate(UtilQ.Q("q2"));
			statement.executeUpdate(UtilQ.Q("q1"));	
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
			preparedStatement = connection.prepareStatement(UtilQ.Q("q3"));
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
			preparedStatement = connection.prepareStatement(UtilQ.Q("q4"));
			preparedStatement.setString(CommonConstants.COLUMN_INDEX_ONE, employeeId);
			
			ResultSet resultSet = preparedStatement.executeQuery();
			
			while (resultSet.next()) {
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
			preparedStatement = connection.prepareStatement(UtilQ.Q("q6"));
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
			preparedStatement = connection.prepareStatement(UtilQ.Q("q5"));
			
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
	
	public void printEmployee(ArrayList<Employee> employeeList){

		System.out.printf(CommonConstants.EMPLOYEE_TABLE_COLUMNS);
		System.out.println(CommonConstants.EMPLOYEE_TABLE_COLUMNS_BREAK_LINE);
		
		for(int i = 0; i < employeeList.size(); i++){
			
			Employee employee = employeeList.get(i);
			
			System.out.printf(CommonConstants.EMPLOYEE_TABLE_COLUMN_ORDER_IN_ROW,
									employee.getEmployeeId(), 
									employee.getFullName(), 
									employee.getAddress(), 
									employee.getFacultyName(), 
									employee.getDepartment(), 
									employee.getDesignation());
			
			System.out.println(CommonConstants.EMPLOYEE_TABLE_ROW_BREAK_LINE);
		}
	}
}