package phptravel.demo.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import phptravel.demo.tests.BaseTest;
import phptravel.demo.utils.SeleniumHelper;

import java.util.List;


public class LoginPage extends BaseTest {

    @FindBy(xpath = "//input[@name='email' and @placeholder='Email']")
    private WebElement emailLoginInput;

    @FindBy(name = "password")
    private WebElement passwordLoginInput;

    @FindBy(xpath = "//span[text()='Login']")
    private WebElement loginButton;

    @FindBy(xpath = "//h5[@class='modal-title title']")
    private WebElement loginHeader;

    private WebDriver driver;
    private static final org.apache.logging.log4j.Logger Logger = LogManager.getLogger();
    public LoginPage(WebDriver driver){
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    public void getLoginHeader(){
        waitMethod("//h5[@class='modal-title title']", driver);
        Logger.info("We're on the login page: " + loginHeader.getText()) ;
    }
    public void setEmailLoginInput(String email){
        emailLoginInput.sendKeys(email);
        Logger.info("Email is entered: ");
    }
    public void setPasswordLoginInput(String password){
        passwordLoginInput.sendKeys(password);
        Logger.info("Password is entered: ");
    }

    public void performLogin(){
        loginButton.click();
    }
}