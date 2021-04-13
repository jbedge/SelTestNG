package com.qa.generic;

import java.util.Hashtable;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Pojo {
	
	private WebDriver driver;
	private Properties objProp;
	private WebDriverWait objWebDriverWait;
	private WrapperFunction objWrapperFunction;
	private Hashtable<String, String> dataPoolHashTable;
	
	public void setDriver(WebDriver driver) {
		this.driver=driver;
		
	}
	
	public WebDriver getDriver() {
		return driver;
	}

	public Properties getObjProp() {
		return objProp;
	}

	public void setObjProp(Properties objProp) {
		this.objProp = objProp;
	}

	public WebDriverWait getObjWebDriverWait() {
		return objWebDriverWait;
	}

	public void setObjWebDriverWait(WebDriverWait objWebDriverWait) {
		this.objWebDriverWait = objWebDriverWait;
	}

	public WrapperFunction getObjWrapperFunction() {
		return objWrapperFunction;
	}

	public void setObjWrapperFunction(WrapperFunction objWrapperFunction) {
		this.objWrapperFunction = objWrapperFunction;
	}

	public void setDataPoolHashTable(Hashtable<String, String> dataPoolHashTable) {
		this.dataPoolHashTable = dataPoolHashTable;
	}

	public Hashtable<String, String> getDataPoolHashTable() {
		return dataPoolHashTable;
	}

	
	
	

}
