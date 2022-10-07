package phptravel.demo.tests;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.FluentWait;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import phptravel.demo.utils.DriverFactory;
import phptravel.demo.utils.SeleniumHelper;

import java.io.IOException;
import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

public class BaseTest extends SeleniumHelper {

    public WebDriver driver;

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
        driver = DriverFactory.getDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.get("https://phptravels.net/hotels");
    }
    //@BeforeMethod
    public void languageCheck(){
        WebElement asd = driver.findElement(By.id("languages"));
        asd.click();
        driver.findElement(By.xpath("//a[@href='https://phptravels.net/lang-en']")).click();
    }
    public void waitForElementToExist(By locator) {
        FluentWait<WebDriver> wait = new FluentWait<>(driver);
        wait.ignoring(NoSuchElementException.class);
        wait.withTimeout(Duration.ofSeconds(5));
        wait.until(new Function<WebDriver, Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                List<WebElement> elements = driver.findElements(locator);
                if (elements.size() > 0) {
                    System.out.println("Element jest na stronie");
                    return true;
                } else {
                    System.out.println("Nie ma na stronie tego elementu");
                    return false;
                }
            }
        });
    }
    //@AfterMethod
    public void tearDown(){
        driver.quit();
    }
}