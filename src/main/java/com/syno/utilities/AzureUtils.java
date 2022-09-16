package com.syno.utilities;

import static io.restassured.RestAssured.given;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.bson.Document;

import org.json.JSONObject;
import org.json.simple.JSONArray;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import com.syno.constants.ApplicationConstants;
import com.syno.constants.FilePaths;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class AzureUtils {

	public static Properties prop = new Properties();
	private Logger log = LogManager.getLogger(this.getClass());
	MongoClient mongoClient;
	MongoDatabase database;
	MongoCollection<Document> collection;

	public AzureUtils() throws IOException {
		try {
			FileInputStream fis = new FileInputStream(FilePaths.CONFIG_FILE);
			prop.load(fis);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * This method will return the inner unique ID of Testcase.
	 * 
	 * @param testCaseID Test case ID visible on Azure UI
	 * @return Unique inner linked ID
	 * @throws Exception
	 */
	public int getInnerUniqueLinkedTestCaseIDFromAzure(int testCaseID) throws Exception {
		Map<String, String> param = new HashMap<>();
		param.put("api-version", "5.1");
		String path = "/Microsoft%20Product%20Server%20and%20Technologies/Synoverge%20QAL/_apis/testplan/Plans/" + prop.getProperty("testPlanID")
				+ "/Suites/" + prop.getProperty("testSuiteID") + "/TestCase";
		RestAssured.baseURI = prop.getProperty("url") + path;
		Response response = given().auth().preemptive().basic(prop.getProperty("userID"), prop.getProperty("password"))
				.header("content-type", "application/json").queryParams(param).get().then().extract().response();
		JsonPath jsonPath = response.jsonPath();
		int length = jsonPath.getInt("value.size()");
		int expectedPATCID = 0;
		for (int index = 0; index < length; index++) {
			int actualtcID = jsonPath.getInt("value[" + index + "].workItem.id");
			if (actualtcID == testCaseID) {
				expectedPATCID = jsonPath.getInt("value[" + index + "].pointAssignments[0].id");
			}
		}
		return expectedPATCID;
	}

	/**
	 * This method will update the test case status as pass or fail on Azure
	 * TestPlan.
	 * 
	 * @param linkedTestCaseID        Inner Test case ID
	 * @param testCasePassOrFailValue This value can be 2=pass or 3=fail for test
	 *                                case status
	 */
	public void updateTestCaseStatusOnAzure(int linkedTestCaseID, int testCasePassOrFailValue) {
		Map<String, String> param = new HashMap<>();
		param.put("api-version", "5.1");
		JSONObject jsonObject = new JSONObject();
		JSONArray requestBody = new JSONArray();
		JSONObject jsonResultObject = new JSONObject();
		jsonObject.put("id", linkedTestCaseID);
		jsonResultObject.put("outcome", testCasePassOrFailValue);
		jsonObject.put("results", jsonResultObject);
		requestBody.add(jsonObject);
		String path = "/Microsoft%20Product%20Server%20and%20Technologies/Synoverge%20QAL/_apis/testplan/Plans/" + prop.getProperty("testPlanID")
				+ "/Suites/" + prop.getProperty("testSuiteID") + "/TestPoint";
		RestAssured.baseURI = prop.getProperty("url") + path;
		Response response = given().auth().preemptive().basic(prop.getProperty("userID"), prop.getProperty("password"))
				.header("content-type", "application/json").queryParams(param).body(requestBody.toJSONString()).patch()
				.then().extract().response();
		log.info("STEP : Update TestCase statusCode is: " + response.statusCode());
	}


	/*
	 * To establish cosmosDB connection.
	 */
	public void establishCosmoDBConnection() {
		mongoClient = new MongoClient(new MongoClientURI(ApplicationConstants.COSMODB_URI));
		database = mongoClient.getDatabase(ApplicationConstants.COSMODB_DATABASE_NAME);
		collection = database.getCollection(ApplicationConstants.COSMODB_COLLECTION_NAME);
	}

	
}
