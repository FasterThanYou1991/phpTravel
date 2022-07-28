package phptravel.demo.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;


public class HotelSearchPage {
    @FindBy(xpath = "//span[text()=' Search by City']")
    private WebElement searchHotelSpan;
    @FindBy(xpath = "//input[@class='select2-search__field']")
    private WebElement searchHotelInput;
    @FindBy(name = "checkin")
    private WebElement checkin;
    @FindBy(name = "checkout")
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

    public void setCity(String cityName) {
        Logger.info("Setting city" + cityName);
        waitMethod("//span[text()=' Search by City']");
        searchHotelSpan.click();
        searchHotelInput.sendKeys(cityName);
        String xpath = String.format("//li[contains(text(),'%s')]", cityName);
        waitMethod(xpath);
        driver.findElement(By.xpath(xpath)).click();
        Logger.info("Setting city done");
    }

    public void setCheckIn(String date) {

        checkin.click();
        Logger.info("Set checkin date" );

        WebElement eval = driver.findElement(By.xpath("//div[contains(@class,'datepicker-days')]/table/tbody"));
        List<WebElement> alldates = eval.findElements(By.tagName("td"));
        for(WebElement cell : alldates){
            String day = cell.getText();
            if (cell.getText().equals(date)) {
                WebElement selectDay = driver.findElement(By.xpath("//td[@class='day ' and text()='"+day+"']"));
                selectDay.isDisplayed();
                selectDay.click();
                break;
            }
        }
        // Alternative approach for selecting visible day from calendar
        /*driver.findElements(By.xpath("//td[ @class='day  new' and (text()='3')]"))
                .stream()
                .filter(WebElement::isDisplayed)
                .findFirst()
                .ifPresent(WebElement::click);*/

        Logger.info("Checkin is set");
    }

    public void setCheckOut(String date){
        Logger.info("Set Check out date" );

        WebElement eval = driver.findElement(By.xpath("//div[contains(@class,'datepicker-days')]/table/tbody"));
        List<WebElement> alldates = eval.findElements(By.tagName("td"));
        for(WebElement cell : alldates){
            String day = cell.getText();
            if (cell.getText().equals(date)) {
                WebElement selectDay = driver.findElement(By.xpath("//td[@class='day ' and text()='"+day+"']"));
                selectDay.isDisplayed();
                selectDay.click();
                break;
            }
        }
        // Alternative approach for selecting visible day from calendar
        /*driver.findElements(By.xpath("//td[@class='day  new' and (text()='6')]"))
                .stream()
                .filter(element -> element.isDisplayed())
                .findFirst()
                .ifPresent(element -> element.click());*/
        Logger.info("Checkout is set");
    }

    public void setTravelers() {
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
    }

    public void assertionHeader(String cityName) {
        String xpath = String.format("//h2[contains(text(),'%s')]", cityName.toLowerCase(Locale.ROOT));
        waitMethod(xpath);
        driver.findElement(By.xpath(xpath));
        Logger.info("Hotels are founded in " + cityName);
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
