package phptravel.demo.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SeleniumHelper {

    public static void waitForElementToExist(WebDriver driver, By locator) {
        WebDriverWait wait = new WebDriverWait(driver, 10l);
        wait.until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    public static void waitForElementToExist(WebDriver driver, WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, 10l);
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    public static void waitForNotEmptyList(WebDriver driver, By locator) {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(driver1 -> driver.findElements(locator).size()>0);
    }
}