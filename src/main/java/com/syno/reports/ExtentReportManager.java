package com.syno.reports;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.syno.base.ControlActions;
import com.syno.base.ITestListeners;
import com.syno.constants.ApplicationConstants;
import com.syno.constants.FilePaths;
import com.syno.customAnnotation.TestCaseDetails;
import com.syno.enums.TestCaseCategoryType;

public class ExtentReportManager {

	private ExtentReportManager() {
	}

	private static final ExtentReports extentReports = new ExtentReports();
	private static final ThreadLocal<ExtentTest> extentTest = new ThreadLocal<>();

	public static void initReport() {
		String folderTimeStamp = new SimpleDateFormat("dd_MMM_yyyy").format(new Date());
		String reportTimeStamp = new SimpleDateFormat("dd_MMM_yyyy_hh_mm").format(new Date());

		ExtentSparkReporter spark = new ExtentSparkReporter(FilePaths.EXTENT_REPORT + folderTimeStamp + File.separator + "ExecutionReport_" + System.getProperty("user.name") + "_" + reportTimeStamp + ".html");
		spark.config().setTheme(Theme.STANDARD);
		spark.config().setDocumentTitle("Extent Reports");
		spark.config().setReportName(System.getProperty("user.name"));
		spark.config().setDocumentTitle("Synoverge POM Automation");

		extentReports.setSystemInfo("Java Version", System.getProperty("java.version"));
		extentReports.setSystemInfo("Author", System.getProperty("user.name").toUpperCase());
		extentReports.setSystemInfo("Execution Env", ApplicationConstants.ENVIRONMENT);
		extentReports.setSystemInfo("OS", System.getProperty("os.name"));
		extentReports.setSystemInfo("Browser", ApplicationConstants.BROWSER);
		extentReports.attachReporter(spark);
	}

	public static void flushReports() {
		extentReports.flush();
	}

	public static void createTest(ITestResult result) {
		extentTest.set(extentReports.createTest(getMethodName(result)));
		extentTest.set(extentTest.get().createNode("Test Case : " + getMethodName(result)));

		extentTest.set(extentTest.get().assignAuthor(result.getMethod().getConstructorOrMethod().getMethod().getAnnotation(TestCaseDetails.class).testCaseTitle()));
		TestCaseCategoryType[] categories = result.getMethod().getConstructorOrMethod().getMethod().getAnnotation(TestCaseDetails.class).category();
		addCategory(categories);
		extentTest.set(extentTest.get().assignDevice(result.getMethod().getConstructorOrMethod().getMethod().getAnnotation(TestCaseDetails.class).testCaseID()));
	}

	private static void addCategory(TestCaseCategoryType[] categories) {
		for (TestCaseCategoryType category : categories) {
			extentTest.set(extentTest.get().assignCategory(category.toString()));
		}
	}

	public static void pass(ITestResult result) {
		extentTest.get().log(Status.PASS, getMethodName(result));
	}

	public static void skip(ITestResult result) {
		extentTest.get().log(Status.SKIP, getMethodName(result));
		extentTest.get().log(Status.SKIP, result.getThrowable());
	}

	public static void fail(ITestResult result) {
		if(ITestListeners.browserFlag)
			extentTest.get().addScreenCaptureFromBase64String(ControlActions.takeScreenShot(), getMethodName(result).toLowerCase());
		extentTest.get().log(Status.FAIL, getMethodName(result));
		extentTest.get().log(Status.FAIL, result.getThrowable());
	}

	private static String getMethodName(ITestResult result) {
		return result.getMethod().getMethodName();
	}

	public static void info(String message) {
		extentTest.get().info(message);
	}

	public static void ward(String message) {
		extentTest.get().warning(message);
	}

	public static void pass(String message) {
		extentTest.get().pass(message);
	}

	public static void fail(String message) {
		extentTest.get().fail(message);
	}

	public static void info(Markup createTable) {
		extentTest.get().info(createTable);		
	}
}
