package phptravel.demo.pages;

import com.microsoft.cognitiveservices.speech.ResultReason;
import com.microsoft.cognitiveservices.speech.SpeechConfig;
import com.microsoft.cognitiveservices.speech.SpeechRecognizer;
import com.microsoft.cognitiveservices.speech.audio.AudioConfig;
import com.microsoft.cognitiveservices.speech.audio.AudioProcessingConstants;
import com.microsoft.cognitiveservices.speech.audio.AudioProcessingOptions;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import phptravel.demo.tests.BaseTest;
import phptravel.demo.utils.DriverFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;


public class SignUpPage extends BaseTest {

    private SpeechConfig config = SpeechConfig.fromSubscription("c5f183bc0c084b85a9d61e7bb5be626c", "francecentral");


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

    @FindBy(id = "cookie_stop")
    private WebElement cookie;

    @FindBy(xpath = "//span[@class='select2-selection select2-selection--single']")
    private WebElement accountType;

    @FindBy(xpath = "//div[@class='recaptcha-checkbox-border']")
    private WebElement captchaCheckbox;

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

    public LoginPage selectCustomerSignup() {
        waitMethod("//a[@href='https://phptravels.net/signup']", driver);
        customerSignup.click();
        return new LoginPage(driver);
    }

    public SignUpPage setFirstName(String firstName) {
        String firstNameText = "Tester";
        firstNameInput.sendKeys(firstNameText + firstName);
        return this;
    }

    public SignUpPage setLastName(String lastName) {
        String lastNameText = "Testowy";
        lastNameInput.sendKeys(lastNameText + lastName);
        return this;
    }

    public SignUpPage setPhoneInput(String phone) {
        phoneInput.sendKeys(phone);
        return this;
    }

    public SignUpPage setEmailInput(String email) {
        int randomNumber = (int) (Math.random() * 1000);
        String randomEmail = "tester" + randomNumber + "@wp.pl";
        emailInput.sendKeys(randomEmail);
        return this;
    }

    public SignUpPage setPasswordInput(String password) {
        passwordInput.sendKeys(password);
        return this;
    }

    public void acceptCookie() {
        waitForElementToExist(driver, cookie);
        cookie.click();
    }

    public void accountTypeDropdown(String type) {
        waitClickAtPoint(driver, accountType);
        waitMethod("//span[@class='select2-selection select2-selection--single']");
        accountType.click();
        String xpath = String.format("//li[contains(text(),'%s')]", type);
        waitMethod(xpath);
        driver.findElement(By.xpath(xpath)).click();
    }


    public void selectCheckboxRecaptcha() {
        WebDriverWait wait = new WebDriverWait(driver, 5);
        try {
            wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.xpath("//iframe[@title='reCAPTCHA']")));
            wait.until(ExpectedConditions.elementToBeClickable(captchaCheckbox));
            captchaCheckbox.click();
        } catch (Exception e) {
            System.out.println("Error from Code: " + e.getMessage());
        }
        waitMethod("//div[@class='recaptcha-checkbox-border']");
        captchaCheckbox.click();

    }

    public void iFrameCaptcha() throws InterruptedException {
        List<WebElement> iframe = driver.findElements(By.tagName("iframe"));
        int numbersOfTags = iframe.size();
        System.out.println("Number of iframes: " + numbersOfTags);

        if(iframe.size() > 0){
            driver.switchTo().defaultContent();
            WebDriverWait wait = new WebDriverWait(driver, 5);
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.xpath("//iframe[@title='recaptcha challenge expires in two minutes' and @name='c-zln40izj5c9']")));
        WebElement audioOptionButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@title='Get an audio challenge']")));
        waitClickAtPoint(driver,audioOptionButton);
            audioOptionButton.click();

            SpeechToText();

            WebElement inputCaptcha = driver.findElement(By.id("audio-response"));
            inputCaptcha.sendKeys();

            WebElement verifyButton = driver.findElement(By.id("recaptcha-verify-button"));
            verifyButton.click();

            driver.switchTo().defaultContent();
            WebElement submitButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("wpforms-submit-3347")));
            submitButton.click();
        }else{
            performSubmit();
        }
    }
    public void SpeechToText() throws InterruptedException {
        ((JavascriptExecutor)driver).executeScript("window.open()");
        ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(1));
        driver.get("https://azure.microsoft.com/en-us/products/cognitive-services/speech-to-text/#features");
        WebElement speakButton = driver.findElement(By.id("speakbtn"));
        waitClickAtPoint(driver, speakButton);
        driver.switchTo().parentFrame();
        WebElement playButton = driver.findElement(By.xpath("//button[text()='PLAY']"));
        waitClickAtPoint(driver,playButton);
        Thread.sleep(10000);
        driver.switchTo().window(tabs.get(1));
        WebElement getTextMessageFromInput = driver.findElement(By.id("speechout"));
        getTextMessageFromInput.getText();



    }

    public SignUpPage performSubmit() {
        waitClickAtPoint(driver,buttonSubmit);
        buttonSubmit.click();
        return this;
    }
}
