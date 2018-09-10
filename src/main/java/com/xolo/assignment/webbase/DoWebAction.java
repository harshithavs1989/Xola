package com.xolo.assignment.webbase;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;


/**
 * 
 * @author Harshitha VS
 *
 */

 public class DoWebAction implements IDoAction{
	Logger log = Logger.getLogger("Logger");
	WebDriver driver;
	public DoWebAction(){}

	/**
	 * Use this method to type into a text box using send keys method.
	 */
	public void webType(WebDriver driver, WebElement element, String text, int waitTime) {
		log.info("Typing into locator" + element.toString().split(":")[2].replace("]", ""));
		WebDriverWait wait = new WebDriverWait(driver, waitTime);
		wait.until(ExpectedConditions.visibilityOf(element));
		element.clear();
		element.sendKeys(text);
	}

	/**
	 * Use this to click on an element
	 */
	public void webClick(WebDriver driver, WebElement element, int waitTime) {
		log.info("Clicking" + element.toString().split("->")[1].replace("]", ""));
		WebDriverWait wait = new WebDriverWait(driver, waitTime);
		wait.until(ExpectedConditions.elementToBeClickable(element));
		element.click();
	}

	/**
	 * This is used to select value by string from a drop down. Pass the text to be
	 * selected as an argument.
	 */

	public void selectInDropDownUsingValue(WebDriver driver, WebElement element, String valueToSelect, int waitTime) {
		WebDriverWait wait = new WebDriverWait(driver, waitTime);
		wait.until(ExpectedConditions.visibilityOf(element));
		log.info("Selecting " + valueToSelect + " from " + element.toString().split(":")[2].replace("]", ""));
		Select select = new Select(element);
		select.selectByVisibleText(valueToSelect);
	}

	/**
	 * This is used to Check the check box.
	 */
	public void checkCheckBox(WebDriver driver, WebElement element, int waitTime) {
		WebDriverWait wait = new WebDriverWait(driver, waitTime);
		wait.until(ExpectedConditions.visibilityOf(element));
		boolean selected = element.isSelected();
		if (!selected) {
			element.click();
		} else {
			log.info("Checkbox of " + element.toString().split(":")[2].replace("]", "") + " is already checked");
		}
	}

	/**
	 * This is used to uncheck a check box.
	 * 
	 * @param element
	 */

	public void uncheckCheckBox(WebDriver driver, WebElement element, int waitTime) {
		WebDriverWait wait = new WebDriverWait(driver, waitTime);
		wait.until(ExpectedConditions.visibilityOf(element));
		boolean selected = element.isSelected();
		if (selected) {
			element.click();
		} else {
			log.info("Checkbox of " + element.toString().split(":")[2].replace("]", "") + "is already unchekced");
		}
	}

	/**
	 * This is used to click ok on java script alert box.
	 */

	public void clickAlertOK(WebDriver driver, int waitTime) {
		WebDriverWait wait = new WebDriverWait(driver, waitTime);
		wait.until(ExpectedConditions.alertIsPresent());
		log.debug("Clicking ok in the java scrip alert");
		driver.switchTo().alert().accept();
	}

	/**
	 * This is used to click cancel on java script alert box.
	 */

	public void clickAlertCancel(WebDriver driver, int waitTime) {
		WebDriverWait wait = new WebDriverWait(driver, waitTime);
		wait.until(ExpectedConditions.alertIsPresent());
		log.debug("Clicking cancel in the java scrip alert");
		driver.switchTo().alert().dismiss();
	}

	/**
	 * This is used for getting the text of alert box.
	 * 
	 * @return
	 */

	public String getAlertText(WebDriver driver, int waitTime) {
		WebDriverWait wait = new WebDriverWait(driver, waitTime);
		wait.until(ExpectedConditions.alertIsPresent());
		log.debug("Getting text from java script alert box");
		return driver.switchTo().alert().getText();
	}
	
	
}
