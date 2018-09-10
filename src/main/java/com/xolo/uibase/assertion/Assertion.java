package com.xolo.uibase.assertion;

import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.testng.asserts.SoftAssert;


/**
 * This class is used for asserting. We have used soft assert
 * @author vinay.prasanna
 *
 */
public class Assertion
{
	static Logger ESS_LOGS = Logger.getLogger("devpinoyLogger");
	
	private SoftAssert sa = new SoftAssert();
	
	/**
	 * This method is used to assert a string value
	 * @param actual
	 * @param expected
	 * @param message
	 */
	public  void assertString(String actual, String expected, String message)
	{
		sa.assertEquals(actual.trim(), expected.trim(), message);
		ESS_LOGS.debug("The actual " + actual + " and expected " + expected + " matches");
	}
	
	/**
	 * This is used to assert if web element exists or not
	 * @param element
	 * @param message
	 */
	public void assertWebElementExists(WebDriver driver, WebElement element, int timeout, String message)
	{
		FluentWait<WebDriver> fluent = new FluentWait<WebDriver>(driver);
		fluent.ignoring(ElementNotVisibleException.class);
		fluent.withTimeout(10, TimeUnit.SECONDS);
		fluent.pollingEvery(2, TimeUnit.SECONDS);
		fluent.until(ExpectedConditions.visibilityOf(element));
		
		boolean elementStatus = false;
		if(element.isDisplayed())
		{
			elementStatus = true;
		}
		
		sa.assertEquals(elementStatus, true, message);
		ESS_LOGS.debug("The element " + element + " is dispalyed");
		
	}
	
	/**
	 * This is used to assert true condition
	 * @param expected
	 * @param message
	 */
	public  void assertTrue(boolean expected, String message)
	{
		sa.assertTrue(expected, message);
		ESS_LOGS.debug("The expected condition matches with the actual condition");
	}
	
	/**
	 * This is used to assert false condition
	 * @param expected
	 * @param message
	 */
	public  void assertFalse(boolean expected, String message)
	{
		sa.assertFalse(expected, message);
		ESS_LOGS.debug("The expected condition matches with the actual condition");
	}
	
	/**
	 * This is used for asserting intiger values
	 * @param actual
	 * @param expected
	 * @param message
	 */
	public  void assertInt(int actual, int expected, String message)
	{
		sa.assertEquals(actual, expected, message);
		ESS_LOGS.debug("The expected integer '"+ expected +"' matches with the '"+ actual + "' actual integer");
		
	}
	
	public void assertElementClickable(WebDriver driver, WebElement element, int timeout, String message)      
	{
		boolean elementIsClickable = false;
		try
		{
			FluentWait<WebDriver> fluent = new FluentWait<WebDriver>(driver);
			fluent.ignoring(StaleElementReferenceException.class);
			fluent.withTimeout(timeout, TimeUnit.SECONDS);
			fluent.pollingEvery(2, TimeUnit.SECONDS);
			fluent.until(ExpectedConditions.elementToBeClickable(element));
			elementIsClickable = true;
		
		}
		catch (Exception e)
		{}
		
		sa.assertEquals(elementIsClickable, true, message);
		ESS_LOGS.debug("The element '"+ element + " is not clickable");
	}
	
	/**
	 * This is used to perform the final assert
	 */
	public  void assertAll()
	{
		sa.assertAll();
	}

}
