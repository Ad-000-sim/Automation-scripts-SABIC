package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.qameta.allure.Description;
import utilities.Logs;
import pageObjects.DashBoardPage;
import pageObjects.PerformancePredictionWorkFlowPage;
import pageObjects.consoleLandingPage;
import pageObjects.loginPage;
import testBase.BaseClass;
import utilities.GenerateRandomStrings;

public class PerformancePredictionWorkFlowRegressionTest extends BaseClass {

	public static String ReportName ="PerformancePredictionAutomationTestReport "+GenerateRandomStrings.generateNumber();
    loginPage lp ;
	DashBoardPage dp;
	consoleLandingPage clp;
	PerformancePredictionWorkFlowPage ppwfpg;
	
	@Description("Regression test for Performance Prediction workflow")
	@Test(groups= {"Regression"})
	public void RegressionTestPerformacePrediction() {
		try {
		dp =new DashBoardPage(driver);
		Logs.info("clicking on tenants");
		dp.clickonTenants();
		Logs.info("clicking on Access tenants");
		dp.ClickonAccessTenants();
		Logs.info("navigated to the console page");
		clp = new consoleLandingPage(driver);
		Logs.info("clicking on Performace Prediction");
		clp.clickonPerfPred();
		
		ppwfpg = new PerformancePredictionWorkFlowPage(driver);
		Logs.info("Entering invalid Report name");
		ppwfpg.EnterReportName(GenerateRandomStrings.specialCharacters() );
		Logs.info("Verifying error message for the report name");
	   	ppwfpg.verifyErrormsgRName();
	   	Logs.info("Entering valid report name");
	   	ppwfpg.EnterReportName(ReportName);
	   	Logs.info("Selecting Dataset from the dataset dropdown");
	   	ppwfpg.RegClickandSearchInDatasetDD();
	   	Logs.info("clicking on next button");
	   	ppwfpg.clickonNextButton();
	   	Thread.sleep(2000);
	   	Logs.info("Turning on toggle for all the properties");
	   	ppwfpg.TurnonPropToggle();
	   	Logs.info("clicking on previous button");
	   	ppwfpg.previousButton();
	   	Thread.sleep(2000);
	   	Logs.info("clicking on next button");
	 	ppwfpg.clickonNextButton();
		Thread.sleep(2000);
		
		ppwfpg.TurnonPropToggle();
		Thread.sleep(2000);
		ppwfpg.clickonNextButton();
		Thread.sleep(2000);
		ppwfpg.VerifyMsgpp();
		ppwfpg.TurnonPropToggle();
		ppwfpg.clickonNextButton();
		Thread.sleep(2000);
		//ppwfpg.RegIngredientSelection();
		ppwfpg.previousButton();
		ppwfpg.previousButton();
		ppwfpg.clickonNextButton();
		ppwfpg.clickonNextButton();
	
	 	
	   	
		}
		catch(Exception e) {
			e.printStackTrace();
			Assert.fail("Test failed due to exception: " + e.getMessage());
		}
	
					
	}

}
