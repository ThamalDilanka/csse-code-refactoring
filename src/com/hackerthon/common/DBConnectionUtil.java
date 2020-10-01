package com.hackerthon.common;

import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.SQLTimeoutException;

/**
 * This Singleton class is for database connection for the project
 * 
 * @author Kavindu Tharaka
 * @version 1.0
 */

public class DBConnectionUtil extends CommonUtil {

	private static Connection connection = null;
	private static final Logger log = Logger.getLogger(DBConnectionUtil.class.getName());
	
	private DBConnectionUtil() {
		
	}

	/**
	 * This create connection with database.
	 * 
	 * @return Connection - Connection type Object will be returned.
	 */
	public static Connection getDBConnection(){
		try {
			if (connection == null || connection.isClosed()) {

				Class.forName(properties.getProperty(CommonConstants.DRIVER_NAME));

				connection = DriverManager.getConnection(
								properties.getProperty(CommonConstants.URL), 
								properties.getProperty(CommonConstants.USERNAME),
								properties.getProperty(CommonConstants.PASSWORD));
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