package testCases;

import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import pageObjects.DashBoardPage;
import pageObjects.landingPage;
import pageObjects.loginPage;
import testBase.BaseClass;
import utilities.DataProviders;

public class LoginDDT extends BaseClass {
    loginPage lp;
    landingPage lgp;
    DashBoardPage dp;
    WebDriverWait ww;

    @Test(dataProvider = "ExcelDataProvider", dataProviderClass = DataProviders.class, groups= {"smoke"})
    public void testLogin(String username, String password, String status) {
      
            System.out.println("Testing with username: " + username);
            System.out.println("Testing with password: "+ password);
            
            
            lp = new loginPage(driver);
            
            lp.enterEmail(username);
            lp.enterPassword(password);
            lp.clickonSubmit();
//            lp.verifyImage();
            
//            Thread.sleep(3000);
//            
//            
//            dp = new DashBoardPage(driver);
//            dp.clickProfilerIcon();
//            Thread.sleep(5000);
//            dp.clickLogoutLink(); 
//            Thread.sleep(5000);
//            dp.clickONLogoutInPopUp(); 
//            
//            lgp.clickOnSubmitButtonLandingPage();
            
            
        
    }
}