package com.xolo.assignment.test;

import org.testng.annotations.Test;

import com.xolo.assignment.base.Base;
import com.xolo.assignment.pageobjects.BookingDetailsPage;
import com.xolo.assignment.pageobjects.CheckoutPage;
import com.xolo.assignment.pageobjects.DataProviders;
import com.xolo.uibase.assertion.Assertion;

public class BookingDetailsTest extends Base
{

	BookingDetailsPage bdp;
	CheckoutPage cp;
	
	@Test(description = "Check the price value from $**")
	public void verifyFromValue() {
		bdp = new BookingDetailsPage(driver);
		boolean priceIsDisplayedProperly = bdp.validateFromPrice();
		
		Assertion asert = new Assertion();
		asert.assertTrue(priceIsDisplayedProperly, "The price section in top page is not displayed properly");
		asert.assertAll();
	}

	@Test(description = "Continue booking with more then four ticket")
	public void bookMoreThanfourTickets() {
		bdp = new BookingDetailsPage(driver);
		bdp.continueBookingWithMoreThanFourTickets();
		String quantityFieldValidation = bdp.getQuantityValidationForMoreThanFour();
		Assertion asert = new Assertion();
		asert.assertString(quantityFieldValidation,
				"Please contact Bengaluru City Tours at 1234567890 or email rushi+blrtours@xola.com if you are interested in booking a group larger than 4",
				"Assert booking ticket with more than four of tickets");
		asert.assertAll();
	}

	@Test(dependsOnMethods = "bookMoreThanfourTickets",description = "Continue booking with less then one ticket")
	public void bookLessThanOneTicket() {
		bdp = new BookingDetailsPage(driver);
		bdp.continueBookingWithLessThanOneTicket();
		
		String quantityFieldValidation = bdp.getQuantityValidationForLessThanOne();
		Assertion asert = new Assertion();
		asert.assertString(quantityFieldValidation, "Quantity must be at least 1",
				"Assert booking ticket with less than one ticket");
		asert.assertAll();
	}

	@Test(dependsOnMethods="bookLessThanOneTicket",description = "Name Field validation")
	public void nameFieldValidation() {
		bdp = new BookingDetailsPage(driver);
		bdp.clickContinueButton();
		String nameFieldValidationMessage = bdp.getNameValidationMessage();
		Assertion asert = new Assertion();
		asert.assertString(nameFieldValidationMessage, "Required", "Asserting Name field with no data");
		asert.assertAll();
	}

	@Test( dependsOnMethods = "nameFieldValidation",description = "Email field validation",dataProvider = "emailValidation",dataProviderClass= DataProviders.class)
	public void invalidEmailID(String email, String expectedErrMessage, String message) {
		bdp = new BookingDetailsPage(driver);
		bdp.continueBookingWithInvalidEmail(email);
		Assertion asert = new Assertion();
		asert.assertString(bdp.getEmailFieldValidationMessage(), expectedErrMessage, message);
		asert.assertAll();
	}

	@Test(dependsOnMethods = "invalidEmailID",description="Continue booking with valid data")
	public void continueBookingWithValidData() {
		cp=new CheckoutPage(driver);
		bdp = new BookingDetailsPage(driver);
		bdp.enterBookingDetails("Harshitha", "Harshitha@g.com", "224454", "25");
		bdp.clickContinueButton();
		Assertion asert = new Assertion();
		asert.assertString(cp.checkOutPageValidation(), "CHECKOUT", "Navigated to checkout page ");
		asert.assertAll();
	}	
}
