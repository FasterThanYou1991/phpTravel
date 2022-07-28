package phptravel.demo.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import phptravel.demo.utils.SeleniumHelper;

import java.time.Duration;
import java.util.List;
import java.util.function.Function;


public class HotelSearchPage {
    @FindBy(xpath = "//span[text()=' Search by City']")
    private WebElement searchHotelSpan;
    @FindBy(xpath = "//input[@class='select2-search__field']")
    private WebElement searchHotelInput;
    @FindBy(name = "checkin")
    private WebElement checkin;

    @FindBy(xpath = "//td[@class='day ' and text()='29']")
    private WebElement checkOut;
    @FindBy(xpath = "//span[@class='guest_hotels']")
    private WebElement travellersDropdown;
    @FindBy(xpath = "//div[@class='roomInc' and i[@class='la la-plus']]")
    private WebElement rooms;
    @FindBy(xpath = "//div[@class='qtyInc']")
    private WebElement addAdults;
    @FindBy(xpath = "//div[@class='qtyInc']")
    private WebElement addChilds;
    @FindBy(id = "submit")
    private WebElement submit;
    @FindBy(xpath = "//a[@href='https://phptravels.net/signup']")
    private WebElement signupButton;

    private WebDriver driver;

    private static final Logger Logger = LogManager.getLogger();

    public HotelSearchPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    public void waitMethod(String xpath) {
        WebDriverWait wait = new WebDriverWait(driver, 5);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
    }

    public HotelSearchPage setCity(String cityName) {
        Logger.info("Setting city" + cityName);
        waitMethod("//span[text()=' Search by City']");
        searchHotelSpan.click();
        searchHotelInput.sendKeys(cityName);
        String xpath = String.format("//li[contains(text(),'%s')]", cityName);
        waitForElementToExist(By.xpath(xpath));
        driver.findElement(By.xpath(xpath)).click();
        Logger.info("Setting city done");
        return this;
    }

    public HotelSearchPage setCheckIn() {

        checkin.click();
        Logger.info("Set checkin date and Check out" );
        driver.findElements(By.xpath("//td[ @class='day  new' and (text()='3')]"))
                .stream()
                .filter(WebElement::isDisplayed)
                .findFirst()
                .ifPresent(WebElement::click);

        driver.findElements(By.xpath("//td[@class='day  new' and (text()='6')]"))
                .stream()
                .filter(element -> element.isDisplayed())
                .findFirst()
                .ifPresent(element -> element.click());
        Logger.info("Checkin and Checkout is set");
        return this;
    }

    public HotelSearchPage setTravelers() {
        Logger.info("Setting travellers");
        travellersDropdown.click();
        rooms.click();
        driver.findElements(By.xpath("//div[@class='qtyInc']"))
                .stream()
                .filter(element -> element.isDisplayed())
                .findFirst()
                .ifPresent(element -> element.click());

        List<WebElement> addChildButton = driver.findElements(By.xpath("//div[@class='qtyInc']"));
        addChildButton.get(2).click();
        Select childAge = new Select(driver.findElement(By.id("ages1")));
        Assert.assertEquals(17, childAge.getOptions().size());
        childAge.selectByIndex(3);
        Logger.info("Traveleres are set");
        return this;
    }

    public void assertionHeader() {
        driver.findElement(By.xpath("//h2[@class='sec__title_list']"));
        Assert.assertEquals("Search Hotels in dubai", "Search Hotels in dubai", "Header is wrong");

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

    public ResultPage performSearch() {
        submit.click();
        return new ResultPage(driver);
    }


    public void openSignUpForm() {
        WebDriverWait wait = new WebDriverWait(driver, 5);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@href='https://phptravels.net/signup']")));
        signupButton.click();
    }


}
