package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.qameta.allure.Description;
import pageObjects.DashBoardPage;
import pageObjects.PerfomanceSearchWorkFlowPage;
import pageObjects.consoleLandingPage;
import pageObjects.loginPage;
import testBase.BaseClass;
import utilities.ConfigReader;
import utilities.GenerateRandomStrings;

public class PerformanceSearchWorkFlow extends BaseClass {
	loginPage lp ;
	
	consoleLandingPage clPage;
	PerfomanceSearchWorkFlowPage psw;
	DashBoardPage dp;
	ConfigReader cr ;
	
	String rn2 = "PerformanceSearchAutomationReport" + GenerateRandomStrings.generateNumber();
	@Description("Verify successful report generation for Performance Search")
	@Test(groups= {"smoke"})
	public void psReport() {
		try {
//	    dp = new DashBoardPage(driver);
//		dp.clickonTenants();
//		dp.ClickonAccessTenants();
		clPage = new consoleLandingPage(driver);
		clPage.clickonPerformanceSearch();
		
		psw = new PerfomanceSearchWorkFlowPage(driver);
		psw.EnterReportName(rn2);
		psw.clickOnDatasetDD();
		psw.clickonNextButton();
		psw.SelectProperties();
		psw.GenerateButton();
		psw.verifyReport(rn2);
		psw.verifyReportNameinReport(rn2);
		clPage.ClickonDashBoard();
		}
		catch(Exception e) {
			e.printStackTrace();
			Assert.fail("Test is failed"+ e.getMessage());
		}
		
		
		
		
	}

}
