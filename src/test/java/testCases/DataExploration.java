package testCases;

import org.testng.annotations.Test;

import pageObjects.DashBoardPage;
import pageObjects.DataExplorationPage;
import pageObjects.consoleLandingPage;
import pageObjects.loginPage;
import testBase.BaseClass;
import utilities.ConfigReader;

public class DataExploration extends BaseClass
{

	loginPage lp ;
	ConfigReader cr ;
	DashBoardPage dp;
	consoleLandingPage clp;
	DataExplorationPage dep;
	
	@Test(groups= {"smoke"})
	public void dataWorkflow() throws InterruptedException
	{
//		dp =new DashBoardPage(driver);
//		dp.clickonTenants();
//		dp.ClickonAccessTenants();
		clp = new consoleLandingPage(driver);
		clp.clickOnDataExploration();
		
		lp = new loginPage(driver);
		cr= new ConfigReader();
		dep = new DataExplorationPage(driver);
		
//        String expectedFileName = "Samplecsv.csv";
//		dep.clickTemplateBtnAndVerifyDownload(expectedFileName);
		
		dep.clickAddDatasetBtn();
		dep.enterDatasetName(cr.getdatasetName());
		dep.fileUpload();
		dep.clickSubmitBtn();
//		boolean isListed = dep.verifyListingElement(cr.getdatasetName());
//		org.testng.Assert.assertTrue(isListed, "Dataset '" + cr.getdatasetName() + "' not found in listing.");
//		dep.clickFilterViewDatasetColonIconInPreFilter();
//		dep.applyColumnFilterValues();
//		dep.clickSaveBtn();
//		dep.enterFilterName();
//		dep.clickSaveBtnAfterEnteringFilterName();
//		dep.clickFilterandViewDatasetBtn();
//		dep.verifyFilteredColumnsInGrid1();
//		dep.breadcrumbInGridView();
//		dep.clickFilterViewDatasetColonIconInPreFilter();
//		dep.applyValueBasedFilter();
//		dep.clickSaveBtn();
//		dep.enterFilterName2();
//		dep.clickSaveBtnAfterEnteringFilterName();
//		dep.clickFilterandViewDatasetBtn();
//		dep.verifyFilteredValuesInIdentifier2Column();
//		dep.breadcrumbInGridView();
//		dep.clickFilterViewDatasetColonIconInPreFilter();
//		dep.clickSkipAndViewDataset();
//		dep.clickFilterBtn();
//		dep.clickFilterbasedOnColumn();
//		dep.clickSaveBtn();
//		dep.enterFilterName3();
//		dep.clickSaveBtnAfterEnteringFilterName();
//		dep.clickFilterandViewDatasetBtn();
//		dep.verifyGridColumnsFilter();
//		dep.clickFilterBtn();
//		dep.clickResetBtn();
//		dep.applyGridValueBasedFilter();
//		dep.clickFilterandViewDatasetBtn();
		
	}
}
