package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.qameta.allure.Description;
import pageObjects.DashBoardPage;
import pageObjects.RecipeSearchWorkFlowPage;
import pageObjects.consoleLandingPage;
import testBase.BaseClass;

public class RecipeSearchWorkFlow extends BaseClass{
	consoleLandingPage clPage;
	DashBoardPage dp;
	RecipeSearchWorkFlowPage rsw;
	
	
	@Description("Verify successful report generation for Recipe Search")
	@Test(groups= {"smoke"})
	public void RecipeSearchWorkFlowTest()  {
		try {
//		dp = new DashBoardPage(driver);
//		dp.clickonTenants();
//		dp.ClickonAccessTenants();
		
		clPage = new consoleLandingPage(driver);
		clPage.clickonRecipeSearch();
		
		rsw = new RecipeSearchWorkFlowPage(driver);
		rsw.SearchDetailsTab(rsw.rn);
		rsw.clickOnDataset();
		rsw.clickOnNextButton();
		rsw.AddIngredients();
		rsw.verifyReport(rsw.rn);
		rsw.verifyReportNameInReport();
		clPage.ClickonDashBoard();
		}
		catch(Exception e) {
			Assert.fail("Recipe Search WorkFlow Test Failed: " + e.getMessage());
		}
	}
}
