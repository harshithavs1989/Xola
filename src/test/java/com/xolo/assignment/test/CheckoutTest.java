package com.xolo.assignment.test;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.xolo.assignment.base.Base;
import com.xolo.assignment.pageobjects.BookingDetailsPage;
import com.xolo.assignment.pageobjects.CheckoutPage;
import com.xolo.assignment.pageobjects.DataProviders;
import com.xolo.uibase.assertion.Assertion;

public class CheckoutTest extends Base
{
	BookingDetailsPage bdp;
	CheckoutPage cp;

	@Test(description = "Enter booking information")
	public void eneterBookingInformation() {
		bdp = new BookingDetailsPage(driver);
		String random = RandomStringUtils.randomAlphabetic(5);
		bdp.enterBookingDetails("harshitha"+random, "harsh@gmail.com", "667878997","24");
		bdp.clickContinueButton();
	}

	@Test(dependsOnMethods ="eneterBookingInformation")
	public void clickOnBackLink(){
		bdp = new BookingDetailsPage(driver);
		cp = new CheckoutPage(driver);
		cp.clickOnBackLink();
		Assertion asert = new Assertion();
		asert.assertString(bdp.bookingDetailsPageValidation(), "BOOKING DETAILS", "Navigated to booking details page");
		asert.assertAll();
		bdp.clickContinueButton();
	}

	@Test(dependsOnMethods="clickOnBackLink",dataProvider = "cardNumberValidation", description = "Card Number field Validation")
	public void cardNumberFieldValidation(String cardDetails, String expectedErorMessage, String message) 
	{
		bdp = new BookingDetailsPage(driver);
		cp = new CheckoutPage(driver);
		WebElement e = driver.findElement(By.name("number"));
		e.clear();
		for(int i=0;i<cardDetails.length();i++)
		{
			e.sendKeys(cardDetails.charAt(i) + "");
		}
		cp.clickOnPayButton();
		Assertion asert = new Assertion();
		asert.assertString(cp.getCardValidation(), expectedErorMessage, message);
		asert.assertAll();
	}

	@Test(dependsOnMethods="cardNumberFieldValidation",dataProvider = "securityCodeValidation",dataProviderClass= DataProviders.class, description = "Card Number field Validation")
	public void securityCodeFieldValidation(String securityCode, String expectedErorMessage, String message) {
		cp = new CheckoutPage(driver);
		cp.enterSecurityCodeDetails(securityCode);
		cp.clickOnPayButton();	
	}

	@Test(dependsOnMethods ="securityCodeFieldValidation",description = "card Expiry validation") 
	public void selectMonthAndYear() {
		cp = new CheckoutPage(driver);
		cp.selectExpiryMonth("01 Jan");
		cp.selectExpiryYear("2018");
		cp.clickOnPayButton();	
		Assertion asert = new Assertion();
		asert.assertString(cp.cardExpireyValidation(), "Card has expired", "Cars Expiry validation");
		asert.assertAll();
	}

	@Test(dependsOnMethods="selectMonthAndYear",description = "Book tickets successfully")
	public void booktickets() {
		cp = new CheckoutPage(driver);
		cp.selectExpiryMonth("01 Jan");
		cp.selectExpiryYear("2019");
		cp.clickOnPayButton();
		Assertion asert = new Assertion();
		asert.assertString(cp.getConfirmedBookingText(), "YOUR BOOKING HAS BEEN PLACED", "Booking confirmation message");
		asert.assertAll();
	}

	@DataProvider(name = "cardNumberValidation")
	public Object[][] getCardNumber() {
		return new Object[][] {
			{"", "Required", "Continue booking card number with blank"},
			{ "4242424242", "Card number is invalid","Continue booking with card number less then 16 characters"},
			{ "4242424242424242","" , "Continue booking with valid card details"}
		};
	}

}
