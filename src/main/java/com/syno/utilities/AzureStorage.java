package com.syno.utilities;

import java.io.File;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import com.google.common.collect.Iterables;
import com.microsoft.azure.storage.CloudStorageAccount;
import com.microsoft.azure.storage.StorageException;
import com.microsoft.azure.storage.blob.CloudBlob;
import com.microsoft.azure.storage.blob.CloudBlobClient;
import com.microsoft.azure.storage.blob.CloudBlobContainer;
import com.microsoft.azure.storage.blob.CloudBlobDirectory;
import com.microsoft.azure.storage.blob.ListBlobItem;
import com.syno.constants.ApplicationConstants;
import com.syno.constants.FilePaths;

public class AzureStorage {

	private Logger log = LogManager.getLogger(this.getClass());
	File downloadedFile = null;
	CloudStorageAccount storageAccount;
	CloudBlobClient blobClient = null;
	CloudBlobContainer container = null;

	
}
