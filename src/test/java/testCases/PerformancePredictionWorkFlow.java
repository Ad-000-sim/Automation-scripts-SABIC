package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.qameta.allure.Description;
import pageObjects.DashBoardPage;
import pageObjects.PerformancePredictionWorkFlowPage;
import pageObjects.consoleLandingPage;
import pageObjects.loginPage;
import testBase.BaseClass;
import utilities.GenerateRandomStrings;

public class PerformancePredictionWorkFlow extends BaseClass{

	loginPage lp ;
	
	DashBoardPage dp;
	consoleLandingPage clp;
	PerformancePredictionWorkFlowPage ppwf;
	String rn="PerformancePredictionAutomationTestReport "+GenerateRandomStrings.generateNumber();
	@Description("Verify successful report generation for Performance Prediction")
	@Test(groups= {"smoke"})
	public void PPWorkflow()  {
		try {
		dp =new DashBoardPage(driver);
		dp.clickonTenants();
		dp.ClickonAccessTenants();
		
		clp = new consoleLandingPage(driver);
		clp.clickonPerfPred();
		
		
		
		ppwf = new PerformancePredictionWorkFlowPage(driver);
		ppwf.EnterReportName(ppwf.rn);
		ppwf.clickOnDatasetDD();
		ppwf.clickonNextButton();
		ppwf.TurnonPropToggle();
		ppwf.clickonNextButton();
		ppwf.SelectIng();
		ppwf.VerfiyReport(ppwf.rn);
		clp.ClickonDashBoard();
		}
		catch(Exception e) {
			e.printStackTrace();
			  Assert.fail("Test failed due to exception: " + e.getMessage());
		}
		
		
		
		
	}
}
