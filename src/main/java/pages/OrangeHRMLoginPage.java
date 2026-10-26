package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class OrangeHRMLoginPage {
    WebDriver driver;
    WebDriverWait wait;

    @FindBy(name="username")
    WebElement txt_username;

    @FindBy(name="password")
    WebElement txt_password;

    @FindBy(xpath = "//button[@type='submit']")
    WebElement btn_submit;

    public OrangeHRMLoginPage(WebDriver driver){
        this.driver= driver;
        PageFactory.initElements(driver,this);
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    public void enterUsername(String name){
        wait.until(ExpectedConditions.visibilityOf(txt_username)).sendKeys(name);
    }
    public void enterPassword(String pass){
        wait.until(
                ExpectedConditions.visibilityOf(txt_password)
        ).sendKeys(pass);
    }
    public void clickLogin(){
        wait.until(
                ExpectedConditions.elementToBeClickable(btn_submit)
        ).click();
    }
}
