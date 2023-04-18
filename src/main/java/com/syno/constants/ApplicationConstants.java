package com.syno.constants;

import java.util.Base64;

public abstract class ApplicationConstants {

	public static String URL = "https://uat.tuftapp.com/authentication/login";
	public static String BROWSER = "chrome";
	public static String ENVIRONMENT = "qa";

	public static final int EXP_WAIT = 30;
	
	//public static final String EMAIL = new String(Base64.getDecoder().decode("-m[bʊfj)\"));
	public static final String EMAIL="test22.2multi@yopmail.com";
	
	//public static final String PASSWORD =new String(Base64.getDecoder().decode("QyR8QHV0bzIwMjI="));
	public static final String PASSWORD ="12345678";
	
	/* To encode any String use below code
	public static void main(String[] args) {
		String originalInput = "yourName.surname@synoverge.com";
		String encryptedData = Base64.getEncoder().encodeToString(originalInput.getBytes());
		System.out.println("encryptedData: "+encryptedData);
	}*/
	
	//Azure storage constants
	public static final String STORAGE_CONNECTION_STRING = 
		"DefaultEndpointsProtocol=https;" +
		"AccountName=accountname;" +
		"AccountKey=abc9BcdaC6fxA+6+DNZGJszXAv+3bOBk/KTON+cSe22oPvaIKIPf5VAysE1cFbvqb95y0/C00V511X0NSDIw==";
	public static final String CONTAINER_NAME="QAevents";
	
	//Azure Cosmos DB constants
	final public static String COSMODB_DATABASE_NAME = "syno_qa";
	final public static String COSMODB_URI = "mongodb://syno-test-qa-db:tZzd1gGkM382G31yZuqenYRwEoxXayA";
	final public static String COSMODB_COLLECTION_NAME = "QACollection";
	
	
}
