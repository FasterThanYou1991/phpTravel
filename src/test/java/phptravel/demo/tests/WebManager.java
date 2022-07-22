package phptravel.demo.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.InvalidArgumentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.Test;

public class WebManager {


    public void openBrowser() {
        WebDriver driver = getDriver("chrome");
    }

    public WebDriver getDriver(String browser) {
        switch (browser) {
            case "chrome":
                return new ChromeDriver();
            case "firefox":
                String firefoxPath = "C:\\Users\\amade\\OneDrive\\Pulpit\\Java Selenium Repo\\geckodriver.exe";
                System.setProperty("webdriver.gecko.driver", firefoxPath);
                return new FirefoxDriver();
            case "ie":
                String iePath = "C:\\Users\\amade\\OneDrive\\Pulpit\\Java Selenium Repo\\IEDriverServer.exe";
                System.setProperty("webdriver.ie.driver", iePath);
                return new InternetExplorerDriver();
            default:
                throw new InvalidArgumentException("Invalid browser name");
        }
    }

}
