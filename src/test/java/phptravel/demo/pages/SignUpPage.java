package phptravel.demo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import phptravel.demo.tests.BaseTest;



public class SignUpPage extends BaseTest {

    @FindBy(id = "ACCOUNT")
    private WebElement accountDropdown;
    @FindBy(name = "first_name")
    private WebElement firstNameInput;

    @FindBy(name = "last_name")
    private WebElement lastNameInput;

    @FindBy(name = "phone")
    private WebElement phoneInput;

    @FindBy(name = "email")
    private WebElement emailInput;

    @FindBy(name = "password")
    private WebElement passwordInput;

    @FindBy(xpath = "//span[@id='select2-account_type-container']")
    private WebElement accountType;

    @FindBy(xpath = "//label")
    private WebElement reCaptcha;

    @FindBy(xpath = "//button[@type='submit']")
    private WebElement buttonSubmit;

    @FindBy(xpath = "//a[@href='https://phptravels.net/signup']")
    private WebElement customerSignup;


    private WebDriver driver;

    public SignUpPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    public void waitMethod(String xpath) {
        WebDriverWait wait = new WebDriverWait(driver, 5);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
    }

    public void selectAccountDropdown() {
        accountDropdown.click();
    }
    public LoginPage selectCustomerSignup(){
        waitMethod("//a[@href='https://phptravels.net/signup']",driver);
        customerSignup.click();
        return new LoginPage(driver);
    }

    public SignUpPage setFirstName(String firstName){
        String firstNameText = "Tester";
        firstNameInput.sendKeys(firstNameText + firstName);
        return this;
    }

    public SignUpPage setLastName(String lastName){
        String lastNameText = "Testowy";
        lastNameInput.sendKeys(lastNameText + lastName);
        return this;
    }

    public SignUpPage setPhoneInput(String phone){
        phoneInput.sendKeys(phone);
        return this;
    }

    public SignUpPage setEmailInput(String email){
        int randomNumber = (int) (Math.random()* 1000);
        String randomEmail = "tester" + randomNumber + "@wp.pl";
        emailInput.sendKeys(randomEmail);
        return this;
    }

    public SignUpPage setPasswordInput(String password){
        passwordInput.sendKeys(password);
        return this;
    }

    public void accountTypeDropdown(String type) {
        waitMethod("//span[@id='select2-account_type-container']");
        accountType.click();
        String xpath = String.format("//li[contains(text(),'%s')]", type);
        waitMethod(xpath);
        driver.findElement(By.xpath(xpath)).click();
    }

    public void selectCheckboxRecaptcha(){
            WebDriverWait wait = new WebDriverWait(driver, 5);
            wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.xpath("//iframe[@title='reCAPTCHA']")));
            WebElement captchaCheckbox = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='recaptcha-checkbox-border']")));
            captchaCheckbox.click();
        }



    public SignUpPage performSubmit() {
        buttonSubmit.click();
        return this;
    }
}


