package com.qa.pages;

import java.util.Hashtable;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.qa.generic.Pojo;

public class OlaHomePage {

	private Pojo objPojo;
	private int minETA = 1;
	private int minETAIndex = 0;
	private String minCabPrice;
	private boolean blnReturnStatus = false;

	public OlaHomePage(Pojo objPojo) {
		this.objPojo = objPojo;
	}

	By tagOlaApp = By.tagName("ola-app");
	By tagIronPages = By.cssSelector("iron-pages");
	By tagOlaHome = By.cssSelector("ola-home");
	By tagOlaLocPermission = By.cssSelector("ola-loc-permission");
	By btnContinueToNextStep = By.cssSelector("button.confirm-btn");
	By tagOlaNotification = By.cssSelector("ola-notification");
	By btnContinue = By.cssSelector("button.confirm-btn");
	By htmlBody = By.xpath("//body[@service='p2p']");
	By tagOlaLocationInput = By.cssSelector("ola-location-input");
	By lblFrom = By.cssSelector("div[class='right h-full text value']");
	By tagOlaModal = By.cssSelector("ola-modal");
	By tagOlaLocation = By.cssSelector("ola-location");
	By inpFrom = By.cssSelector("input#addressInput");
	By lblLocation = By.cssSelector("div[class='card bg-white'] div[class='name']");
	By tagOlaHomeLocal = By.cssSelector("ola-home-local");
	By inpTo = By.cssSelector("div[class='right h-full text placeholder']");
	By tagOlaLaterOptions = By.cssSelector("ola-later-options");
	By drpWhenOption = By.cssSelector("select#rideOptPicker");
	By tagOlaCabs = By.cssSelector("ola-cabs");
	By tagOlaConfirmRideP2p = By.cssSelector("ola-confirm-ride-p2p");
	By btnRideConfirmContinue = By.cssSelector("button[class='nxt-btn-active ola-ripple next-btn-pos']");
	By lblPriceEstimation = By.cssSelector("div[class='price-estimate']");

	public void clickOlaHomePageContinueToNextStepButton() {

		WebElement olaHome = objPojo.getObjWrapperFunction().nestedShadow(objPojo.getDriver().findElement(tagOlaApp),
				tagOlaHome);
		WebElement olaLocPermission = objPojo.getObjWrapperFunction().nestedShadow(olaHome, tagOlaLocPermission);
		WebElement shadowLocPermission = objPojo.getObjWrapperFunction().expandShadowRootElement(olaLocPermission);
		objPojo.getObjWrapperFunction().logReporter("Click Ola Home Page Continue To Next Step Button ",
				objPojo.getObjWrapperFunction().click(shadowLocPermission.findElement(btnContinueToNextStep)));
		
	}

	public void clickOlaHomePageAllowPushNotificationContinue() {

		WebElement olaNotification = objPojo.getObjWrapperFunction()
				.nestedShadow(objPojo.getDriver().findElement(tagOlaApp), tagOlaNotification);
		WebElement shadowOlaNotification = objPojo.getObjWrapperFunction().expandShadowRootElement(olaNotification);
		objPojo.getObjWrapperFunction().logReporter("Click Ola Home Page Allow Push Notification Continue",
				objPojo.getObjWrapperFunction().click(shadowOlaNotification.findElement(btnContinue)));

	}

	public void verifyOlaHomePageIsDisplayed() {
		objPojo.getObjWrapperFunction().waitForDOMToBeLoaded(htmlBody);
	}

