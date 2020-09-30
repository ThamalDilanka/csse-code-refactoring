package com.hackerthon.common;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.Properties;

public class CommonUtil {

	public static final Properties properties = new Properties();
	private static final Logger log = Logger.getLogger(DBConnectionUtil.class.getName());

	static {
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
