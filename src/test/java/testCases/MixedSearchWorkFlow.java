package testCases;

import org.testng.annotations.Test;

import io.qameta.allure.Description;
import pageObjects.DashBoardPage;
import pageObjects.MixedSearchWorkFlowPage;
import pageObjects.consoleLandingPage;
import testBase.BaseClass;

public class MixedSearchWorkFlow extends BaseClass {
	DashBoardPage dp;
	consoleLandingPage clPage;
	MixedSearchWorkFlowPage mswp;
	
	@Test(groups= {"smoke"})
	@Description("Verify successful report generation for mixed search")
	public void MixedSearchReportGeneration() throws InterruptedException {
		try {
//		 dp = new DashBoardPage(driver);
//		dp.clickonTenants();
//		dp.ClickonAccessTenants();
		
		 clPage = new consoleLandingPage(driver);
		clPage.clickonMixedSearch();
		
		mswp = new MixedSearchWorkFlowPage(driver);
//		mswp.repoName(mswp.rn);
//		mswp.clickOnDataset();
//		mswp.clickOnNextButton();
//		mswp.selectProperties();
//		mswp.clickOnNextButton();
//		mswp.addRecipeIngredients();
		mswp.EnterReportName(mswp.rn);
		mswp.clickOnDatasetDD();
		mswp.clickonNextButton();
		mswp.SelectProperties();
		mswp.addIngredient();
		mswp.verifyReport(mswp.rn);
		mswp.verifyReportNameInReport();
		clPage.ClickonDashBoard();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("Mixed Search WorkFlow Test Failed: " + e.getMessage());
		}
		
	}


}