	public void setOlaHomePageFrom(String str) {
		WebElement olaHome = objPojo.getObjWrapperFunction().nestedShadow(objPojo.getDriver().findElement(tagOlaApp),
				tagOlaHome);
		WebElement olaLocInput = objPojo.getObjWrapperFunction().nestedShadow(olaHome, tagOlaLocationInput);
		WebElement shadowolaLocInput = objPojo.getObjWrapperFunction().expandShadowRootElement(olaLocInput);
		objPojo.getObjWrapperFunction().logReporter("Click on Ola Home Page From",
				objPojo.getObjWrapperFunction().click(shadowolaLocInput.findElement(lblFrom)));
		WebElement olaModal = objPojo.getObjWrapperFunction().nestedShadow(objPojo.getDriver().findElement(tagOlaApp),
				tagOlaModal);
		WebElement olaLocation = objPojo.getObjWrapperFunction().nestedShadow(olaModal, tagOlaLocation);
		WebElement shadowOlaLocation = objPojo.getObjWrapperFunction().expandShadowRootElement(olaLocation);
		objPojo.getObjWrapperFunction().logReporter("Set an Ola Home Page From",
				objPojo.getObjWrapperFunction().setText(shadowOlaLocation.findElement(inpFrom), str));
		objPojo.getObjWrapperFunction().logReporter("Set an Ola Home Page From",
				objPojo.getObjWrapperFunction().click(shadowOlaLocation.findElement(lblLocation)));
	}

	public void setOlaHomePageTo(String str) {
		WebElement olaHome = objPojo.getObjWrapperFunction().nestedShadow(objPojo.getDriver().findElement(tagOlaApp),
				tagOlaHome);
		WebElement olaHomeLocal = objPojo.getObjWrapperFunction().nestedShadow(olaHome, tagOlaHomeLocal);
		WebElement olaLocationInput = objPojo.getObjWrapperFunction().nestedShadow(olaHomeLocal, tagOlaLocationInput);
		WebElement shadowOlaLocationInput = objPojo.getObjWrapperFunction().expandShadowRootElement(olaLocationInput);
		objPojo.getObjWrapperFunction().logReporter("Click on Ola Home Page From",
				objPojo.getObjWrapperFunction().click(shadowOlaLocationInput.findElement(inpTo)));

		WebElement olaModal = objPojo.getObjWrapperFunction().nestedShadow(objPojo.getDriver().findElement(tagOlaApp),
				tagOlaModal);
		WebElement olaLocation = objPojo.getObjWrapperFunction().nestedShadow(olaModal, tagOlaLocation);
		WebElement shadowOlaLocation = objPojo.getObjWrapperFunction().expandShadowRootElement(olaLocation);
		objPojo.getObjWrapperFunction().logReporter("Set an Ola Home Page To",
				objPojo.getObjWrapperFunction().setText(shadowOlaLocation.findElement(inpFrom), str));
		objPojo.getObjWrapperFunction().logReporter("Set an Ola Home Page To",
				objPojo.getObjWrapperFunction().click(shadowOlaLocation.findElement(lblLocation)));

	}

	public void selectOlaHomePageWhenDropDown(String str) {
		WebElement olaHome = objPojo.getObjWrapperFunction().nestedShadow(objPojo.getDriver().findElement(tagOlaApp),
				tagOlaHome);
		WebElement olaHomeLocal = objPojo.getObjWrapperFunction().nestedShadow(olaHome, tagOlaHomeLocal);
		WebElement olaLaterOptions = objPojo.getObjWrapperFunction().nestedShadow(olaHomeLocal, tagOlaLaterOptions);
		WebElement shadowolaLaterOptions = objPojo.getObjWrapperFunction().expandShadowRootElement(olaLaterOptions);
		objPojo.getObjWrapperFunction().logReporter("Selected Options in When", objPojo.getObjWrapperFunction()
				.selectDropDownOption(shadowolaLaterOptions.findElement(drpWhenOption), str, "Text"));

	}

