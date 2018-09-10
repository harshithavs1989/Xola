package com.xolo.assignment.screenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;

/**
 * 
 * @author Harshitha VS
 *
 */
public interface IScreenShot 
{
	
	
	
	public void takeWebScreenShot(WebDriver driver, ITestResult result);

}
