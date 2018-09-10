package com.xolo.assignment.screenshot;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.Reporter;



/**
 * 
 * @author Harshitha VS
 *
 */
public class WebScreenShot implements IScreenShot
{

	/**
	 * @param driver
	 * @param ITestResult
	 * This is used to take screen shot if the test case fails.
	 */
	public void takeWebScreenShot(WebDriver driver, ITestResult result) 
	{
		if(!(result.isSuccess()))
		{
			System.setProperty("org.uncommons.reportng.escape-output", "false");
			Reporter.setCurrentTestResult(result);
			File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			DateFormat dateFormat = new SimpleDateFormat("dd_MMM_yyyy__hh_mm_ssaa");
			String destDir = "target/surefire-reports/screenshots";
			new File(destDir).mkdirs();
			String destFile = result.getMethod().getMethodName()  + "_" + dateFormat.format(new Date()) + ".png";
	
			try 
			{
				FileUtils.copyFile(scrFile, new File(destDir + "/" + destFile));	
			} catch (IOException e) {
				e.printStackTrace();
			}
			if(result.getParameters().length>0)
			{
				Reporter.log("<p align=\"left\", style= \"color: red; font: italic bold 12px/30px Georgia,serif;\" > Test method is " + result.getMethod().getMethodName()+ "\nTest case params are " + Arrays.toString(result.getParameters()) + "</p>");
			}
			Reporter.log("<a href = ../screenshots/" + destFile +" target='_blank'" +"> " +
					"<p align=\"left\", style= \"color: red; font: italic bold 12px/30px Georgia,serif;\" >Click here for screen shot </p> </a>");
		
		}else
		{
			Reporter.log("<p align=\"left\", style= \"color: green; font: italic bold 12px/30px Georgia,serif;\" > Passed for " + result.getMethod().getMethodName() + "_" + Arrays.toString(result.getParameters()) + "</p>");
		}
	}
}