	public void verifyCabsAndClickOnLowestETA() {

		WebElement olaHome = objPojo.getObjWrapperFunction().nestedShadow(objPojo.getDriver().findElement(tagOlaApp),
				tagOlaHome);
		WebElement olaHomeLocal = objPojo.getObjWrapperFunction().nestedShadow(olaHome, tagOlaHomeLocal);
		WebElement olaCabs = objPojo.getObjWrapperFunction().nestedShadow(olaHomeLocal, tagOlaCabs);
		WebElement shadowOlaCabs = objPojo.getObjWrapperFunction().expandShadowRootElement(olaCabs);
		int i = 0;
		List<WebElement> lst = shadowOlaCabs.findElements(By.cssSelector("div[class='row cab-row ptr ola-ripple']"));
		minETA = Integer.parseInt(
				lst.get(0).findElement(By.cssSelector("span[class='av-time']")).getText().replaceAll("[^0-9]", ""));
		for (WebElement we : lst) {
			String cabNameDetails = we.findElement(By.cssSelector("div[class='text value cab-name']")).getText();
			String cabName = cabNameDetails.replaceAll("[^A-Za-z]+", "");
			System.out.print(cabName + ", ");
			String cabTimeDetails = we.findElement(By.cssSelector("span[class='av-time']")).getText();
			System.out.print(cabTimeDetails + " ,");
			if (!cabTimeDetails.equals("")) {
				int cabTime = Integer.parseInt(cabTimeDetails.replaceAll("[^0-9]", ""));
				if (minETA > cabTime) {
					minETA = cabTime;
					minETAIndex = i;
					
				}
			}
			String cabPrice = we.findElement(By.cssSelector("span[class='text value price'] > span")).getText();
			System.out.println(cabPrice);
			i++;
		}
		minCabPrice= lst.get(minETAIndex).findElement(By.cssSelector("span[class='text value price'] > span")).getText();
		objPojo.getObjWrapperFunction().logReporter("Click on Lowest ETA",
				objPojo.getObjWrapperFunction().click(lst.get(minETAIndex)));
	}

	public void verifyOlaHomePagePickUpAndDrop(String strpickup, String drop) {
		WebElement olaModal = objPojo.getObjWrapperFunction().nestedShadow(objPojo.getDriver().findElement(tagOlaApp),
				tagOlaModal);
		WebElement olaConfirmRideP2p = objPojo.getObjWrapperFunction().nestedShadow(olaModal, tagOlaConfirmRideP2p);
		WebElement shadowOlaConfirmRideP2p = objPojo.getObjWrapperFunction().expandShadowRootElement(olaConfirmRideP2p);
		List<WebElement> lst = shadowOlaConfirmRideP2p.findElements(By.cssSelector("div[class='row-sm se-false']"));
		String pickUp = lst.get(0).getText();
		String dropULoc = lst.get(1).getText();
		objPojo.getObjWrapperFunction().logReporter("verify Pick Up", pickUp.contains(strpickup));
		objPojo.getObjWrapperFunction().logReporter("verify Drop Location", dropULoc.contains(drop));

	}

	public void verifyOlaHomePagePrice() {
		WebElement olaModal = objPojo.getObjWrapperFunction().nestedShadow(objPojo.getDriver().findElement(tagOlaApp),
				tagOlaModal);
		WebElement olaConfirmRideP2p = objPojo.getObjWrapperFunction().nestedShadow(olaModal, tagOlaConfirmRideP2p);
		WebElement shadowOlaConfirmRideP2p = objPojo.getObjWrapperFunction().expandShadowRootElement(olaConfirmRideP2p);
		String priceEstimation = shadowOlaConfirmRideP2p.findElement(lblPriceEstimation).getText();
		objPojo.getObjWrapperFunction().logReporter("verify Pick Up", priceEstimation.contains(minCabPrice));

	}

	public void clickOlaHomePageContinue() {
		WebElement olaModal = objPojo.getObjWrapperFunction().nestedShadow(objPojo.getDriver().findElement(tagOlaApp),
				tagOlaModal);
		WebElement olaConfirmRideP2p = objPojo.getObjWrapperFunction().nestedShadow(olaModal, tagOlaConfirmRideP2p);
		WebElement shadowOlaConfirmRideP2p = objPojo.getObjWrapperFunction().expandShadowRootElement(olaConfirmRideP2p);

		objPojo.getObjWrapperFunction().logReporter("click Ola Home Page Ride Continue",
				objPojo.getObjWrapperFunction().click(shadowOlaConfirmRideP2p.findElement(btnRideConfirmContinue)));
	}

}
