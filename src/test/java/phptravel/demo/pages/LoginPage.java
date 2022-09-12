package phptravel.demo.pages;

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

    private WebDriver driver;

    public LoginPage(WebDriver driver){
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    public void setEmailLoginInput(String email){
        emailLoginInput.sendKeys(email);
    }
    public void setPasswordLoginInput(String password){
        passwordLoginInput.sendKeys(password);
    }

    public void performLogin(){
        loginButton.click();
    }
}