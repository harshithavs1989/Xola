package com.xolo.assignment.pageobjects;

import org.testng.annotations.DataProvider;

public class DataProviders {

	@DataProvider(name = "emailValidation")
	public Object[][] getData() {

		Object o[][] = { { "", "Required", "Validating blank email id" },
				{ "123", "Customer email must be a valid email", "Validating numeric email id" },
				{ "abc", "Customer email must be a valid email", "Validating alphabetic email id" },
				{ "abc@mail", "Customer email must be a valid email", "Validating email id without a ." },
				{ "abc.com", "Customer email must be a valid email", "Validating email id without @" } };
		return o;
	}

	@DataProvider(name = "securityCodeValidation")
	public Object[][] getSecurityuCode() {
		return new Object[][] {
			{"", "Required", "Continue booking Security code with blank" },
			{ "1234Invalid", "Security code is invalid", "Continue booking with invalid Security number" },
			{ "123", "", "Continue booking with valid Security number" }};
	}

	@DataProvider(name = "applyCode")
	public Object[][] getCode() {

		Object o[][] = { { "12334556", "Invalid Code", "click on apply code withot entering the code" },
				{ "", "Required", "click on apply code withot entering the code" },
				{ "adfsd", "Invalid Code", "click on apply code withot entering the code" } };
		return o;
	}
}
