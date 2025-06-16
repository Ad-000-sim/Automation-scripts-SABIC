package testCases;

import org.testng.annotations.Test;

import pageObjects.loginPage;
import testBase.BaseClass;
import utilities.ConfigReader;

public class loginWithNewUser extends BaseClass {
	loginPage lp;
	ConfigReader cr;
	@Test
	public void LoginWithCreatedCredentials() {
		loginPage lp = new loginPage(driver);
		cr = new ConfigReader();
		
		lp.enterEmail(CreateUser.gm);
	
		lp.enterPassword(CreateUser.pwd);
		
		lp.clickonSubmit();
	}

}
