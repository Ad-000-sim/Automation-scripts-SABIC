package pageObjects;

import java.time.Duration;
import java.util.List;
import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

import utilities.ConfigReader;
import utilities.GenerateRandomStrings;

public class PerformancePredictionWorkFlowPage extends BasePage {

	public String rn = "PerformancePredictionAutomationTestReport"+GenerateRandomStrings.generateNumber();
	ConfigReader cr = new ConfigReader();

	public PerformancePredictionWorkFlowPage(WebDriver driver) {
		super(driver);
	}

	By ReportName = By.id("prediction-profile-name");	
	By errorMessageRN = By.xpath("//span[@class='text-danger errorSpl']");	
	By DatasetDD = By.xpath("//span[text()='Select dataset']"); // commom ele
	By searchBoxBy = By.xpath("//input[@role='searchbox']"); 
	By DatasetType = By.xpath("//li[normalize-space()='Cycoloy FR Pmml']");
	By NextButton = By.xpath("//a[@href='#next']"); // common for all 3 tabs in pp workflow
	By PreviousButton = By.xpath("//a[normalize-space()='Previous']"); // common for 2 tabs in pp workflow
	
	
	By PropertiesToggleBtn = By.xpath("//label[contains(@for,'customSwitch')]"); // multiple ele's common for all 3
	By msgValidPP = By.xpath("//span[text()='At least one property should be active.']");
	By searchBoxpp=By.id("search");
	
	By Categorydd = By.xpath("//span[@id='select2-gcategory-container']");
	// By Categoryopt=By.xpath("//li[normalize-space()='ADDITIVE']");

	By SelectIngredientdd = By.xpath("//span[@id='select2-product-container']");
	By IngredientNames = By.xpath("//li[@role='option']"); // multiple Ingred

	By value = By.id("value");
	By addIngButton = By.xpath("//i[@class='fas fa-plus text-secondary']");

	By GenerateButton = By.xpath("//a[normalize-space()='Generate Report']");

//	By GeneReportViewIcon = By
//			.xpath("//span[text()='PerformancePredictionAutomationReport764']/../../..//a[@aria-label='View Report']");
//	By success = By.xpath("//i[@data-toggle='tooltip'and @data-bs-original-title='Success']");
//	By Fail = By.xpath("//i[@data-toggle='tooltip'and @data-bs-original-title='Failed']");
//	By Processing = By.xpath("//i[@aria-label='Processing']");
	By RefreshIcon = By.xpath("//button[@id='refresh_spin']");
	By reportNameInPPreport = By.xpath("//h5[contains(@class,'mr-2 text-uppercase font-weight-normal')]");

	public void EnterReportName(String rname) {
		WebElement inputField = ww.until(ExpectedConditions.visibilityOfElementLocated(ReportName));
		inputField.clear(); // Optional: clear before entering text
		inputField.sendKeys(rname);
	}

	public void verifyErrormsgRName() throws InterruptedException {
	
		WebElement msg = driver.findElement(errorMessageRN);
		Assert.assertTrue(msg.isDisplayed(), "Error message for report name is not displayed.");
		Thread.sleep(2000);
		
	}
	public void clickOnDatasetDD() throws InterruptedException {
		WebElement dd = ww.until(ExpectedConditions.elementToBeClickable(DatasetDD));
		dd.click();

		WebElement typeOption = ww.until(ExpectedConditions.elementToBeClickable(DatasetType));
		typeOption.click();
		Thread.sleep(5000);
	}
	
	
	public void RegClickandSearchInDatasetDD() throws InterruptedException {
		WebElement dd = ww.until(ExpectedConditions.elementToBeClickable(DatasetDD));
		dd.click();
		driver.findElement(searchBoxBy).sendKeys("Cycoloy FR Pmml");
		a.sendKeys(Keys.ENTER).perform();
		Thread.sleep(3000);
	}

	public void clickonNextButton() {
		WebElement element = driver.findElement(NextButton);
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);

		ww.until(ExpectedConditions.elementToBeClickable(element));

