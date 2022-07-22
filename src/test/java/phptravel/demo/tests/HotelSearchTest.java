package phptravel.demo.tests;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;
import phptravel.demo.pages.HotelSearchPage;
import phptravel.demo.pages.ResultPage;



public class HotelSearchTest extends BaseTest {

    public HotelSearchTest(WebDriver driver) {
        super();
    }

    @Test
    public void hotelSearchTest() {

        HotelSearchPage hotelSearchPage = new HotelSearchPage(driver);
        hotelSearchPage.setCity("Berlin");

        hotelSearchPage.setCheckIn();
        hotelSearchPage.setTravelers();
        hotelSearchPage.performSearch();
        //hotelSearchPage.assertionHeader();

        ResultPage resultPage = new ResultPage(driver);
        resultPage.getHotelNames();



        /*//Jest to warunek 'wait' dla konkretnego lokatora -> Explicit wait-y ignorują wyjątki np. noSuchElemenetExepction
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOfElementLocated
                (By.xpath("//li[@class='select2-results__option select2-results__option--highlighted' and text()='Dubai,United Arab Emirates']")));
        driver.findElement(By.xpath("//li[@class='select2-results__option select2-results__option--highlighted' and text()='Dubai,United Arab Emirates']")).click();

        // Jeżeli chcemy aby FluentWait ignorował jakieś wyjątki to trzeba je zdefiniować.
        FluentWait<WebDriver> checkIn = new FluentWait<>(driver);
        // własny warunek, który ignoruje error NoSuchElementException
        checkIn.ignoring(NoSuchElementException.class);
        // Określony timeout dla tego warunku wait
        checkIn.withTimeout(Duration.ofSeconds(5));
        checkIn.until(ExpectedConditions.visibilityOfElementLocated(By.name("checkin")));*/

    }
}


