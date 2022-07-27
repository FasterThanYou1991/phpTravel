package phptravel.demo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import phptravel.demo.model.User;
import phptravel.demo.tests.BaseTest;



public class SignUpPage extends BaseTest {

    @FindBy(xpath = "//a[@href='https://phptravels.net/signup']")
    private WebElement signupButton;
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

    @FindBy(xpath = "//button[@type='submit']")
    private WebElement buttonSubmit;


    private WebDriver driver;

    public SignUpPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    public void waitMethod(String xpath) {
        WebDriverWait wait = new WebDriverWait(driver, 5);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
    }

    public void openSignUpForm() {
        waitMethod("//a[@href='https://phptravels.net/signup']");
        signupButton.click();
    }

    public void accountTypeDropdown(String type) {
        waitMethod("//span[@id='select2-account_type-container']");
        accountType.click();
        String xpath = String.format("//li[contains(text(),'%s')]", type);
        waitMethod(xpath);
        driver.findElement(By.xpath(xpath)).click();
    }

    public void performSubmit() {
        buttonSubmit.click();
    }

    public void fillSignUpForm(String firstName, String lastName, String phone, String email, String password, String accountType) {
        firstNameInput.sendKeys(firstName);
        lastNameInput.sendKeys(lastName);
        phoneInput.sendKeys(phone);
        emailInput.sendKeys(email);
        passwordInput.sendKeys(password);
        accountTypeDropdown(accountType);
        performSubmit();
    }

    public void fillSignUpForm(User user) {
        firstNameInput.sendKeys(user.getFirstName());
        lastNameInput.sendKeys(user.getLastName());
        phoneInput.sendKeys(user.getPhone());
        emailInput.sendKeys(user.getEmail());
        passwordInput.sendKeys(user.getPassword());
        //accountTypeDropdown(accountType);
        buttonSubmit.click();
    }
}


