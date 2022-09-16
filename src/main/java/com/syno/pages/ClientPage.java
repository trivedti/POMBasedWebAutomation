package com.syno.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import com.syno.base.ControlActions;
import com.syno.enums.FileNames;
import com.syno.utilities.PropertyFileOperations;

public class ClientPage extends ControlActions {

	private Logger log = LogManager.getLogger(this.getClass());

	PropertyFileOperations locators;
	private static ClientPage clientPage;

	private ClientPage() {
		locators = new PropertyFileOperations(FileNames.clientPage);
		log.info("STEP: Clients Page Locators loaded into memory");
	}
	
	/*
	 * To get ClientPage instance
	 * 
	 * @return ClientPage
	 */
	public static ClientPage getClientPageInstance() {
		if (clientPage == null)
			clientPage = new ClientPage();
		return clientPage;
	}

	/*
	 * Navigate to client management page.
	 * 
	 * @return boolean - True if navigation is successful.
	 */
	public boolean goToClientManagement() {
		clickOnElement(locators.getKey("ClickAddClientmenu"));
		log.info("STEP: On Client management page.");
		return isElementDisplayed(locators.getKey("AddClientButton"));
	}

	/*
	 * Navigate to add client page.
	 * 
	 * @return boolean - True if navigation is successful.
	 */
	public boolean goToAddClientPage() {
		clickOnElement(locators.getKey("AddClientButton"));
		log.info("STEP: Clicked on Add Client button.");
		return isElementDisplayed(locators.getKey("EnterFirstName"));
	}

	
	
	/*
	 * To Create new client.
	 * 
	 * @param String - Start LName , FName, String clientName
	 */
	public boolean createClient(String FName, String LName, String MNo) {
		enterFirstName(FName);
		enterLastName(LName);
		enterMobileNumber(MNo);
		clickSaveChangesButton();
		
		boolean clientCreateSuccess = waitForElement(2000, locators.getKey("ClickOnOkButtonofMessage"));
		return clientCreateSuccess;
	}
	
	
	/*
	 * To Enter client first name .
	 * 
	 * @param String - First Name for client 
	 */
	private void enterFirstName(String FName) {
		String firstName = String.format(locators.getKey("EnterFirstName"), FName);
		enterText(firstName, FName);
		log.info("STEP: Entered first name for Asset");
	}

	
	/*
	 * To Enter client Last name .
	 * 
	 * @param String - Last Name for client 
	 */
	private void enterLastName(String LName) {
		String lastName = String.format(locators.getKey("EnterLastName"), LName);
		enterText(lastName, LName);
		log.info("STEP: Entered last name for client");
	}
	
	
	/*
	 * To Enter client mobile number .
	 * 
	 * @param String - Mobile Number for client 
	 */
	private void enterMobileNumber(String MNo) {
		String mobileNumber = String.format(locators.getKey("EnterClientMobile"), MNo);
		enterText(mobileNumber, MNo);
		log.info("STEP: Entered mobile number for client");
	}
	/*
	 * Click on Save Changes button to submit client details.
	 */
	public void clickSaveChangesButton() {
		EnterOnButton(locators.getKey("ClickOnSaveChanges"));
		wait(3000);
		log.info("STEP: Clicked on Save Changes button");
	}
	
	
	/*
	 * To Review Clients.
	 * 
	 *  @param String - Client name to be searched.
	 */
	public void reviewClient(String searchClientValue,String clientName) {
		clearSearchClientField();
		enterSearchClient(searchClientValue);
		clickSearchedClient(clientName);
		isClientCreated(clientName);
		
	}

	/*
	 * Enter Client name to be searched.
	 * 
	 * @param String -  Client name to be searched.
	 */
	private void enterSearchClient(String searchClientValue) {
		wait(500);
		enterText(locators.getKey("SearchClientText"), searchClientValue+Keys.ENTER);
		wait(2000);
		log.info("STEP: Entered Client name to be search.");
	}

	/*
	 * Clicked on searched Client.
	 * 
	 * @param String - Client name.
	 */
	private void clickSearchedClient(String clientName) {
		String clientToSearchLocator = String.format(locators.getKey("clickExistingSearchedClient"), clientName);
		clickOnElement(clientToSearchLocator);
		log.info("STEP: Clicked on searched client");
	}
	
	
	
	/*
	 * To check Client is created or not
	 * 
	 * @param - String clientName
	 * 
	 * @return - boolean flagForClientCreation
	 */
	public boolean isClientCreated(String clientName) {
		enterSearchClient(clientName);
		String clientFromTable = getElementText(locators.getKey("clickExistingSearchedClient"));
		boolean flagForClientCreation = clientName.equalsIgnoreCase(clientFromTable);
		return flagForClientCreation;
	}

	
	/*
	 * This method will clear search asset field.
	 */
	private void clearSearchClientField() {
		wait(500);
		getElement(locators.getKey("SearchClientText")).clear();
		wait(500);
		log.info("STEP: Cleared Client search field.");
	}

	
	
	
}