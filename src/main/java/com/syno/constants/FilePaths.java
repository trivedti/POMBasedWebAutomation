package com.syno.constants;

import java.io.File;

public abstract class FilePaths {

	/**
	 * This is Base path for all the page locators
	 */
	public static final String PROPERTIES_FOLDER_PATH = System.getProperty("user.dir") + File.separator + "src"
			+ File.separator + "main" + File.separator + "resources" + File.separator + "pageLocators" + File.separator;

	public static final String EXTENT_REPORT = System.getProperty("user.dir") + File.separator
			+ "src" + File.separator + "test" + File.separator + "resources" + File.separator + "executionsReports" + File.separator;

	public static final String QAData_FILE_PATH = System.getProperty("user.dir") + File.separator + "src"
			+ File.separator + "test" + File.separator + "resources" + File.separator + "testData" + File.separator
			+ "QA" + File.separator + "QA1.MP4";

	public static final String PATH_FOR_CSVTEXTDATA = System.getProperty("user.dir") + File.separator + "src"
			+ File.separator + "test" + File.separator + "resources" + File.separator + "testData" + File.separator
			+ "csvFile" + File.separator + "QATestData.xlsx";

	
	public static final String PATH_FOR_Download_FileValidation_FILE = System.getProperty("user.dir") + File.separator
			+ "src" + File.separator + "test" + File.separator + "resources" + File.separator + "testData"
			+ File.separator + "fileValidation" + File.separator + "%s"+ "_" + System.currentTimeMillis() + ".txt";
	
	public static final String CONFIG_FILE = System.getProperty("user.dir") + File.separator
			+ "src" + File.separator + "test" + File.separator + "resources" + File.separator + "testData" + File.separator + "config.properties";

	
}