package phptravel.demo.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import phptravel.demo.utils.SeleniumHelper;

import java.util.List;


public class HotelSearchPage extends SeleniumHelper {
    @FindBy(xpath = "//span[@id='select2-hotels_city-container' and @title=' Search by City']")
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
    @FindBy(xpath = "//input[@id='adults']")
    private WebElement addAdults;
    @FindBy(xpath = "//input[@id='childs']")
    private WebElement addChilds;
    @FindBy(id = "submit")
    private WebElement submit;
    @FindBy(xpath = "//a[@href='https://phptravels.net/signup']")
    private WebElement signupButton;

    @FindBy(id = "ACCOUNT")
    private WebElement accountDropdown;

    @FindBy(xpath = "//a[@href='https://phptravels.net/login']")
    private WebElement customerLogin;

    private WebDriver driver;

    private static final Logger Logger = LogManager.getLogger();

    public HotelSearchPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    public void setCity(String cityName) {
        waitMethod("//span[@id='select2-hotels_city-container' and @title=' Search by City']", driver);
        searchHotelSpan.click();
        searchHotelInput.sendKeys(cityName);Logger.info("City Name was entered" + cityName);
        Logger.info("City Name was entered" + cityName);
        String xpath = String.format("//li[contains(text(),'%s')]", cityName);
        waitMethod(xpath,driver);
        Logger.info("Element with city name exist" + "--> " + cityName);
        driver.findElement(By.xpath(xpath)).click();
    }

    public void setCheckIn(String dateIn) {

        checkin.click();
        WebElement eval = driver.findElement(By.xpath("//div[contains(@class,'datepicker-days')]/table/tbody"));
        waitMethod("//div[contains(@class,'datepicker-days')]/table/tbody", driver);
        List<WebElement> allDates = eval.findElements(By.tagName("td"));
        for(WebElement cell : allDates){
            String dayIn = cell.getText();
            if (cell.getText().equals(dateIn)) {
                WebElement selectDay = driver.findElement(By.xpath("//td[@class='day ' and text()='"+dayIn+"']"));
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

        Logger.info("CheckIn date was selected: " + dateIn);
    }

    public void setCheckOut(String dateOut){

        WebElement eval = driver.findElement(By.xpath("//div[contains(@class,'datepicker-days')]/table/tbody"));
        //waitMethod("//div[contains(@class,'datepicker-days')]/table/tbody", driver);
        List<WebElement> allDates = eval.findElements(By.tagName("td"));
        for(WebElement cell : allDates){
            String dayOut = cell.getText();
            if (cell.getText().equals(dateOut)) {
                WebElement selectDayOut = driver.findElement(By.xpath("//td[@class='day ' and text()='"+dayOut+"']"));
                selectDayOut.isDisplayed();
                selectDayOut.click();
                break;
            }
        }
        // Alternative approach for selecting visible day from calendar
        /*driver.findElements(By.xpath("//td[@class='day  new' and (text()='6')]"))
                .stream()
                .filter(element -> element.isDisplayed())
                .findFirst()
                .ifPresent(element -> element.click());*/
        Logger.info("ChecOut date was selected : " + dateOut);
    }

    public void setTravelers() {
        travellersDropdown.click();
        Logger.info("Travelers dropDown was clicked");

        rooms.click();
        Logger.info("Rooms was selected");
        Actions adults = new Actions(driver);
        adults.doubleClick(addAdults);
        addAdults.clear();
        addAdults.sendKeys("2");
        Logger.info("Adults was added by using input");

        Actions child = new Actions(driver);
        child.doubleClick(addChilds);
        addChilds.clear();
        addChilds.sendKeys("3");
        Logger.info("Child was added by using input");
    }

    public void setNationality(){
        String nationality = "Poland";
        Select select = new Select(driver.findElement(By.id("nationality")));
        select.selectByVisibleText(nationality);
        Logger.info("Nationality was selected by text value in dropdown" + "--> " + nationality);
    }

    public ResultPage performSearch() {
        submit.click();
        return new ResultPage(driver);
    }
    public void selectAccountDropdown(){
        accountDropdown.click();
    }

    public LoginPage selectCustomerLogin(){
        waitMethod("//a[@href='https://phptravels.net/login']",driver);
        customerLogin.click();
        return new LoginPage(driver);
    }

    public void openSignUpForm() {
        waitMethod("//a[@href='https://phptravels.net/signup']",driver);
        signupButton.click();
    }
}