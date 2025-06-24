package testCases;

import org.testng.annotations.Test;

import io.qameta.allure.Description;
import pageObjects.DashBoardPage;
import pageObjects.PerformancePredictionWorkFlowPage;
import pageObjects.RecipeSuggestionWorkFlowPage;
import pageObjects.consoleLandingPage;
import pageObjects.loginPage;
import testBase.BaseClass;
import utilities.ConfigReader;
import utilities.GenerateRandomStrings;

public class RecipeSuggestionWorkFlow extends BaseClass{
	
	loginPage lp ;
	ConfigReader cr ;
	DashBoardPage dp;
	consoleLandingPage clp;
	PerformancePredictionWorkFlowPage ppwf;
	RecipeSuggestionWorkFlowPage rswf;
	RecipeSuggestionWorkFlow rsw;
	
	String rn="RecipeSuggestionAutomationTestReport "+GenerateRandomStrings.generateNumber();
	@Description("Verify successful report generation for Recipe Suggestion")
	@Test(groups= {"smoke"})
	public void RecipeSuggestionReportGeneration() throws InterruptedException {
//		dp =new DashBoardPage(driver);
//		dp.clickonTenants();
//		dp.ClickonAccessTenants();
//		
		clp = new consoleLandingPage(driver);
		clp.clickonRecipeSuggestion();
		
		cr=new ConfigReader();
		rsw= new RecipeSuggestionWorkFlow();
		rswf = new RecipeSuggestionWorkFlowPage(driver);
		rswf.EnterReportName(rsw.rn);
		rswf.clickOnDatasetDD();
		rswf.clickonNextButton();
		rswf.SelectProperties();
		rswf.addIngredient();
		rswf.verifyReport(rsw.rn);
		clp.ClickonDashBoard();
		
		
	}
	
	
	

}
