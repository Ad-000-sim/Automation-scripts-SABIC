package pageObjects;

import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

public class PerfomanceSearchWorkFlowPage extends BasePage {
	Random ran;
	Select s;
	
	public PerfomanceSearchWorkFlowPage(WebDriver driver) {
		super(driver);
	}
	
	By ReportName = By.id("prediction-profile-name");
	By DatasetDD = By.xpath("//span[text()='Select dataset']");
	By DatasetType = By.xpath("//li[normalize-space()='Cycoloy FR Pmml']");
	By NextButton = By.xpath("//a[@href='#next']");
	By propRows = By.xpath("(//tbody//tr)[position()<= 5]");
	By criteriaDropdown = By.xpath("(//select[contains(@class, 'proCriteria')])[position()<=5]");
	By toggleButton = By.xpath("(//label[@class='custom-control-label'])[position()<=5]");
	By rangeMinField = By.xpath("//input[contains(@id,'minVal')]");
	By rangeMaxField = By.xpath("//input[contains(@name,'maxVal')]");
	
	
	By GenerateButton=By.xpath("//a[normalize-space()='Generate Report']");
	By GeneReportViewIcon=By.xpath("//span[text()='PerformancePredictionAutomationReport76']/../../..//a[@aria-label='View Report']");
    By success=By.xpath("//i[@data-toggle='tooltip'and @data-bs-original-title='Success']");
    By Fail= By.xpath("//i[@data-toggle='tooltip'and @data-bs-original-title='Failed']");
    By Processing=By.xpath("//i[@aria-label='Processing']");
    By RefreshIcon=By.xpath("//button[@id='refresh_spin']");
	
	
    public void EnterReportName(String rname) {
		WebElement inputField = ww.until(ExpectedConditions.visibilityOfElementLocated(ReportName));
		inputField.clear();
		inputField.sendKeys(rname);
	}