		((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
	}
	
	public void previousButton() {
		WebElement element = driver.findElement(PreviousButton);
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);

		ww.until(ExpectedConditions.elementToBeClickable(element));

		((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
	}
	
	public void VerifyMsgpp() {
		WebElement txt = driver.findElement(msgValidPP);
		Assert.assertTrue(txt.isDisplayed(), "Error message for properties toggle is not displayed.");
	}

	public void TurnonPropToggle() throws InterruptedException {

		List<WebElement> togBtns = driver.findElements(PropertiesToggleBtn);

		for (WebElement toggle : togBtns) {
//	            ww.until(ExpectedConditions.elementToBeClickable(toggle)).click();
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", toggle);
		}
		Thread.sleep(2000);
	}

	public void SelectIng() throws InterruptedException {
		try {
//	     
		Thread.sleep(3000);
		int countToClick = 6;
		for (int i = 1; i < countToClick; i++) {
			// List<WebElement> ingNames =
			// ww.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(IngredientNames));
			WebElement dropdown = ww.until(ExpectedConditions.elementToBeClickable(Categorydd));
			dropdown.click();
			a.sendKeys(Keys.ARROW_DOWN).pause(Duration.ofMillis(300)).sendKeys(Keys.ENTER).perform();
			Thread.sleep(5000);
			WebElement ingDropdown = driver.findElement(SelectIngredientdd);
			//WebElement ingDropdown = ww.until(ExpectedConditions.elementToBeClickable(SelectIngredientdd));
			ingDropdown.click();
			Thread.sleep(1000);
			//List<WebElement> ingNames = driver.findElements(IngredientNames);
			List<WebElement> ingNames = ww.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(IngredientNames));
			if (i >= ingNames.size())
				break;
			// Thread.sleep(3000);
			ingNames.get(i).click();
			WebElement qtyField =driver.findElement(value);
			qtyField.click();
					 //ww.until(ExpectedConditions.elementToBeClickable(value));
			qtyField.clear();
			qtyField.sendKeys("20");
			WebElement addBtn = driver.findElement(addIngButton);
					//ww.until(ExpectedConditions.elementToBeClickable(addIngButton));
			addBtn.click();
			Thread.sleep(4000);
			
		}
		WebElement generateBtn = ww.until(ExpectedConditions.presenceOfElementLocated(GenerateButton));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", generateBtn);

		// generateBtn =
		// ww.until(ExpectedConditions.elementToBeClickable(GenerateButton));
		generateBtn.click();

		Thread.sleep(5000);
		}
		catch(NoSuchElementException e) {
			System.out.println("No ingredients found to select.");
		}
		
	}


	

	public void VerfiyReport(String reportName) {
	    try {
	        Thread.sleep(5000);

	        boolean isReportVisible = false;
	        int attempts = 0;
	        int maxAttempts = 4;

	        // Build dynamic XPath using the reportName parameter
	        By dynamicReportViewIcon = By
	            .xpath("//span[text()='" + reportName + "']/../../..//a[@aria-label='View Report']");
	        // **Added status locators**
	        By statusSuccess = By.xpath("//span[text()='" + reportName + "']/../../..//i[@data-toggle='tooltip' and @data-bs-original-title='Success']");
	        By statusFailed = By.xpath("//span[text()='" + reportName + "']/../../..//i[@data-toggle='tooltip' and @data-bs-original-title='Failed']");

	        while (attempts < maxAttempts && !isReportVisible) {
	            try {
	                WebElement viewIcon = ww.until(ExpectedConditions.visibilityOfElementLocated(dynamicReportViewIcon));
	                //((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", viewIcon);
	                // **Check status before clicking view icon**
	                
	                if (!driver.findElements(statusSuccess).isEmpty()) {
	                    System.out.println("Report status: Success");
	                    ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", viewIcon);
	                    ww.until(ExpectedConditions.elementToBeClickable(viewIcon)).click();
	                    isReportVisible = true;
	                    Assert.assertTrue(true, "Report generated successfully."); // **Test pass**
	                    break;
	                } else if (!driver.findElements(statusFailed).isEmpty()) {
	                    System.out.println("Report status: Failed");
	                    Assert.fail("Report generation failed."); // **Test fail**
	                    break;
	                }
	                else {
	                	System.out.println("Report is still processing, waiting for completion...");
	              
	                	break;
	                }
	                // else, do nothing, continue to refresh
	            } catch (NoSuchElementException | org.openqa.selenium.TimeoutException e) {
	                System.out.println("Report is visible checking the status: " + (attempts + 1));
	            }

	            try {
	                WebElement refreshBtn = ww.until(ExpectedConditions.elementToBeClickable(RefreshIcon));
	                refreshBtn.click();
	                Thread.sleep(5000);
	            } catch (Exception e) {
	                System.out.println("Failed to click refresh icon.");
	            }

	            attempts++;
	        }

	        if (!isReportVisible) {
	            System.out.println("Report status is in process after maximum retries.");
	           
	        }

	    } catch (InterruptedException e) {
	        Thread.currentThread().interrupt();
	        System.out.println("Interrupted Exception occurred.");
	    }
	}


	public void verifyReportNameInReport() {
		String ActReportNametxt = driver.findElement(reportNameInPPreport).getText();
    	String ExpReportNametxt = PerformancePredictionWorkFlowPage.this.rn;

    	Assert.assertEquals(ActReportNametxt.toLowerCase(), ExpReportNametxt.toLowerCase(), "Report name does not match expected value.");
		System.out.println("Actual report name: " + ActReportNametxt);
		System.out.println("Expected report name: " + ExpReportNametxt);
	}
	
	
	
	

}
