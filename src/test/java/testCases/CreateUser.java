package testCases;

import org.testng.annotations.Test;

import pageObjects.CreateUserPage;
import pageObjects.DashBoardPage;
import testBase.BaseClass;
import utilities.GenerateRandomStrings;

public class CreateUser extends BaseClass{
	public static String gm;
	public static String pwd;
	DashBoardPage dp;
	CreateUserPage cp;
	LoginTest lt;
	
	@Test(dependsOnMethods = {"testCases.LoginTest.LoginTest"},groups= {"sanity"})
	public void creatingUser() throws InterruptedException {
//	  lt= new LoginTest();
//	  lt.LoginTest();
	  dp = new DashBoardPage(driver);
	  dp.ClickOnUsersLink();
	  //Thread.sleep(2000);
	  cp=new CreateUserPage(driver);
	  cp.clickOnAddUserBtn();
	  String fn = GenerateRandomStrings.generateRandomFirstName();
	  cp.EnterFirstName(fn);
	  String ln = GenerateRandomStrings.generateRandomLastName();
	  cp.EnterLastName(ln);
	  gm = GenerateRandomStrings.RandomGmail();
	  cp.EnterEmail(gm);
	  String ph = GenerateRandomStrings.generateRandomPhoneNumber();
	  cp.EnterPhoneNumber(ph);
	  //Thread.sleep(3000);
	  cp.selectCountry();
	  Thread.sleep(2000);
	  cp.selectCity();
	  cp.selectDefaultProject();
	  cp.selectUserRole();
	  pwd = GenerateRandomStrings.generateAlphanumericPassword();
	  cp.clickOnSubmitButton();
	  cp.clickOnSetNewPassword();
	  cp.enterPassword(pwd);
	  cp.enterConfirmPassword(pwd);
	  cp.clickOnSecondSubmitButton();
	  
	  
	  
	  
	  
	  
	}
	

}
