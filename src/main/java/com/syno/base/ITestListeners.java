package com.syno.base;

import org.apache.logging.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.apache.logging.log4j.LogManager;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;

import com.google.common.base.Strings;
import com.syno.constants.ApplicationConstants;
import com.syno.customAnnotation.TestCaseDetails;
import com.syno.reports.ExtentReportManager;
import com.syno.utilities.AzureUtils;

public class ITestListeners extends ControlActions implements ITestListener {
	public static boolean browserFlag = true;
	Logger log = LogManager.getLogger(this.getClass());
	AzureUtils azureUtils;
	int testCaseID;

	public void onTestStart(ITestResult result) {
		ExtentReportManager.createTest(result);
		if(browserFlag){
			initBrowser(ApplicationConstants.BROWSER, ApplicationConstants.URL);
			ExtentReportManager.pass("STEP: " + ApplicationConstants.URL + " browser launched");
			log.info("STEP:" + ApplicationConstants.BROWSER + " browser launched");
			log.info("STEP:" + ApplicationConstants.URL + " loaded");
			testCaseIDDetail = result.getMethod().getConstructorOrMethod().getMethod().getAnnotation(TestCaseDetails.class).testCaseID();
			testCaseID = Integer.parseInt(testCaseIDDetail.split(":")[1].trim());
			try {
				azureUtils = new AzureUtils();
			//	linkedTestCaseID = azureUtils.getInnerUniqueLinkedTestCaseIDFromAzure(testCaseID);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public void onTestSuccess(ITestResult result) {
		ExtentReportManager.pass(result);
		if(browserFlag){
			if (AzureUtils.prop.getProperty("updateOnAzure").equalsIgnoreCase("yes")) {
				Reporter.log(result.getMethod().getMethodName() + "Test cases is Passed");
				try {
					azureUtils = new AzureUtils();
					azureUtils.updateTestCaseStatusOnAzure(linkedTestCaseID , 2);
				} catch (Exception e) {
					log.error("Error in updateTestCaseStatusOnAzure method", e);
				}
			}
			terminateBrowser();
		}
	}

	public void onTestFailure(ITestResult result) {
		ExtentReportManager.fail(result);
		if(browserFlag){
			if (AzureUtils.prop.getProperty("updateOnAzure").equalsIgnoreCase("yes")) {
				Reporter.log(result.getMethod().getMethodName() + "Test cases is Failed");
				try {
					azureUtils = new AzureUtils();
					azureUtils.updateTestCaseStatusOnAzure(linkedTestCaseID , 3);
				} catch (Exception e) {
					log.error("Error in updateTestCaseStatusOnAzure method", e);
				}
			}
			terminateBrowser();
			ExtentReportManager.pass("STEP: " + ApplicationConstants.BROWSER + " Closed");
			log.info("STEP: " + ApplicationConstants.BROWSER + " Closed");
		}
	}

	public void onTestSkipped(ITestResult result) {
		ExtentReportManager.skip(result);
		if(browserFlag){
			terminateBrowser();
			ExtentReportManager.pass("STEP: " + ApplicationConstants.BROWSER + " Closed");
			log.info("STEP: " + ApplicationConstants.BROWSER + " Closed");
		}
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
	}

	public void onStart(ITestContext context) {
		PropertyConfigurator.configure("./src/main/resources/log4j2.xml");
		ExtentReportManager.initReport();

		ApplicationConstants.BROWSER = Strings.isNullOrEmpty(System.getProperty("browser"))
				? ApplicationConstants.BROWSER
				: System.getProperty("browser");
		ApplicationConstants.ENVIRONMENT = Strings.isNullOrEmpty(System.getProperty("env"))
				? ApplicationConstants.ENVIRONMENT
				: System.getProperty("env");
		ApplicationConstants.URL = Strings.isNullOrEmpty(System.getProperty("url")) ? ApplicationConstants.URL
				: System.getProperty("url");

	}

	public void onFinish(ITestContext context) {
		ExtentReportManager.flushReports();
	}
}
