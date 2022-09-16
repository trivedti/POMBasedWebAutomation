package com.syno.testScripts;



import java.util.Properties;

import org.testng.Assert;


import com.syno.constants.ApplicationConstants;

//import com.syno.enums.clients.AddClient;

import com.syno.pages.ClientPage;
import com.syno.pages.CosmosDBPage;


import com.syno.pages.HomePage;

import com.syno.reports.ExtentReportManager;

public class TestBase {
	
	public static Properties prop = new Properties();
	
	HomePage homePage = HomePage.getHomePageInstance();
	ClientPage clientPage=ClientPage.getClientPageInstance();
	
	
	CosmosDBPage cosmosDBPage = CosmosDBPage.getCosmosDBPage();
	
	
	/*
	 * To get email address for login.
	 * 
	 * @return String - emailID
	 */
	String getEmailAddress(){
		return ApplicationConstants.EMAIL;
	}


	/*
	 * To get password for login.
	 * 
	 * @return String - password
	 */
	String getPasswordAddress(){
		return ApplicationConstants.PASSWORD;
	}
	
	/*
	 * This method will delay the script by given time
	 * 
	 * @param long millis wait for that time
	 */
	void delay(long millis){
		try {
			Thread.sleep(millis);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	
	/*
	 * To assert test case and add steps to extent report.
	 * 
	 * @param - boolean flagForAssertion
	 * 			String errorMsg
	 * 			String statusForReport
	 * 			String reportMessage
	 */
	public void reportStep(boolean flagForAssertion, String statusForReport, String passMessage, String errorMessage) {
		if (!flagForAssertion) {
			ExtentReportManager.fail("ERROR - " + errorMessage);
			Assert.assertTrue(flagForAssertion, "ERROR - " + errorMessage);
		} else {
			Assert.assertTrue(flagForAssertion);
			if (statusForReport.equalsIgnoreCase("pass"))
				ExtentReportManager.pass("STEP : " + passMessage);
			else
				ExtentReportManager.info("STEP : " + passMessage);
		}
	}
}
