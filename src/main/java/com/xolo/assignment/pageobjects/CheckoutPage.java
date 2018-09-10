package com.xolo.assignment.pageobjects;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.xolo.assignment.webbase.DoWebAction;

public class CheckoutPage {
	WebDriver driver;

	public CheckoutPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(name = "number")
	private WebElement cardNumberTextBox;

	@FindBy(name = "cvv")
	private WebElement securityCodeTextBox;

	@FindBy(name = "expiryMonth")
	private WebElement selectMonthDropdown;

	@FindBy(name = "expiryYear")
	private WebElement selectYearDropdown;

	@FindBy(name = "billingPostcode")
	private WebElement zipCodeTextBox;

	@FindBy(xpath = "//button[contains(@class,'btn btn-success action-pay')]")
	private WebElement payButton;

	@FindBy(xpath = "//div[@class='alert alert-success']")
	private WebElement bookingConfirmation;

	@FindBy(xpath = "//a[text()='Have a code?']")
	private WebElement codeLink;

	@FindBy(xpath = "//button[text()='CANCEL']")
	private WebElement cancelButton;

	@FindBy(xpath = "//button[text()='Apply Code']")
	private WebElement applyCodeButton;

	@FindBy(xpath = "//h2[text()='Checkout']")
	private WebElement checkOutPage;

	@FindBy(xpath = "//div[input[@name='code']]/span")
	private WebElement enterCodeFieldValidation;

	@FindBy(xpath = "//span[text()='×']")
	private WebElement closeButton;

	@FindBy(name = "code")
	private WebElement enterCodeTextBox;
	
	@FindBy(xpath="//a[text()='Back']")
	private WebElement backLink;

	@FindBy(xpath = "//div[@class='input-group cc-number']//span")
	private WebElement cardValidation;
	
	@FindBy(xpath = "//div[@class='col-sm-4 form-group cc-password has-error']//span")
	private WebElement securityCodeValidation;
	
	@FindBy(xpath="//div[contains(text(),'Card has expired')]")
	private WebElement cardExpiryValidation;
	
	public void enterCardDetails(String cardNo, String cvv, String zipcode, String expiryMonth, String expiryYear) {
		DoWebAction action = new DoWebAction();
		action.webType(driver, cardNumberTextBox, cardNo, 2);
		action.webType(driver, securityCodeTextBox, cvv, 2);
		action.webType(driver, zipCodeTextBox, zipcode, 2);
		action.selectInDropDownUsingValue(driver, selectMonthDropdown, expiryMonth, 2);
		action.selectInDropDownUsingValue(driver, selectYearDropdown, expiryYear, 2);
		clickOnPayButton();
	}

	public void enterCarddetails(String cardDetails) {
		cardNumberTextBox.clear();
		new DoWebAction().webType(driver, cardNumberTextBox, cardDetails, 2);
	}

	public void enterSecurityCodeDetails(String cardDetails) {
		securityCodeTextBox.clear();
		new DoWebAction().webType(driver, securityCodeTextBox, cardDetails, 2);
	}
	
	public void enterCodeField(String applyCode) {
		enterCodeTextBox.clear();
		new DoWebAction().webType(driver, enterCodeTextBox, applyCode, 2);
	}
	
	public void selectExpiryMonth(String Month) {
		new DoWebAction().selectInDropDownUsingValue(driver, selectMonthDropdown, Month, 2);
	}
	
	public void selectExpiryYear(String Year) {
		//selectYearDropdown.click();
		new DoWebAction().selectInDropDownUsingValue(driver, selectYearDropdown, Year, 2);
	}
	
	public void clickOnPayButton() {
		new DoWebAction().webClick(driver, payButton, 3);
	}

	public void clickOnHaveACodeLink() {
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.elementToBeClickable(codeLink));
		try {
			new DoWebAction().webClick(driver, codeLink, 5);
		} catch (WebDriverException e) {
			new DoWebAction().webClick(driver, codeLink, 5);
		}
	}

	public void clickCancelOnCodePopUp() {
		new DoWebAction().webClick(driver, cancelButton, 5);
	}

	public void clickOnApplyCodeButton() {
			new DoWebAction().webClick(driver, applyCodeButton, 5);
	}
	
	public void clickOnCloseButton() {
		new DoWebAction().webClick(driver, closeButton, 1);
	}
	
	public void clickOnBackLink() {
		  JavascriptExecutor js = (JavascriptExecutor)driver;	
		  js.executeScript("arguments[0].click();", backLink);
	}

	public String getConfirmedBookingText() {
		return bookingConfirmation.getText();
	}

	public String getCardValidation() {
		return cardValidation.getText();
	}
	
	public String getSecurityCodeValidation() {
		return securityCodeValidation.getText();
	}

	public String checkOutPageValidation() {
		return checkOutPage.getText();
	}
	
	public String cardExpireyValidation() {
		return cardExpiryValidation.getText();
	}

	public String applyCodePopUpValidation() {
		return enterCodeFieldValidation.getText();
	}
}
