package com.xolo.assignment.test;

import org.apache.commons.lang3.RandomStringUtils;
import org.testng.annotations.Test;

import com.xolo.assignment.base.Base;
import com.xolo.assignment.pageobjects.BookingDetailsPage;
import com.xolo.assignment.pageobjects.CheckoutPage;
import com.xolo.assignment.pageobjects.DataProviders;
import com.xolo.uibase.assertion.Assertion;

public class HaveACodeTest extends Base
{
	CheckoutPage cp;
	BookingDetailsPage bdp;

	@Test(enabled=true,description = "Click on cancel button on the apply code pop-up")
	public void clickCancelOnCodePopup() {
		String name = RandomStringUtils.randomAlphabetic(5);
		cp = new CheckoutPage(driver);
		bdp = new BookingDetailsPage(driver);
		bdp.enterBookingDetails("Harshitha"+name, "test@gmail.com", "883400812", "20");
		bdp.clickContinueButton();
		cp.clickOnHaveACodeLink();
		cp.clickCancelOnCodePopUp();
		Assertion asert = new Assertion();
		asert.assertString(cp.checkOutPageValidation(), "CHECKOUT", "Navigated to checkoutpage");
		asert.assertAll();
	}

	@Test(enabled=true,dependsOnMethods = "clickCancelOnCodePopup",dataProvider = "applyCode",dataProviderClass= DataProviders.class,description = "Validate Enter Code Text Box")
	public void applyCodeFieldValidation(String applyCode, String errorMessage, String message) {
		cp = new CheckoutPage(driver);
		cp.clickOnHaveACodeLink();
		cp.enterCodeField(applyCode);
		cp.clickOnApplyCodeButton();
		Assertion asert = new Assertion();
		asert.assertString(cp.applyCodePopUpValidation(), errorMessage, message);
		asert.assertAll();
		cp.clickCancelOnCodePopUp();
	}

	@Test(enabled=true,dependsOnMethods="applyCodeFieldValidation",description = "click on close button in the Apply Code pop-up")
	public void clickOnCloseButton() {
		cp = new CheckoutPage(driver);
		cp.clickOnHaveACodeLink();
		cp.clickOnCloseButton();
		Assertion asert = new Assertion();
		asert.assertString(cp.checkOutPageValidation(), "CHECKOUT", "Navigated to checkoutpage");
		asert.assertAll();
	}
}
