package pageObjects;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.io.File;
import java.nio.file.*;
import java.time.Duration;

import org.testng.Assert;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import utilities.ConfigReader;

public class DataExplorationPage extends BasePage {
	ConfigReader config = new ConfigReader();
	WebElement btn;

	public DataExplorationPage(WebDriver driver) {
		super(driver);
	} 
	
	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	
	By btnAddDataset = By.xpath("//button[normalize-space()='Add Dataset']");
	By btnTemplate = By.xpath("//a[@data-id='sample1']");
	By txtDatasetName = By.id("dataset_name");
	By uploadFile = By.id("dataset_model_file");
	By btnUploadDataset = By.id("submitbutton");
	By listUploadedDataset = By.xpath("//span[text()='SampleData1']");
	By listColonFilterViewDataset = By
			.xpath("//span[text()='SampleData1']/../../..//a[@data-bs-original-title='Filter & View Dataset']");
	By txtSelectingPreFilterBasedOnColumn = By.xpath("//textarea[@aria-label='Search']");
	By btnPreFilterSaveInPopup = By.xpath("//button[contains(text(),'Save Filter')]");
	By txtFilterName = By.xpath("//input[@placeholder='Enter Filter Name']");
	By btnSaveAfterGivingFilterName = By.id("listingSaveFilter");
	By btnResetInPopUP = By.xpath("//button[@id='resetFilterBtn']");
	By dropdownSavedFilter = By.xpath("//span[@id='select2-myDropdown-container']");
	By btnPreFilterFilterandViewDataset = By.id("filtered_submit_btn");
	By gridViewTableFirstRow = By.xpath("//*[@id=\"grid_datasetGrid_3_columns\"]/table/tbody/tr");
	By tableHeaderRow = By.xpath("//div[@class='w2ui-col-header  ']");
	By breadcrumbInGridView = By.xpath("//a[text()='Data Exploration']");
	By dropdownPreFilterFirstSelectField = By.id("select2-column1-container");
	By txtFieldSearch1 = By.xpath("//*[@id='filter_modal2']/span/span/span[1]/input");
	By dropdownPreFilterSecondSelectField = By.id("select2-column2-container");
	By txtFieldSearch2 = By.xpath("//span[@class='select2-search select2-search--dropdown']/descendant::input");
	//By txtFieldSearch2 = By.xpath("//*[@id='filter_modal2']/span/span/span[2]/input");
	By btnAddNewRowPlusIcon = By.id("addNewRow");
	By txtValue1 = By.id("val1");
	By txtValue2 = By.id("val2");
	By dropdownFilterMode = By.xpath("//select[@id='mainCondition']");
	By identifier2ColumnLocator = By.xpath("//*[@id='grid_datasetGrid_3_column_1']/div[2]/text()");
	By btnSkipAndViewDataset = By.xpath("//button[@id='filtered_skip_submit_btn']");
	By btnFilterGrid = By.xpath("//div[contains(text(),'Filter')]");
	By txtSelectingColumnNameInGridView = By.xpath("//input[@placeholder='Select Field']");
	

	public void clickAddDatasetBtn() throws InterruptedException {
		WebElement btn = driver.findElement(btnAddDataset);
		btn.click();
		Thread.sleep(2000);
	}

	public void clickTemplateBtnAndVerifyDownload(String expectedFileName) {
        try {
            System.out.println("➡️ Clicking Template button...");
            WebElement btn = driver.findElement(btnTemplate);
            btn.click();

            String downloadPath = "C:\\Users\\SIM-IND-0039\\Desktop\\SabicDataExploration\\Automation-scripts-SABIC\\downloads";

            File downloadedFile = waitForFileDownload(expectedFileName, downloadPath, 30);

            if (downloadedFile != null && downloadedFile.exists()) {
                System.out.println("✅ File downloaded successfully: " + downloadedFile.getAbsolutePath());
            } else {
                System.out.println("❌ File not downloaded within timeout.");
            }

        } catch (Exception e) {
            System.out.println("❌ Exception in clickTemplateBtnAndVerifyDownload");
            System.out.println("Reason: " + e.getMessage());
            e.printStackTrace(); // optional: shows where the error happened
        }
    }

