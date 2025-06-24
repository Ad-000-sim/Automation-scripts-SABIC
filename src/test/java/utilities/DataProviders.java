package utilities;

import java.io.IOException;

import org.testng.ITestContext;
import org.testng.annotations.DataProvider;

public class DataProviders {
    
//	 @DataProvider(name = "LoginData")
//	    public static Object[][] getLoginData() throws IOException {
//	        String filePath = System.getProperty("user.dir") + "/src/test/resources/testData/loginData.xlsx";
//	        return ExcelUtilities.getExcelData(filePath, "Sheet1"); // now returns all 3 columns
//	    }
//	 
	 
	 @DataProvider(name = "ExcelDataProvider")
	 public static Object[][] getExcelData(ITestContext context) throws IOException {
	     String fileName = context.getCurrentXmlTest().getParameter("excelFile");
	     String sheetName = context.getCurrentXmlTest().getParameter("sheetName");

	     String filePath = System.getProperty("user.dir") + "/src/test/resources/testData/" + fileName;
	     return ExcelUtilities.getExcelData(filePath, sheetName);
	 }
}
    

    // Convert 3-column data to 2-column if needed
//  Object[][] loginData = new Object[allData.length][2];
//  for (int i = 0; i < allData.length; i++) {
//      loginData[i][0] = allData[i][0]; // username
//      loginData[i][1] = allData[i][1]; // password
//  }
//  return loginData;
