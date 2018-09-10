package com.xolo.assignment.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.SkipException;

import com.xolo.assignment.webbase.DoWebAction;

public class BookingDetailsPage {

	WebDriver driver;

	public BookingDetailsPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//h3[@class='experience-price']/var")
	private WebElement experiencePrice;

	@FindBy(xpath = "(//input[@name='demographics'])[1]")
	private WebElement getNoOfAdultTickets;

	@FindBy(xpath = "(//input[@name='demographics'])[2]")
	private WebElement getNoOfChildTickets;

	@FindBy(id = "customerName")
	private WebElement customerNameTextField;

	@FindBy(name = "customerEmail")
	private WebElement customerEmailTextField;

	@FindBy(name = "phone")
	private WebElement phoneNumberTextField;

	@FindBy(xpath = "//button[text()='Continue']")
	private WebElement continueButton;

	@FindBy(xpath = "//div[label[contains(text(),'Adults')]]/following-sibling::div/button[1]")
	private WebElement decreaseAdultQuantityButton;

	@FindBy(xpath = "//div[label[contains(text(),'Adults')]]/following-sibling::div/button[2]")
	private WebElement increaseAdultQuantityButton;

	@FindBy(xpath = "//div[label[contains(text(),'Children')]]/following-sibling::div/button[1]")
	private WebElement decreaseChildrenQuantityButton;

	@FindBy(xpath = "//div[label[contains(text(),'Children')]]/following-sibling::div/button[2]")
	private WebElement increaseChildrenQuantityButton;

	@FindBy(xpath = "//button[@name='arrivalTime']/var[text()='12:00 AM']")
	private WebElement selectTime;

	@FindBy(xpath = "//div[label[contains(text(),'Adults')]]//following-sibling ::div/input")
	private WebElement selectQuantity;

	@FindBy(xpath = "//h2[text()='Booking Details']")
	private WebElement bookingDetailsPageValidation;

	// Validations

	@FindBy(xpath = "//div[input[@name='customerName']]/span")
	private WebElement nameFieldValidation;

	@FindBy(xpath = "//div[input[@name='customerEmail']]/span")
	private WebElement emailFieldValidation;

	@FindBy(xpath = "//div[text()='Select a valid date']")
	private WebElement dateAndTimeValidation;

	@FindBy(xpath = "//span[text()='Quantity must be at least 1']")
	private WebElement quantityFieldValidationForLessThanOne;

	@FindBy(xpath = "//span[contains(text(),'Please contact Bengaluru City Tours')]")
	private WebElement quantityFieldValidationForMoreThanFour;

	public boolean validateFromPrice() {
		String s = experiencePrice.getText();
		if(s.matches("\\$[0-9]{2,4}\\.[0-9]{2,3}"))
		{
			return true;
		}else
		{
			return false;
		}
		
	}

	public int noOfTickets() {
		String noOfAdulttickets = getNoOfAdultTickets();
		String noOfChildtickets = getNoOfChildTickets();
		Integer adultTickets = Integer.valueOf(noOfAdulttickets);
		Integer childTickets = Integer.valueOf(noOfChildtickets);
		Integer totalTickets = adultTickets + childTickets;
		return totalTickets;
	}

	public void continueBookingWithMoreThanFourTickets() {
		if (noOfTickets() > 0) {
			for (int i = noOfTickets(); i < 5; i++) {
				increaseAdultQuantityButton.click();
			}
		}
	}

	public void continueBookingWithLessThanOneTicket() {
		System.out.println("inside ++s");
		if (noOfTickets() > 0)
			for (int i = noOfTickets(); i > 0; i--) {
				decreaseAdultQuantityButton.click();
			}
	}

	public void setDateAndTime(String day) {
		try {
			driver.findElement(By.xpath("//a[@data-date='" + day + "']/div/var")).isDisplayed();
			driver.findElement(By.xpath("//a[@data-date='" + day + "']/div/var")).click();
		} catch (NoSuchElementException n) {
			throw new SkipException("The provided day is not valid. Please select another date");
		}

		List<WebElement> elemnets = driver.findElements(By.name("arrivalTime"));
		elemnets.get(0).click();
	}

	public void enterBookingDetails(String customerName, String customerEmail, String phoneNumber, String day) {
		new DoWebAction().webType(driver, customerNameTextField, customerName, 2);
		new DoWebAction().webType(driver, customerEmailTextField, customerEmail, 2);
		new DoWebAction().webType(driver, phoneNumberTextField, phoneNumber, 2);
		new DoWebAction().webClick(driver, increaseAdultQuantityButton, 2);
		setDateAndTime(day);
	}

	public void clickContinueButton() {
		new DoWebAction().webClick(driver, continueButton, 2);
	}

	public void continueBookingWithEmailFieldBlank() {
		customerEmailTextField.clear();
		clickContinueButton();
	}

	public void continueBookingWithInvalidEmail(String email) {
		customerEmailTextField.clear();
		new DoWebAction().webType(driver, customerEmailTextField, email, 2);
	}

	public String getNameValidationMessage() {
		return nameFieldValidation.getText();
	}

	public String getEmailFieldValidationMessage() {
		return emailFieldValidation.getText();
	}

	public String getDateValidationMessage() {
		return dateAndTimeValidation.getText();
	}

	public String getNoOfAdultTickets() {
		String noOfAdultTickets = getNoOfAdultTickets.getAttribute("value");
		return noOfAdultTickets;
	}

	public String getNoOfChildTickets() {
		String noOfChildTickets = getNoOfChildTickets.getAttribute("value");
		return noOfChildTickets;
	}

	public String getQuantityValidationForLessThanOne() {
		return quantityFieldValidationForLessThanOne.getText();
	}

	public String getQuantityValidationForMoreThanFour() {
		return quantityFieldValidationForMoreThanFour.getText();
	}

	public String bookingDetailsPageValidation() {
		return bookingDetailsPageValidation.getText();
	}
}
