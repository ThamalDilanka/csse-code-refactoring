package com.hackerthon.common;

/**
 * This class contains all the common constants for the project
 * 
 * @author Kavindu Tharaka
 * @version 1.0
 */

public class CommonConstants {
	
	/** Constant for config.properties key for query file path */
	public static final String QUERY_XML = "QueryFilePath";
	
	/** Constant for file path of config.properties*/
	public static final String PROPERTY_FILE = "../config/config.properties";

	/** Constant for query tag in EmployeeQuery.xml */
	public static final String TAG_NAME = "query";
	
	/** Constant for query id in EmployeeQuery.xml */
	public static final String ATTRIBUTE_ID = "id";
	
	/** Constant for comma */
	public static final String COMMA = ",";
	
	/** Constant for url key of MYSQL database in config.properties */
	public static final String URL = "url";
	
	/** Constant for user name key of MYSQL database in config.properties */
	public static final String USERNAME = "username";
	
	/** Constant for password key of MYSQL database in config.properties */
	public static final String PASSWORD = "password";
	
	/** Constant for driver name key of MYSQL database in config.properties */
	public static final String DRIVER_NAME = "driverName";
	
	/** Constant for column index one */
	public static final int COLUMN_INDEX_ONE = 1;
	
	/** Constant for column index two */
	public static final int COLUMN_INDEX_TWO = 2;
	
	/** Constant for column index three */
	public static final int COLUMN_INDEX_THREE = 3;
	
	/** Constant for column index four */
	public static final int COLUMN_INDEX_FOUR = 4;
	
	/** Constant for column index five */
	public static final int COLUMN_INDEX_FIVE = 5;
	
	/** Constant for column index six */
	public static final int COLUMN_INDEX_SIX = 6;
	
	/** Constant for columns of employee table */
	public static final String EMPLOYEE_TABLE_COLUMNS = "Employee ID\t\tFull Name\t\t\tAddress\t\t\t\tFaculty Name\t\tDepartment\t\t\tDesignation\n%n";
	
	/** Constant for columns of employee table */
	public static final String EMPLOYEE_TO_STRING_FORMAT = "Employee ID \t=\t %s\nFullName \t=\t %s\nAddress \t=\t %s\nFaculty Name \t=\t %s\nDepartment \t=\t %s\nDesignation \t=\t %s";
	
	/** Constant for query id of create_table in EmployeeQuery.xml */
	public static final String EMPLOYEES_CREATE_TABLE_QUERY = "employees_create_table_query";
	
	/** Constant for query id of drop_table in EmployeeQuery.xml */
	public static final String EMPLOYEES_DROP_TABLE_QUERY = "employees_drop_table_query";
	
	/** Constant for query id of insert employee in EmployeeQuery.xml */
	public static final String EMPLOYEES_INSERT_DATA_QUERY = "employees_insert_data_query";
	
	/** Constant for query id of retrieve an employee in EmployeeQuery.xml */
	public static final String EMPLOYEES_RETRIEVE_EMPLOYEE_QUERY = "employees_retrieve_employee_query";
	
	/** Constant for query id of retrieve all employees in EmployeeQuery.xml */
	public static final String EMPLOYEES_RETRIEVE_ALL_EMPLOYEES_QUERY = "employees_retrieve_all_employees_query";
	
	/** Constant for query id of delete employee in EmployeeQuery.xml */
	public static final String EMPLOYEES_RETRIEVE_DELETE_EMPLOYEE_QUERY = "employees_delete_employee_query";
	
	/** Constant for cell order in a row of employee table */
	public static final String EMPLOYEE_TABLE_COLUMN_ORDER_IN_ROW = "%s\t\t%s\t\t%s\t\t%s\t\t%s\t\t%s\n%n";
	
	/** Constant for heading break line of employee table */
	public static final String TABLE_COLUMNS_BREAK_LINE = "=================================================================================================================================";
	
	/** Constant for row break line of employee table */
	public static final String TABLE_ROW_BREAK_LINE = "-------------------------------------------------------------------------------------------------------------------------------------";

	/** Constant for xpath employee id key*/
	public static final String XPATH_EMPLOYEE_ID_KEY = "xpath_employee_id";
	
	/** Constant for xpath employee name key*/
	public static final String XPATH_EMPLOYEE_NAME_KEY = "xpath_employee_name";
	
	/** Constant for xpath emoplyee address ke*/
	public static final String 	XPATH_EMPLOYEE_ADDRESS_KEY = "xpath_employee_address";
	
	/**	Constant for xpath employee faculty name*/
	public static final String  XPATH_EMPLOYEE_FACULTY_NAME_KEY = "xpath_employee_faculty_name";
	
	/** Constant for xpath employee department key*/
	public static final String XPATH_EMPLOYEE_DEPARTMENT_KEY = "xpath_employee_department";

	/** Constant for xpath employee designation key*/
	public static final String XPATH_EMPLOYEE_DESIGNATION_KEY = "xpath_employee_designation";

	/** Constant for file path employee request xml*/
	public static final String SRC_EMPLOYEE_XML_REQUEST = "src_employee_xml_request";
	
	/** Constant for file path employee modified xsl*/
	public static final String SRC_EMPLOYEE_XSL = "src_employee_xsl";
	
	/** Constant for file path employee response xml*/
	public static final String SRC_EMPLOYEE_XML_RESPONSE = "src_employee_xml_response";
	
	/** Constant for count compile path*/
	public static final String COMPLIE_COUNT_EMPLOYEE = "count(//Employees/Employee)";
	
	/** Constant for employee compile path*/
	public static final String COMPLIE_EMPLOYEES_EMPLOYEES= "//Employees/Employee[";
	
	/** Constant for employee id compile path*/
	public static final String COMPILE_EMOPLYEES_EMOPLYEE_ID = "]/EmployeeID/text()";
	
	/** Constant for employee name compile path*/
	public static final String COMPILE_EMOPLYEES_EMOPLYEE_NAME = "]/EmployeeFullName/text()";
	
	/** Constant for employee address compile path*/
	public static final String COMPILE_EMOPLYEES_EMOPLYEE_ADDRESS = "]/EmployeeFullAddress/text()";
	
	/** Constant for employee faculty name compile path*/
	public static final String COMPILE_EMOPLYEES_EMOPLYEE_FACULTY_NAME = "]/FacultyName/text()";
	
	/** Constant for employee department compile path*/
	public static final String COMPILE_EMOPLYEES_EMOPLYEE_DEPARTMENT = "]/Department/text()";

	/** Constant for employee designation compile path*/
	public static final String COMPILE_EMOPLYEES_EMOPLYEE_DESIGNATION = "]/Designation/text()";
	
	/** Constant for file path query xml */
	public static final String SRC_EMOPLOYEE_QUERY_XML= "src_emoployee_query_xml";
}
