package com.qa.generic;

import java.io.FileInputStream;
import java.util.Hashtable;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest extends Pojo {

	private Properties prop;
	private String browser = "";
	Hashtable<String, Hashtable<String, String>> testDataTable;
	public static Hashtable<String, String> testDataForTest;

	public void initializeWebEnviornment(final String testDataFilePath) {

		loadConfig();
		loadDataProvider(testDataFilePath);
		setObjWrapperFunction(new WrapperFunction(this));
		browser = this.getObjProp().getProperty("browser");
		if (browser.trim().equalsIgnoreCase("chrome")) {
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--disable-notification");
			options.addArguments("--disable-infobars");
			options.addArguments("--start-maximized");
			options.addArguments("incognito");
			
			WebDriverManager.chromedriver().version("89").setup();
			
//			System.setProperty("webdriver.chrome.driver",
//					System.getProperty("user.dir") + "\\src\\main\\resources\\Drivers\\chromedriver.exe");
			this.setDriver(new ChromeDriver(options));
//			getDriver().manage().window().maximize();

		}

		setObjWebDriverWait(new WebDriverWait(getDriver(), Integer.parseInt(getObjProp().getProperty("explicitwait"))));
		getDriver().get(getObjProp().getProperty("url"));
		getDriver().manage().timeouts().implicitlyWait(Integer.parseInt(getObjProp().getProperty("implicitwait")),
				TimeUnit.SECONDS);
		getDriver().manage().timeouts().pageLoadTimeout(Integer.parseInt(getObjProp().getProperty("pageloadtimeout")),
				TimeUnit.SECONDS);

	}

	public void loadConfig() {

		setObjProp(new Properties());
		try {
			getObjProp().load(new FileInputStream(
					String.valueOf(System.getProperty("user.dir")) + "\\src\\main\\resources\\config.properties"));
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void tearDown() {
System.out.println("Inside teardown");
		if (getDriver() != null) {
			getDriver().manage().deleteAllCookies();
			getDriver().quit();
		}
	}

	public void loadDataProvider(String testDataFilePath) {

		if (!testDataFilePath.equals("")) {

			testDataFilePath = String.valueOf(System.getProperty("user.dir")) + "/src/test/resources/testdata/"
					+ testDataFilePath + ".xlsx";
			final DataPool objDataPool = new DataPool();
			this.testDataTable = objDataPool.loadTestData(testDataFilePath);
		}
	}

	public void loadTestData(final String TCID) {

		this.testDataForTest = this.testDataTable.get(TCID);
		System.out.println("testDataForTest------->" + this.testDataForTest);
		this.setDataPoolHashTable(this.testDataForTest);

	}

	public String dpString(String columnHeader) {
		Hashtable<String, String> dataPoolHashTable = getDataPoolHashTable();
		try {
			if (dataPoolHashTable.get(columnHeader) == null)
				return "";
			else {
				System.out.println("I found, Key: " + columnHeader + " Value : " + dataPoolHashTable.get(columnHeader));
				return dataPoolHashTable.get(columnHeader);
			}
		} catch (Exception exception) {
			exception.printStackTrace();
			return "";
		}

	}

}
