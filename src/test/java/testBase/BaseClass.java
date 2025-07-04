	package testBase;

import java.io.File;
import java.time.Duration;
import java.util.HashMap;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;

import io.qameta.allure.testng.AllureTestNg;
import utilities.ConfigReader;

@Listeners({ AllureTestNg.class })
public class BaseClass {

	protected static WebDriver driver;
	protected ConfigReader config;
	public Logger lg;
	JavascriptExecutor js;

	public static WebDriver getDriver() {
		return driver;
	}

	@BeforeSuite(alwaysRun = true)
	@Parameters({ "os", "browser" })
	public void launch(String os, String br) {
		lg = LogManager.getLogger(this.getClass());

		// 1. Define a download path for all browsers
		String downloadPath = "C:\\Users\\SIM-IND-0039\\Desktop\\SabicDataExploration\\Automation-scripts-SABIC\\downloads";
		new File(downloadPath).mkdirs(); // ensure folder exists

		switch (br.toLowerCase()) {
		case "chrome":
			HashMap<String, Object> chromePrefs = new HashMap<>();
			chromePrefs.put("download.default_directory", downloadPath);
			chromePrefs.put("download.prompt_for_download", false);
			chromePrefs.put("profile.default_content_settings.popups", 0);

			ChromeOptions chromeOptions = new ChromeOptions();
			chromeOptions.setExperimentalOption("prefs", chromePrefs);
			driver = new ChromeDriver(chromeOptions);
			break;

		case "firefox":
			FirefoxOptions firefoxOptions = new FirefoxOptions();
			firefoxOptions.addPreference("browser.download.folderList", 2);
			firefoxOptions.addPreference("browser.download.dir", downloadPath);
			firefoxOptions.addPreference("browser.helperApps.neverAsk.saveToDisk",
					"application/octet-stream,application/vnd.ms-excel,application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
			firefoxOptions.addPreference("pdfjs.disabled", true);
			driver = new FirefoxDriver(firefoxOptions);
			break;

		case "edge":
			HashMap<String, Object> edgePrefs = new HashMap<>();
			edgePrefs.put("download.default_directory", downloadPath);
			edgePrefs.put("download.prompt_for_download", false);

			EdgeOptions edgeOptions = new EdgeOptions();
			edgeOptions.setExperimentalOption("prefs", edgePrefs);
			driver = new EdgeDriver(edgeOptions);
			break;

		default:
			System.out.println("⚠️ Invalid browser specified: " + br);
		}

		config = new ConfigReader();
		driver.manage().window().maximize();
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

		String baseUrl = config.getBaseURL();
		driver.get(baseUrl);
	}

	@AfterSuite
	public void tearDown() {
		if (driver != null) {
			driver.quit();
		}
	}
}
