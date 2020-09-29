package com.hackerthon.common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.SQLTimeoutException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * This Singleton class is for database connection for the project
 * 
 * @author Kavindu Tharaka
 * @version 1.1
 */

public class DBConnectionUtil extends UtilC {

	private static Connection connection = null;
	private static final Logger log = Logger.getLogger(DBConnectionUtil.class.getName());
	
	private DBConnectionUtil() {
		
	}

	public static Connection getDBConnection(){
		try {
			if (connection == null || connection.isClosed()) {

				Class.forName(p.getProperty(CommonConstants.DRIVER_NAME));

				connection = DriverManager.getConnection(
								p.getProperty(CommonConstants.URL), 
								p.getProperty(CommonConstants.USERNAME),
								p.getProperty(CommonConstants.PASSWORD));
			}
		} 
		catch (ClassNotFoundException e) {
			log.log(Level.SEVERE, e.getMessage());
		} 
		catch (SQLTimeoutException e) {
			log.log(Level.SEVERE, e.getMessage());
		}
		catch (SQLException e) {
			log.log(Level.SEVERE, e.getMessage());
		}
		catch (ExceptionInInitializerError e) {
			log.log(Level.SEVERE, e.getMessage());
		}
		catch (LinkageError e) {
			log.log(Level.SEVERE, e.getMessage());
		}
		return connection;
	}
}