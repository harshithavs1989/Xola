package com.xolo.assignment.webbase;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * 
 * @author Harshitha VS
 *
 */

public interface IDoAction 
{
	public void webType(WebDriver driver, WebElement ele, String text, int waitTime);
	
	public void webClick(WebDriver driver, WebElement ele, int waitTime);

	public void selectInDropDownUsingValue(WebDriver driver, WebElement element, String valueToSelect, int waitTime);
	
	public void checkCheckBox(WebDriver driver, WebElement element, int waitTime);
	
	public void uncheckCheckBox(WebDriver driver, WebElement element, int waitTime);
	
	public void clickAlertOK(WebDriver driver, int waitTime);
	
	public void clickAlertCancel(WebDriver driver, int waitTime);
	
	public String getAlertText(WebDriver driver, int waitTime);
	
	
}
