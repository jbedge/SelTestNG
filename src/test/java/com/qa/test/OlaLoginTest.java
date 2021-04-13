package com.qa.test;


import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.qa.generic.BaseTest;
import com.qa.pages.OlaHomePage;

public class OlaLoginTest extends BaseTest{
	
	String patientName = "";
	private OlaHomePage objOlaHomePage;
	
	
	
	public void initializePages() {
		objOlaHomePage = new OlaHomePage(this);
		
	}
	
	
	@BeforeMethod
	public void beforeMethod() {
		getDriver().navigate().refresh();
	}
	
	@BeforeClass
	public void initializeEnviornment(){
		initializeWebEnviornment("excel/SW");
		initializePages();
		
	}
	
	@AfterClass
	public void tearAllDown() {
		tearDown();
		objOlaHomePage = null;
	}
	
	@Test
	public void TC_001()  {
		loadTestData("TC_001");
		objOlaHomePage.verifyOlaHomePageIsDisplayed();
		objOlaHomePage.clickOlaHomePageContinueToNextStepButton();
		objOlaHomePage.clickOlaHomePageAllowPushNotificationContinue();
		objOlaHomePage.setOlaHomePageFrom(dpString("pickUp"));
		objOlaHomePage.setOlaHomePageTo(dpString("Drop"));
		objOlaHomePage.selectOlaHomePageWhenDropDown(dpString("SelectWhen"));
		objOlaHomePage.verifyCabsAndClickOnLowestETA();
		objOlaHomePage.verifyOlaHomePagePickUpAndDrop(dpString("VerifyPickUp"),dpString("VerifyDrop"));
		objOlaHomePage.verifyOlaHomePagePrice();
		objOlaHomePage.clickOlaHomePageContinue();
	}
	
	@Test
	public void TC_002()  {
		loadTestData("TC_002");
		objOlaHomePage.verifyOlaHomePageIsDisplayed();
		objOlaHomePage.clickOlaHomePageContinueToNextStepButton();
		objOlaHomePage.clickOlaHomePageAllowPushNotificationContinue();
		objOlaHomePage.setOlaHomePageFrom(dpString("pickUp"));
		objOlaHomePage.setOlaHomePageTo(dpString("Drop"));
		objOlaHomePage.selectOlaHomePageWhenDropDown(dpString("SelectWhen"));
		objOlaHomePage.verifyCabsAndClickOnLowestETA();
		objOlaHomePage.verifyOlaHomePagePickUpAndDrop(dpString("VerifyPickUp"),dpString("VerifyDrop"));
		objOlaHomePage.verifyOlaHomePagePrice();
		objOlaHomePage.clickOlaHomePageContinue();
	}
}
