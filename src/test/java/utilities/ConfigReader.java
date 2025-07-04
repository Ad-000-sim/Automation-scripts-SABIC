package utilities;

import java.io.InputStream;
import java.util.Properties;

public class ConfigReader {

    private Properties prop;

    public ConfigReader() {
        prop = new Properties();
        try (InputStream input = getClass().getClassLoader().getResourceAsStream("testData/config.properties")) {
            if (input == null) {
                throw new RuntimeException("Unable to find config.properties");
            }
            prop.load(input);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to load config properties", e);
        }
    }

    public String getBaseURL() {
        return prop.getProperty("baseURL");
    }

    public String getUsername() {
        return prop.getProperty("username");
    }

    public String getPassword() {
        return prop.getProperty("password");
    }
    public String getrepoName1() {
    	return prop.getProperty("ReportName1");	
    }
    public String getrepoName2() {
    	return prop.getProperty("ReportName2");
    }
    public String getrepoName3() {
    	return prop.getProperty("ReportName3");
    }
    public String getrepoName4() {
    	return prop.getProperty("ReportName4");
    } 
    public String getrepoName5() {
    	return prop.getProperty("ReportName5");
    }
    public String getdatasetName() {
    	return prop.getProperty("DatasetName");
    }
    public String getSaveColumnBasedFilterName1() {
    	return prop.getProperty("SaveColumnBasedFilterName1");
    }
    public String getSelectFieldFirstColumnName() {
    	return prop.getProperty("SelectFieldFirstColumnName");
    }
    public String getSelectFieldSecondColumnName() {
    	return prop.getProperty("SelectFieldSecondColumnName");
    }
    public String getSaveValueBasedFilterName2() {
    	return prop.getProperty("SaveValueBasedFilterName2");
    }
}
