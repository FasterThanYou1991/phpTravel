package phptravel.demo.tests;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.fasterxml.jackson.databind.deser.impl.ExternalTypeHandler;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.FluentWait;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import phptravel.demo.utils.DriverFactory;

import java.io.IOException;
import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

public class BaseTest {

    public WebDriver driver;

    protected static ExtentHtmlReporter htmlReporter;
    protected static ExtentReports extentReports;

    @BeforeSuite
    public void beforeSuit(){
        htmlReporter = new ExtentHtmlReporter("index.html");
        extentReports = new ExtentReports();
        extentReports.attachReporter(htmlReporter);
    }

    @AfterSuite
    public void afterSuit(){
        htmlReporter.flush();
        extentReports.flush();
    }

    @BeforeMethod
    public void setup() throws IOException {
        driver = DriverFactory.getDriver();
        driver.manage().window();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.get("https://phptravels.net/");
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
