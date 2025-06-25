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
	        System.out.println("Generating Allure report...");

	        // Always generate report
	        ProcessBuilder generateReport = new ProcessBuilder(
	            "C:\\allure-2.34.0\\bin\\allure.bat",
	            "generate",
	            "allure-results",
	            "--clean",
	            "-o",
	            "target/allure-report"
	        );
	        generateReport.inheritIO();
	        Process genProcess = generateReport.start();
	        genProcess.waitFor();
	        System.out.println("Allure report generated successfully.");

	        // Open report in browser only in local (Eclipse/Terminal), not in Jenkins
	        if (System.getenv("JENKINS_HOME") == null && System.getenv("CI") == null) {
	            System.out.println("Opening Allure report in browser...");
	            ProcessBuilder openReport = new ProcessBuilder(
	                "C:\\allure-2.34.0\\bin\\allure.bat",
	                "open",
	                "target/allure-report"
	            );
	            openReport.inheritIO();
	            openReport.start(); 
	        } else {
	            System.out.println("Detected Jenkins environment — skipping browser launch.");
	        }

	    } catch (Exception e) {
	        e.printStackTrace();
	        System.out.println("Failed to generate or open Allure report.");
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
