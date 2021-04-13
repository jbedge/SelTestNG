package com.qa.generic;

import java.io.File;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotSelectableException;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.InvalidSelectorException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.NoSuchFrameException;
import org.openqa.selenium.NoSuchSessionException;
import org.openqa.selenium.NoSuchWindowException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.UnhandledAlertException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.testng.Assert;
import com.google.common.base.Function;

public class WrapperFunction {

	private Pojo objPojo;
	private String method;

	public WrapperFunction(Pojo objPojo) {
		this.objPojo = objPojo;
	}

	public void waitForElementPresent(By locator) {
		objPojo.getObjWebDriverWait().until(ExpectedConditions.presenceOfElementLocated(locator));
	}

	public void waitForWebElementToBeClickable(WebElement we) {
		objPojo.getObjWebDriverWait().until(ExpectedConditions.elementToBeClickable(we));
	}

	public void waitForDOMToBeLoaded(By locator) {

		objPojo.getObjWebDriverWait().until(ExpectedConditions.presenceOfElementLocated(locator));
	}

	public WebElement expandShadowRootElement(By locator) {
		WebElement ele = (WebElement) ((JavascriptExecutor) objPojo.getDriver())
				.executeScript("return arguments[0].shadowRoot", getElementFluent(locator));
		return ele;
	}

	public WebElement expandShadowRootElement(WebElement we) {
		waitFor(2);
		WebElement ele = (WebElement) ((JavascriptExecutor) objPojo.getDriver())
				.executeScript("return arguments[0].shadowRoot", we);
		return ele;
	}

	public boolean click(By locator) {
		try {
			waitForElementPresent(locator);
			objPojo.getDriver().findElement(locator).click();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

	}

	public boolean click(WebElement we) {
		try {
			waitForWebElementToBeClickable(we);
			we.click();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

	}

	public void logReporter(String step, boolean flag) {

		if (flag) {
			System.out.println("Step Description ==>> " + step);
			Assert.assertTrue(true);
		} else {
			System.out.println("Failed at ==>> " + step);
			getScreenShot();
			Assert.assertTrue(false);
		}

	}

	public void getScreenShot() {
		TakesScreenshot scrShot = ((TakesScreenshot) objPojo.getDriver());
		// Call getScreenshotAs method to create image file
		File srcFile = scrShot.getScreenshotAs(OutputType.FILE);
		String methodName = getDateInSpecifiedFormat("dd_MMM_yyyy_HH_mm_ss");
		File destFile = new File(System.getProperty("user.dir") + "\\target\\Screenshots\\" + methodName + ".png");
		try {
			FileUtils.moveFile(srcFile, destFile);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public String getDateInSpecifiedFormat(String dateFormat) {
		String current_date = "";
		Date today = Calendar.getInstance().getTime();
		SimpleDateFormat formatter = new SimpleDateFormat(dateFormat);
		current_date = formatter.format(today);
		return current_date;
	}

	public WebElement getElementFluent(final By locator) {
		Wait<WebDriver> wait = new FluentWait<WebDriver>(objPojo.getDriver()).withTimeout(Duration.ofSeconds(40))
				.pollingEvery(Duration.ofSeconds(1)).ignoring(NoSuchElementException.class);
		WebElement webElement = wait.until(new Function<WebDriver, WebElement>() {
			public WebElement apply(WebDriver driver) {
				return objPojo.getDriver().findElement(locator);
			}
		});
		return webElement;
	}

	public void waitFor(int seconds) {

		try {
			Thread.sleep(seconds * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

	public WebElement nestedShadow(WebElement we, By locator) {
		WebElement webelement = objPojo.getObjWrapperFunction().expandShadowRootElement(we);
		WebElement returnele = webelement.findElement(locator);
		return returnele;

	}

	public boolean setText(WebElement we, String str) {

		try {
			we.clear();
			we.sendKeys(str);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public String getText(WebElement webElement, String textBy) {
		String strText = "";
		try {
			
			if (textBy.trim().equalsIgnoreCase("value")) {
				strText = webElement.getAttribute("value");

			} else {
				strText = webElement.getText();
			}
			return strText;
		} catch (Exception exception) {
			exception.printStackTrace();
			return "";
		}
	}
	
	
	public boolean selectDropDownOption(WebElement webElement, String option, String... selectType) {
		try {
			Select sltDropDown = new Select(webElement);
			if (selectType.length > 0 && !selectType[0].equals("")) {
				if (selectType[0].equalsIgnoreCase("Value"))
					sltDropDown.selectByValue(option);
				else if (selectType[0].equalsIgnoreCase("Text"))
					sltDropDown.selectByVisibleText(option);
				else if (selectType[0].equalsIgnoreCase("Index"))
					sltDropDown.selectByIndex(Integer.parseInt(option));
				return true;
			} else {
				List<WebElement> options = sltDropDown.getOptions();
				boolean blnOptionAvailable = false;
				int iIndex = 0;
				for (WebElement weOptions : options) {
					if (weOptions.getText().trim().equals(option)) {
						sltDropDown.selectByIndex(iIndex);
						blnOptionAvailable = true;
						break;
					} else
						iIndex++;
				}
				return blnOptionAvailable;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	
	public boolean checkElementDisplayed(WebElement we) {
		try {
			return we.isDisplayed();
		} catch (Exception exception) {
			commonExceptions1(exception);
			return false;
		}
	}

	// Bedge 08-08-2019
	public String getViewMethodNameInFailedTestScripts() {
		try {
			StackTraceElement[] viewMethodName = Thread.currentThread().getStackTrace();
			for (int k = 0; k < viewMethodName.length - 1; k++) {
				method = Thread.currentThread().getStackTrace()[k].getMethodName();
				if (method.startsWith("RMID")) {
					method = Thread.currentThread().getStackTrace()[k - 1].getMethodName();
					logReporter(method, true);
					return method;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "Failed method not found.";
	}

	// By Vishnu Sajeevan and Swanand Mahurkar 25-07-2019 Updated By : Jagadeesh
	// Bedge 08-08-2019
	public String getViewMethodNameInFailedTestScripts1() {
		try {
			StackTraceElement[] viewMethodName = Thread.currentThread().getStackTrace();
			for (int k = 0; k < viewMethodName.length - 1; k++) {
				String method = Thread.currentThread().getStackTrace()[k].getMethodName();
				if (method.startsWith("RMID")) {
					method = Thread.currentThread().getStackTrace()[k - 1].getMethodName();
					return method;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "Failed method not found.";
	}
	
	// Exceptions For Return Type Void By Jagadeesh Bedge 18-07-2019 Updated By
	// : Jagadeesh Bedge 26-07-2019
	public void commonExceptions1(Exception e) {
		try {
			throw e;
		} catch (Exception exception) {
			exception.printStackTrace();
			return;
		}
	}
	
}

