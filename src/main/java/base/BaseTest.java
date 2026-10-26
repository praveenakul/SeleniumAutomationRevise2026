package base;

import org.testng.annotations.AfterMethod;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeMethod;
import utilities.ConfigReader;

public class BaseTest {
     protected WebDriver driver;

    @BeforeMethod
    public void setup(){
        ConfigReader.loadProperties();

        String browser = ConfigReader.getProperty("browser");

        if (browser == null) {
            throw new RuntimeException("Browser is not configured in config.properties");
        }

        if (browser.equalsIgnoreCase("chrome")){
            driver = new ChromeDriver();
        }else {
            throw  new IllegalArgumentException("browser not supported:"+browser);
        }
        driver.manage().window().maximize();
        driver.get(ConfigReader.getProperty("url"));
    }
    @AfterMethod
    public void tearDown(){
        if(driver != null){
            driver.quit();
        }
    }

}
