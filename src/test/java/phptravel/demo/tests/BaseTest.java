package phptravel.demo.tests;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import phptravel.demo.utils.DriverFactory;
import phptravel.demo.utils.SeleniumHelper;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class BaseTest extends SeleniumHelper {

    public WebDriver driver;
    String username = "amadeusz01";
    String accesskey = "fkU5GhWLpB70VFNmdDkSqzzrNFDhJ0b5uHrrl4Jf2Oqh1bX2bd";
    public String gridURL = "@hub.lambdatest.com/wd/hub";

    //these fields are here, cuz we can use htmlReporter and extentReports in separate methods.
    protected static ExtentHtmlReporter htmlReporter;
    protected static ExtentReports extentReports;

    @BeforeSuite
    //public method responsible for creating report in html file.
    public void beforeSuit(){
        htmlReporter = new ExtentHtmlReporter("index.html");
        extentReports = new ExtentReports();
        extentReports.attachReporter(htmlReporter);
    }

    @AfterSuite
    //this method have to be to close the report
    public void afterSuit(){
        htmlReporter.flush();
        extentReports.flush();
    }

    @BeforeTest
    public void setup() throws IOException {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("browserName", "chrome");
        capabilities.setCapability("version", "100.0");
        capabilities.setCapability("platform",""); // If this cap isn't specified, it will just get the any available one
        capabilities.setCapability("build", "CaptchaInSelenium");
        capabilities.setCapability("name", "TCaptchaInSeleniumSample");
        try {
            driver = new RemoteWebDriver(new URL("https://" + username + ":" + accesskey + gridURL), capabilities);
        } catch (MalformedURLException e) {
            System.out.println("Invalid grid URL");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        driver = DriverFactory.getDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.manage().deleteAllCookies();
        driver.get("https://phptravels.net/hotels");
    }
    @BeforeMethod
    public void languageCheck(){
        WebElement asd = driver.findElement(By.id("languages"));
        asd.click();
        driver.findElement(By.xpath("//a[@href='https://phptravels.net/lang-en']")).click();
    }

    //@AfterMethod
    public void tearDown(){
        driver.quit();
    }
}