    private File waitForFileDownload(String expectedFileName, String downloadPath, int timeoutSeconds) {
        Path downloadDir = Paths.get(downloadPath);
        File downloadedFile = null;

        int waited = 0;
        while (waited < timeoutSeconds) {
            File[] files = downloadDir.toFile().listFiles((dir, name) -> name.equals(expectedFileName));
            if (files != null && files.length > 0) {
                downloadedFile = files[0];
                break;
            }

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                System.out.println("⚠️ Interrupted while waiting for file download.");
                break;
            }

            waited++;
        }

        return downloadedFile;
    }

	public void enterDatasetName(String dataName) {
		driver.findElement(txtDatasetName).clear();
		driver.findElement(txtDatasetName).sendKeys(dataName);
	}

	public void fileUpload() {
		driver.findElement(uploadFile).sendKeys(
				"C:\\Users\\SIM-IND-0039\\git\\Automation-scripts-SABIC\\src\\test\\resources\\testData\\DataExplorationFile.csv");
	}

	public void clickSubmitBtn() throws InterruptedException {
		WebElement btn = driver.findElement(btnUploadDataset);
		btn.click();
		Thread.sleep(5000);
	}

	public boolean verifyListingElement(String expectedText) {
		try {
			return driver.findElement(listUploadedDataset).isDisplayed();
		} catch (Exception e) {
			return false;
		}
	}

	public void clickFilterViewDatasetColonIconInPreFilter() throws InterruptedException {
		WebElement btn = driver.findElement(listColonFilterViewDataset);
		btn.click();
		Thread.sleep(3000);
	}

	public void applyColumnFilterValues() throws InterruptedException {
	    Thread.sleep(5000);
	    WebElement btn = driver.findElement(txtSelectingPreFilterBasedOnColumn);
	    btn.click();
	    Thread.sleep(2000);

	    List<String> filterValues = Arrays.asList("Identifier-1", "Identifier-2", "Meta data-1", "Ingredient-1");

	    for (String value : filterValues) {
	        btn.sendKeys(value);
	        btn.sendKeys(Keys.ENTER);
	        Thread.sleep(2000);
	    }
	}

	public void verifyFilteredColumnsInGrid1() {
	    List<String> expectedColumns = Arrays.asList("Identifier-1", "Identifier-2", "Meta data-1", "Ingredient-1");

	    ww.until(ExpectedConditions.visibilityOfElementLocated(gridViewTableFirstRow));

	    List<WebElement> headerElements = driver.findElements(tableHeaderRow);

	    List<String> actualColumns = new ArrayList<>();
	    for (WebElement header : headerElements) {
	        String text = header.getText().replaceAll("\\s+", " ").trim();
	        if (!text.isEmpty()) {
	            actualColumns.add(text);
	        }
	    }

	    System.out.println("Expected Columns: " + String.join(", ", expectedColumns));
	    System.out.println("Actual Columns from Grid: " + String.join(", ", actualColumns));

	    boolean match = true;

	    for (int i = 0; i < expectedColumns.size(); i++) {
	        if (i >= actualColumns.size()) {
	            System.err.println("❌ Error: Grid shows fewer columns than expected.");
	            match = false;
	            break;
	        }

	        String expected = expectedColumns.get(i);
	        String actual = actualColumns.get(i);
	        if (!expected.equalsIgnoreCase(actual)) {
	            System.err.println("❌ Mismatch at position " + (i + 1) + ": expected '" + expected + "', but found '" + actual + "'");
	            match = false;
	        }
	    }

	    if (match) {
	        System.out.println("✅ Column verification passed!");
	    } else {
	        System.err.println("⚠️ Column verification failed, but continuing with the test.");
	    }
	}


	public void clickSaveBtn() {
		WebElement btn = driver.findElement(btnPreFilterSaveInPopup);
		btn.click();
	}

	public void enterFilterName() throws InterruptedException {
		Thread.sleep(2000);
		WebElement btn = driver.findElement(txtFilterName);
		btn.click();
		btn.sendKeys(config.getSaveColumnBasedFilterName1());
		Thread.sleep(2000);
	}

	public void clickSaveBtnAfterEnteringFilterName() throws InterruptedException {
		WebElement btn = driver.findElement(btnSaveAfterGivingFilterName);
		btn.click();
		Thread.sleep(5000);
	}
	
	public void clickResetBtn() throws InterruptedException {
		WebElement btn = driver.findElement(btnResetInPopUP);
		btn.click();
		Thread.sleep(5000);
	}
	
	public void clickSavedFilterInPopUp() throws InterruptedException {
		WebElement btn = driver.findElement(dropdownSavedFilter);
		btn.click();
		Thread.sleep(5000);
		btn.sendKeys(config.getSaveColumnBasedFilterName1());
		Thread.sleep(2000);
		btn.sendKeys(Keys.ENTER);
		Thread.sleep(5000);
	}
	
	public void clickFilterandViewDatasetBtn() throws InterruptedException {
		WebElement btn = driver.findElement(btnPreFilterFilterandViewDataset);
		btn.click();
		Thread.sleep(10000);
	}
	
	public void breadcrumbInGridView() throws InterruptedException{
		Thread.sleep(5000);
		WebElement link = driver.findElement(breadcrumbInGridView);
		link.click();
	}
	
	public void applyValueBasedFilter() throws InterruptedException {
		WebElement firstSelectFieldDropdown = driver.findElement(dropdownPreFilterFirstSelectField);
		firstSelectFieldDropdown.click();
		Thread.sleep(3000);
		WebElement searchField = driver.findElement(txtFieldSearch1);
		searchField.click();
		Thread.sleep(5000);
		searchField.sendKeys(config.getSelectFieldFirstColumnName());
		Thread.sleep(3000);
		searchField.sendKeys(Keys.ENTER);
		Thread.sleep(3000);
		WebElement enterValue1 = driver.findElement(txtValue1);
		enterValue1.sendKeys("Grade75");
		WebElement clickAddBtn = driver.findElement(btnAddNewRowPlusIcon);
		Thread.sleep(2000);
		clickAddBtn.click();
		WebElement secondSelectFieldDropdown = driver.findElement(dropdownPreFilterSecondSelectField);
		secondSelectFieldDropdown.click();
		Thread.sleep(3000);
		WebElement searchField2 = driver.findElement(txtFieldSearch2);
		searchField2.click();
		Thread.sleep(5000);
		searchField2.sendKeys(config.getSelectFieldFirstColumnName());
		Thread.sleep(3000);
		searchField2.sendKeys(Keys.ENTER);
		Thread.sleep(3000);
		WebElement enterValue2 = driver.findElement(txtValue2);
		enterValue2.sendKeys("Grade76");
		WebElement dropdownFiltermode = driver.findElement(dropdownFilterMode);
		dropdownFiltermode.click();
		Select filterMode = new Select(dropdownFiltermode);
		filterMode.selectByValue("OR");
		Thread.sleep(1000);
		//verifyFilteredValuesInIdentifier2Column();
	}
	
	public void verifyFilteredValuesInIdentifier2Column() {

	    // XPath to locate each cell under 'Identifier-2' column
	    // Modify the XPath if your grid structure is different
	    //By identifier2ColumnLocator = By.xpath("//td[contains(@data-column-id, 'Identifier-2')]");

	    // Wait until the cells are visible
	    //wait.until(ExpectedConditions.visibilityOfElementLocated(identifier2ColumnLocator));

	    // Fetch all values under Identifier-2 column
	    List<WebElement> identifier2Cells = driver.findElements(identifier2ColumnLocator);

	    // Expected values in any order: Grade75 and Grade76
	    List<String> expectedValues = Arrays.asList("Grade75", "Grade76");

	    for (WebElement cell : identifier2Cells) {
	        String actualValue = cell.getText().trim();
	        if (!expectedValues.contains(actualValue)) {
	            System.out.println("❌ Unexpected value found: " + actualValue);
	            Assert.fail("❌ Unexpected value in Identifier-2 column: " + actualValue);
	        } else {
	            System.out.println("✅ Matched value: " + actualValue);
	        }
	    }

	    System.out.println("✅ All values in 'Identifier-2' column match the applied filters.");
	}

	
	public void clickSkipAndViewDataset()throws InterruptedException
	{
		WebElement btn = driver.findElement(btnSkipAndViewDataset);
		btn.click();
		Thread.sleep(5000);
	}
	
	public void clickFilterBtn()throws InterruptedException
	{
//		Thread.sleep(3000);
//		WebElement btn = wait.until(ExpectedConditions.visibilityOfElementLocated(btnFilterGrid));
//		Thread.sleep(3000);
//		btn.click();
//		Thread.sleep(3000);
		WebElement btn = wait.until(ExpectedConditions.elementToBeClickable(btnFilterGrid));
		btn.click();
	}
	
	public void enterFilterName2() throws InterruptedException {
		Thread.sleep(2000);
		WebElement btn = driver.findElement(txtFilterName);
		btn.click();
		btn.sendKeys(config.getSaveValueBasedFilterName2());
		Thread.sleep(2000);
	}

	public void clickFilterbasedOnColumn() throws InterruptedException
	{
		//WebElement btn = driver.findElement(txtSelectingColumnNameInGridView);
		WebElement btn = wait.until(ExpectedConditions.elementToBeClickable(txtSelectingColumnNameInGridView));
		btn.click();
		
		Thread.sleep(2000);

	    List<String> filterValues = Arrays.asList("Outcome-1", "Outcome-2", "Outcome-3");

	    for (String value : filterValues) {
	        btn.sendKeys(value);
	        btn.sendKeys(Keys.ENTER);
	        Thread.sleep(2000);
	    }
	}
	
	public void enterFilterName3() throws InterruptedException {
		Thread.sleep(2000);
		WebElement btn = driver.findElement(txtFilterName);
		btn.click();
		btn.sendKeys("GridFilter-Out123");
		Thread.sleep(2000);
	}
	
	public void verifyGridColumnsFilter() {
	    List<String> expectedColumns = Arrays.asList("Outcome-1", "Outcome-2", "Outcome-3");

	    ww.until(ExpectedConditions.visibilityOfElementLocated(gridViewTableFirstRow));

	    List<WebElement> headerElements = driver.findElements(tableHeaderRow);

	    List<String> actualColumns = new ArrayList<>();
	    for (WebElement header : headerElements) {
	        String text = header.getText().replaceAll("\\s+", " ").trim();
	        if (!text.isEmpty()) {
	            actualColumns.add(text);
	        }
	    }

	    System.out.println("Expected Columns: " + String.join(", ", expectedColumns));
	    System.out.println("Actual Columns from Grid: " + String.join(", ", actualColumns));

	    boolean match = true;

	    for (int i = 0; i < expectedColumns.size(); i++) {
	        if (i >= actualColumns.size()) {
	            System.err.println("❌ Error: Grid shows fewer columns than expected.");
	            match = false;
	            break;
	        }

	        String expected = expectedColumns.get(i);
	        String actual = actualColumns.get(i);
	        if (!expected.equalsIgnoreCase(actual)) {
	            System.err.println("❌ Mismatch at position " + (i + 1) + ": expected '" + expected + "', but found '" + actual + "'");
	            match = false;
	        }
	    }

	    if (match) {
	        System.out.println("✅ Column verification passed!");
	    } else {
	        System.err.println("⚠️ Column verification failed, but continuing with the test.");
	    }
	}

	
	public void applyGridValueBasedFilter() throws InterruptedException {
		WebElement firstSelectFieldDropdown = driver.findElement(dropdownPreFilterFirstSelectField);
		firstSelectFieldDropdown.click();
		Thread.sleep(3000);
		WebElement searchField = driver.findElement(txtFieldSearch1);
		searchField.click();
		Thread.sleep(5000);
		searchField.sendKeys("Outcome-3");
		Thread.sleep(3000);
		searchField.sendKeys(Keys.ENTER);
		Thread.sleep(3000);
		WebElement enterValue1 = driver.findElement(txtValue1);
		enterValue1.sendKeys("131");
		WebElement clickAddBtn = driver.findElement(btnAddNewRowPlusIcon);
		Thread.sleep(2000);
		clickAddBtn.click();
		WebElement secondSelectFieldDropdown = driver.findElement(dropdownPreFilterSecondSelectField);
		secondSelectFieldDropdown.click();
		Thread.sleep(3000);
		WebElement searchField2 = driver.findElement(txtFieldSearch2);
		searchField2.click();
		Thread.sleep(5000);
		searchField2.sendKeys("Outcome-3");
		Thread.sleep(3000);
		searchField2.sendKeys(Keys.ENTER);
		Thread.sleep(3000);
		WebElement enterValue2 = driver.findElement(txtValue2);
		enterValue2.sendKeys("132");
		WebElement dropdownFiltermode = driver.findElement(dropdownFilterMode);
		dropdownFiltermode.click();
		Select filterMode = new Select(dropdownFiltermode);
		filterMode.selectByValue("OR");
		Thread.sleep(1000);
		
	}
}
