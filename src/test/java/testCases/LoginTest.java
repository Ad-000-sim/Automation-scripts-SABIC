package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.qameta.allure.Description;
import pageObjects.DashBoardPage;
import pageObjects.loginPage;
import testBase.BaseClass;
import utilities.ConfigReader;

public class LoginTest extends BaseClass {
	loginPage lp ;
	ConfigReader cr ;
	DashBoardPage dp;
	CreateUser cu;
	

	@Description("Verify successful login with valid credentials")
	

	
	@Test(priority=1, groups= {"smoke","Master"})
	public void LoginTest() throws InterruptedException {
		try {
		lg.info("starting login test case with valid credentials");
		lp = new loginPage(driver);
		cr= new ConfigReader();
		System.out.println(driver.getCurrentUrl());
		lg.info("URL is launched");
		
		lg.info("providing credentials");
		lp.enterEmail(cr.getUsername());
		
		lp.enterPassword(cr.getPassword());
		lg.info("clicking on submit button");
		lp.clickonSubmit();
		}
		catch(Exception e) {
			lg.error("Test is failed");
			lg.debug("Debug logs");
			Assert.fail();
		}
		
		
//		
//		lp.isLoginErrorDisplayed();
//		lg.info("Login Test is finished");
		
		//cu.creatingUser();
		
	}
	
	

	
	
		

		
		
		
		
	
}
