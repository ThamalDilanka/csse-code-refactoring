package com.hackerthon.common;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.Properties;
/**
 * Contains common utilities 
 * 
 * @author Chamod Rathnayake
 * @version 1.1
 */

public class CommonUtil {

	/**Constant for cofigure properties */
	public static final Properties properties = new Properties();


	/**
	 * Constant for diplay message for this application which returns the name of the entity
	 * static -there is only one logger instance per class, also avoiding attempts to serialize logger
	 * final - no need to change the logger over the lifetime of the class
	 */
	private static final Logger log = Logger.getLogger(DBConnectionUtil.class.getName());

	static {
		/**
		 *  this static method allows unchecked/runtime-exceptions to be thrown
		 */
		try {
			properties.load(QueryUtil.class.getResourceAsStream(CommonConstants.PROPERTY_FILE));
		} 
		catch (IOException e) {
			log.log(Level.SEVERE, e.getMessage());
		}
		catch (IllegalArgumentException e) {
			log.log(Level.SEVERE, e.getMessage());
		}
		catch (NullPointerException e) {
			log.log(Level.SEVERE, e.getMessage());
		}
	}
}
