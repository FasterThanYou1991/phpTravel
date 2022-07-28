package phptravel.demo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;


public class LoginPage extends SignUpPage {

    @FindBy(xpath = "//a[@href='https://phptravels.net/login']")
        private List<WebElement> login;

    @FindBy(name = "email")
        private WebElement emailLoginInput;

    @FindBy(name = "password")
        private WebElement passwordLoginInput;

    @FindBy(xpath = "//span[text()='Login']")
        private WebElement loginButton;

    private WebDriver driver;

    public LoginPage(WebDriver driver){
        super(driver);
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }
    public void waitMethod(String xpath){
        WebDriverWait wait = new WebDriverWait(driver, 5);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
    }
    public void openLoginPage(){
        login
                .stream()
                .filter(WebElement::isDisplayed)
                .findFirst()
                .ifPresent(WebElement::click);
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
