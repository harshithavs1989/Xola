package com.xolo.assignment.base;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import com.xolo.assignment.screenshot.WebScreenShot;

public class Base {
	public WebDriver driver = null;

	@Parameters("browserName")

	@BeforeClass()
	public void setup(@Optional("firefox") String browserName) {
		if (browserName.equalsIgnoreCase("chrome")) {
			String path = System.getProperty("user.dir");
			System.setProperty("webDriver.chrome.driver", path + "/chromedriver.exe");
			driver = new ChromeDriver();
		} else if (browserName.equalsIgnoreCase("firefox")) {
			FirefoxOptions options = new FirefoxOptions();
			options.setCapability("marionette", false);
			String path = System.getProperty("user.dir");
			System.setProperty("webdriver.gecko.driver", path + "/geckodriver.exe");
			driver = new FirefoxDriver(options);
		}

		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get(
				"https://sandbox-checkout.xola.com/index.html#seller/58e235b107876cdd1f8b45e2/experiences/58e2371107876cdd1f8b45e5");

	}

	@AfterMethod
	public void tearDown(ITestResult result) {
		new WebScreenShot().takeWebScreenShot(driver, result);
	}

	@AfterClass
	public void cleanUp() {
		driver.quit();
	}

}
