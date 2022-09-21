package phptravel.demo.pages;

import com.microsoft.cognitiveservices.speech.ResultReason;
import com.microsoft.cognitiveservices.speech.SpeechConfig;
import com.microsoft.cognitiveservices.speech.SpeechRecognizer;
import com.microsoft.cognitiveservices.speech.audio.AudioConfig;
import com.microsoft.cognitiveservices.speech.audio.AudioProcessingConstants;
import com.microsoft.cognitiveservices.speech.audio.AudioProcessingOptions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import phptravel.demo.tests.BaseTest;

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
    public void acceptCookie(){
        waitForElementToExist(driver,cookie);
        cookie.click();
    }

    public void accountTypeDropdown(String type) {
        waitClickAtPoint(driver,accountType);
        waitMethod("//span[@class='select2-selection select2-selection--single']");
        accountType.click();
        String xpath = String.format("//li[contains(text(),'%s')]", type);
        waitMethod(xpath);
        driver.findElement(By.xpath(xpath)).click();
    }


    public void selectCheckboxRecaptcha() throws InterruptedException, ExecutionException {
        WebDriverWait wait = new WebDriverWait(driver, 5);
        try {
            wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.xpath("//iframe[@title='reCAPTCHA']")));
            wait.until(ExpectedConditions.elementToBeClickable(captchaCheckbox));
            captchaCheckbox.click();
        }catch (Exception e){
            System.out.println("Error from Code: " + e.getMessage());
        }
        waitMethod("//div[@class='recaptcha-checkbox-border']");
        captchaCheckbox.click();
        if(captchaCheckbox.isSelected()) {
            driver.switchTo().defaultContent();
            wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.xpath("//iframe[@title='recaptcha challenge expires in two minutes']")));
            WebElement audioOptionButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@title='Get an audio challenge']")));
            waitClickAtPoint(driver,audioOptionButton);
            audioOptionButton.click();

            var audioProcessingOptions = AudioProcessingOptions.create(AudioProcessingConstants.AUDIO_INPUT_PROCESSING_ENABLE_DEFAULT);
            var audioInput = AudioConfig.fromDefaultMicrophoneInput(audioProcessingOptions);
            List<String> recognizedSpeechParts = new ArrayList<>();
            SpeechRecognizer recognizer = new SpeechRecognizer(config, audioInput);
            {
                recognizer.recognized.addEventListener((s, e) -> {
                    if (e.getResult().getReason() == ResultReason.RecognizedSpeech) {
                        recognizedSpeechParts.add(e.getResult().getText());
                        System.out.println("RECOGNIZED: Text=" + e.getResult().getText());
                    } else if (e.getResult().getReason() == ResultReason.NoMatch) {
                        System.out.println("NOMATCH: Speech could not be recognized");
                    }
                });
                recognizer.startContinuousRecognitionAsync().get();
                var playButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='PLAY']")));
                playButton.click();
                Thread.sleep(10000);
                recognizer.stopContinuousRecognitionAsync().get();
            }
            config.close();
            audioInput.close();
            audioProcessingOptions.close();
            recognizer.close();

            var audioResponseInput = driver.findElement(By.id("audio-response"));
            var captchaText = String.join("", recognizedSpeechParts);
            audioResponseInput.sendKeys(captchaText);

            var verifyButton = driver.findElement(By.id("recaptcha-verify-button"));
            verifyButton.click();

            driver.switchTo().defaultContent();
            var submitButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("wpforms-submit-3347")));
            submitButton.click();
        }else{
            System.out.println("Captcha iframe was not visible");
        }
    }

    public SignUpPage performSubmit() {
        WebDriverWait wait = new WebDriverWait(driver, 5);
        try {
            wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.xpath("//button[@type='submit']")));
            wait.until(ExpectedConditions.elementToBeClickable(buttonSubmit));
            buttonSubmit.click();
        }catch (Exception e){
            System.out.println("Error from Code: " + e.getMessage());
        }
        buttonSubmit.click();
        return this;
    }
}


