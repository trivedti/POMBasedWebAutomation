package com.syno.testScripts;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import com.syno.base.ITestListeners;
import com.syno.customAnnotation.TestCaseDetails;
import com.syno.enums.TestCaseCategoryType;
import com.syno.enums.clients.AddClient;



@Listeners(ITestListeners.class)
public class ClientTest2 extends TestBase {

	/*
	 * @author - Trivetim
	 */
	@Test(priority = 1, groups = { "smoke", "regression" })
	@TestCaseDetails(testCaseID = "TestCaseID : 185621640", testCaseTitle = "Verify client is added successfully", category = {
			TestCaseCategoryType.SMOKE, TestCaseCategoryType.REGRESSION })
	public void Verifyclientisaddedsuccessfully_185621640() {

		// Login for Tuft platform
		boolean flagForSuccessfulLogin = homePage.loginOnTuftPlatform(getEmailAddress(), getPasswordAddress());
		reportStep(flagForSuccessfulLogin, "pass", "Login Successfully.", "Login failed.");

		// Test Step : Click on Clients Management menu
		boolean clientManagementPageFlag = clientPage.goToClientManagement();
		reportStep(clientManagementPageFlag, "info", "On Clients management page.", "Clients management is not opened.");

		// Test Step : Click on Add new client button.
		//clientPage.goToAddClientPage();
		boolean AddClientPageFlag = clientPage.goToAddClientPage();
		reportStep(AddClientPageFlag, "info", "On Create Client Page.", "Create Client is not opened.");
		
		// Test Step : Enter client informations and click on submit button.
		boolean clientCreationFlag =clientPage.createClient(AddClient.John.toString(),AddClient.Rovek.toString(),AddClient.MobileNumber.value); 

		reportStep(clientCreationFlag, "pass", "Client is created successfully","Client is not created successfully");
	}

	/*
	 * @author - Trivetim
	 */
	@Test(priority = 1, groups = { "smoke", "regression" })
	@TestCaseDetails(testCaseID = "TestCaseID : 185621641", testCaseTitle = "Verify client is added successfully", category = {
			TestCaseCategoryType.SMOKE, TestCaseCategoryType.REGRESSION })
	public void Verifyclientisaddedsuccessfully_185621641() {

		// Login for Tuft platform
		boolean flagForSuccessfulLogin = homePage.loginOnTuftPlatform(getEmailAddress(), getPasswordAddress());
		reportStep(flagForSuccessfulLogin, "pass", "Login Successfully.", "Login failed.");

		// Test Step : Click on Clients Management menu
		boolean clientManagementPageFlag = clientPage.goToClientManagement();
		reportStep(clientManagementPageFlag, "info", "On Clients management page.", "Clients management is not opened.");

		// Test Step : Click on Add new client button.
		//clientPage.goToAddClientPage();
		boolean createClientPageFlag = clientPage.goToAddClientPage();
		reportStep(createClientPageFlag, "info", "On Create Client Page.", "Create Client is not opened.");
		
		// Test Step : Enter client informations and click on submit button.
		boolean clientCreationFlag =clientPage.createClient(AddClient.John.toString(),AddClient.Rovek.toString(),AddClient.MobileNumber.value); 

		reportStep(clientCreationFlag, "pass", "Client is created successfully","Client is not created successfully");
	}
}