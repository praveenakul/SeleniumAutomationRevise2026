package Tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.OrangeHRMLoginPage;
import utilities.ExcelReader;

public class OrangeHRMLoginTest extends BaseTest {

    @DataProvider(name = "loginData")

    public Object[][] loginData(){
        return ExcelReader.getExcelData("src/test/resources/Book1.xlsx","LoginPage");
    }
    @Test(dataProvider = "loginData")
    public void loginTest(String username,String password,String expectedResult) throws InterruptedException {

        OrangeHRMLoginPage login = new OrangeHRMLoginPage(driver);

        login.enterUsername(username);
        login.enterPassword(password);
        login.clickLogin();

        Thread.sleep(20000);

        String currentUrl= driver.getCurrentUrl();

        if (password.equals("admin123")){
            Assert.assertTrue(currentUrl.contains("dashboard"),"Login Failed");
        }else {
            Assert.assertFalse(currentUrl.contains("dashboard"),"Invalod Login Unexpedtedly suceeded");
        }
    }
}
