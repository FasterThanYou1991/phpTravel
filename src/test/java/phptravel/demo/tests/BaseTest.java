package phptravel.demo.tests;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.FluentWait;
import org.testng.SkipException;
import org.testng.annotations.*;
import phptravel.demo.utils.DriverFactory;
import phptravel.demo.utils.SeleniumHelper;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.Duration;
import java.util.List;
import java.util.function.Function;

public class BaseTest extends SeleniumHelper {

    public WebDriver driver;

    //these fields are here, cuz we can use htmlReporter and extentReports in separate methods.
    protected static ExtentSparkReporter sparkReporter;
    protected static ExtentReports extentReports;

    protected ExtentTest test;

    @BeforeMethod
    public void setUpTest() {
        test = extentReports.createTest(getClass().getSimpleName());
        String siteURL = "https://phptravels.net/hotels"; // przykładowy URL

        if (!isSiteReachable(siteURL)) {
            test.log(Status.FAIL, "Strona " + siteURL + " jest nieosiągalna.");
            throw new SkipException("Strona " + siteURL + " jest nieosiągalna, test został pominięty.");
        }
    }

    @BeforeSuite
    //public method responsible for creating report in html file.
    public void beforeSuit(){
        sparkReporter = new ExtentSparkReporter("index.html");
        extentReports = new ExtentReports();
        extentReports.attachReporter(sparkReporter);
    }

    @AfterSuite
    //this method have to be to close the report
    public void afterSuit(){
        extentReports.flush();
    }

    @BeforeTest
    public void setup() throws IOException {
        driver = DriverFactory.getDriver();
        driver.manage().window();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
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
    @AfterTest
    public void tearDown(){
        driver.quit();
    }

    protected boolean isSiteReachable(String url) {
        try {
            // Próba połączenia z podanym adresem URL
            HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
            connection.setRequestMethod("HEAD");
            int responseCode = connection.getResponseCode();

            // Jeśli odpowiedź jest sukcesem (kod odpowiedzi HTTP 200), strona jest osiągalna
            return (200 <= responseCode && responseCode <= 399);
        } catch (IOException exception) {
            // Jeśli wystąpi wyjątek, strona jest nieosiągalna
            return false;
        }
    }
}