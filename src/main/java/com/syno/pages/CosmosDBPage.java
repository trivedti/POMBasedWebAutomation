package com.syno.pages;

import java.io.IOException;

import org.apache.logging.log4j.Logger;

import com.syno.utilities.AzureUtils;
import com.syno.utilities.PropertyFileOperations;

import org.apache.logging.log4j.LogManager;

public class CosmosDBPage {
	AzureUtils azureUtils;
	private Logger log = LogManager.getLogger(this.getClass());
	PropertyFileOperations locators;
	private static CosmosDBPage cosmosDBPage;

	private CosmosDBPage() {
	}
	
	/*
	 * To get CosmosDBPage instance
	 * 
	 * @return CosmosDBPage
	 */
	public static CosmosDBPage getCosmosDBPage() {
		if (cosmosDBPage == null)
			cosmosDBPage = new CosmosDBPage();
		return cosmosDBPage;
	}
	
	
}