	public void clickOnDatasetDD() throws InterruptedException {
		WebElement dd = ww.until(ExpectedConditions.elementToBeClickable(DatasetDD));
		dd.click();
		WebElement typeOption = ww.until(ExpectedConditions.elementToBeClickable(DatasetType));
		typeOption.click();
		Thread.sleep(5000);
	}

	
	public void clickonNextButton() {
		int attempts = 0;
		boolean clicked = false;

		while (attempts < 3 && !clicked) {
			try {
				WebElement element = ww.until(ExpectedConditions.presenceOfElementLocated(NextButton));
				((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", element);
				ww.until(ExpectedConditions.elementToBeClickable(element));
				((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
				clicked = true;
			} catch (Exception e) {
				System.out.println("Attempt " + (attempts + 1) + " to click Next failed: " + e.getMessage());
				try {
					Thread.sleep(2000);
				} catch (InterruptedException ignored) {}
			}
			attempts++;
		}

		if (!clicked) {
			Assert.fail("Next button could not be clicked after multiple attempts.");
		}
	}

	
	public void SelectProperties() throws InterruptedException {
		ran = new Random();
		List<String> criteriaOptions = Arrays.asList("Greater Than (>)", "Less Than (<)", "Range");
		String selectedCriteria = criteriaOptions.get(ran.nextInt(criteriaOptions.size()));
		List<WebElement> propertyRows = driver.findElements(propRows);

		for (WebElement row : propertyRows) {
			try {
				WebElement dropdownElement = ww.until(ExpectedConditions.elementToBeClickable(row.findElement(criteriaDropdown)));
				((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", dropdownElement);
				Select dropdown = new Select(dropdownElement);
				dropdown.selectByVisibleText(selectedCriteria);

				switch (selectedCriteria) {
					case "Greater Than (>)":
						WebElement gtInput = ww.until(ExpectedConditions.visibilityOf(row.findElement(rangeMinField)));
						gtInput.clear();
						gtInput.sendKeys("0");
						break;

					case "Less Than (<)":
						WebElement ltInput = ww.until(ExpectedConditions.visibilityOf(row.findElement(rangeMinField)));
						ltInput.clear();
						ltInput.sendKeys("100");
						break;

					case "Range":
						WebElement minInput = ww.until(ExpectedConditions.visibilityOf(row.findElement(rangeMinField)));
						WebElement maxInput = ww.until(ExpectedConditions.visibilityOf(row.findElement(rangeMaxField)));
						minInput.clear();
						minInput.sendKeys("0");
						maxInput.clear();
						maxInput.sendKeys("100");
						break;
				}

				
				List<WebElement> togBtns = row.findElements(toggleButton);
				for (WebElement toggle : togBtns) {
					try {
						((JavascriptExecutor) driver).executeScript("arguments[0].click();", toggle);
					} catch (Exception e) {
						System.out.println("Toggle click failed: " + e.getMessage());
					}
				}

				Thread.sleep(2000);
				break;

			} catch (Exception e) {
				System.out.println("Error processing property row: " + e.getMessage());
			}
		}
		Thread.sleep(2000);
	}

	public void GenerateButton() throws InterruptedException {
		
		WebElement generateBtn = ww.until(ExpectedConditions.presenceOfElementLocated(GenerateButton));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", generateBtn);
		generateBtn.click();
	}

	public void verifyReport(String reportName) {
		System.out.println("Report name is :- " + reportName);
		try {
			Thread.sleep(5000);  //modified from 10000 to 5000
			boolean isReportVisible = false;
			int attempts = 0;
			int maxAttempts = 4;

			By dynamicReportViewIcon = By.xpath("//span[text()='" + reportName + "']/../../..//a[@aria-label='View Report']");
			By statusSuccess = By.xpath("//span[text()='" + reportName + "']/../../..//i[@data-toggle='tooltip' and @data-bs-original-title='Success']");
			By statusFailed = By.xpath("//span[text()='" + reportName + "']/../../..//i[@data-toggle='tooltip' and @data-bs-original-title='Failed']");

			while (attempts < maxAttempts && !isReportVisible) {
				try {
					WebElement viewIcon = ww.until(ExpectedConditions.visibilityOfElementLocated(dynamicReportViewIcon));
					((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", viewIcon);

					if (!driver.findElements(statusSuccess).isEmpty()) {
						System.out.println("Report status: Success");
						((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", viewIcon);
						ww.until(ExpectedConditions.elementToBeClickable(viewIcon)).click();
						isReportVisible = true;
						Assert.assertTrue(true, "Report generated successfully.");
						break;
					} else if (!driver.findElements(statusFailed).isEmpty()) {
						System.out.println("Report status: Failed");
						Assert.fail("Report generation failed.");
						break;
					} else {
						System.out.println("Report is still processing...");
					}
				} catch (NoSuchElementException | org.openqa.selenium.TimeoutException e) {
					System.out.println("View icon not yet visible, retrying: " + (attempts + 1));
				}

				try {
					WebElement refreshBtn = ww.until(ExpectedConditions.elementToBeClickable(RefreshIcon));
					refreshBtn.click();
					Thread.sleep(6000);  //from 10k to 6k
				} catch (Exception e) {
					System.out.println("Failed to click refresh icon.");
				}
				attempts++;
			}

			if (!isReportVisible) {
				System.out.println("Report not available after maximum retries.");
				Assert.fail("Report not available after maximum retries.");
			}

		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
			System.out.println("Interrupted Exception occurred.");
		}
	}

	public void verifyReportNameinReport(String expName) {
		By reportNameInPsreport = By.xpath("//h5[contains(@class,'mr-2 text-uppercase font-weight-normal')]");
		String actName = ww.until(ExpectedConditions.visibilityOfElementLocated(reportNameInPsreport)).getText();
		Assert.assertEquals(actName.toLowerCase(), expName.toLowerCase(), "Report name does not match expected value.");
		System.out.println("Actual report name: " + actName);
		System.out.println("Expected report name: " + expName);
	}
	

	
} 
		

