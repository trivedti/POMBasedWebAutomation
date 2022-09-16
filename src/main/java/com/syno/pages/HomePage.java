package com.syno.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.syno.base.ControlActions;
import com.syno.enums.FileNames;
import com.syno.utilities.PropertyFileOperations;

public class HomePage extends ControlActions {

	private Logger log = LogManager.getLogger(this.getClass());

	PropertyFileOperations locators;
	private static HomePage homePage;

	/*
	 * To get HomePage instance
	 * 
	 * @return HomePage
	 */
	private HomePage() {
		locators = new PropertyFileOperations(FileNames.homePage);
		log.info("STEP: Home Page Locators loaded into memory");
	}

	public static HomePage getHomePageInstance() {
		if (homePage == null)
			homePage = new HomePage();
		return homePage;
	}

	/*
	 * This method will login on AD platform.
	 * 
	 * @param String - email Id and password required for login.
	 */
	public boolean loginOnTuftPlatform(String emailID, String password) {
		enterEmailAddress(emailID);
		enterPassword(password);
		clickOnLogInButton();
		return isElementDisplayed(locators.getKey("ClickAddClientmenu"));
	}
	
	
	/*
	 * To click on Log in button.
	 */
	private void clickOnLogInButton() {
		clickOnElement(locators.getKey("LoginButton"));
		log.info("STEP: Click on Log-in button");
	}
	
	
	/*
	 * To enter password for login.
	 * 
	 * @param String - Password
	 */
	private void enterPassword(String password) {
		enterText(locators.getKey("EnterPassword"), password);
		log.info("STEP: Password entered");
	}

	/*
	 * To enter email Id for login.
	 * 
	 * @param String - email Id
	 */
	private void enterEmailAddress(String emailID) {
		enterText(locators.getKey("EnterEmail"), emailID);
		log.info("STEP: Email address entered " + emailID);
	}
}