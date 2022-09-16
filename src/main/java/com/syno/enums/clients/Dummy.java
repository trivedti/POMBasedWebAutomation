package com.syno.enums.clients;

public enum Dummy {
	
	QA_mp4("QA.mp4"),
	QA_Test("QA_Test.txt");
	
	public final String value;
	
	private Dummy(String value){
		this.value = value;
	}
}
