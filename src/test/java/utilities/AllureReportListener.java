package utilities;
import java.io.File;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.IExecutionListener;
import org.testng.ITestListener;
import org.testng.ITestResult;
import java.io.ByteArrayInputStream;

import io.qameta.allure.Allure;
import testBase.BaseClass;

public class AllureReportListener implements ITestListener, IExecutionListener {

	@Override
    public void onExecutionFinish() {
		 try {
	            System.out.println("Generating and launching Allure report...");

	            // On Windows, provide full path to allure.bat
	            ProcessBuilder pb = new ProcessBuilder("C:\\allure-2.34.0\\bin\\allure.bat", "serve", "allure-results");
	            pb.inheritIO();
	            Process process = pb.start();
	            process.waitFor();

	            System.out.println("Allure report generated and opened successfully.");
	        } catch (Exception e) {
	            e.printStackTrace();
	            System.out.println("Failed to generate/open Allure report.");
	        }
	    }
	@Override
	public void onTestFailure(ITestResult result) {
		 WebDriver driver = BaseClass.getDriver();
		try {
            // Capture screenshot
           // File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			 byte[] screenshotBytes = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);

             
             Allure.addAttachment("Screenshot on Failure", new ByteArrayInputStream(screenshotBytes));


            // Create timestamped filename
            String timestamp = new SimpleDateFormat("yyyy_MM_dd_HH-mm-ss").format(new Date());
            String testName = result.getName();
            String screenshotPath = "screenshots/" + testName + "_" + timestamp + ".png";

           
            File destFile = new File(screenshotPath);
            destFile.getParentFile().mkdirs();
            Files.write(destFile.toPath(), screenshotBytes);


            System.out.println(destFile.getAbsolutePath());
        } 
		catch(Exception e) {
			 System.err.println("Failed to save screenshot: " + e.getMessage());
		}
	}
    
	
	

	



}
