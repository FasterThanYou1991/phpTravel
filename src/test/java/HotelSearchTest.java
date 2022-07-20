import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;
import java.util.stream.Collectors;


public class HotelSearchTest extends BaseTest{

    @Test
    public void hotelSearchTest() {

        //Warunek 'Wait' dla wszystkich kroków zawartych w tej metodzie.
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("https://phptravels.net/");

        WebElement paraText = driver.findElement(By.xpath("//span[text()=' Search by City']"));
        paraText.getText();

        WebElement hotel = driver.findElement(By.xpath("//span[@class='select2-selection__rendered' and @id='select2-hotels_city-container']"));
        hotel.click();
        driver.findElement(By.xpath("//input[@class='select2-search__field']")).sendKeys("Dubai");


        //Jest to warunek 'wait' dla konkretnego lokatora -> Explicit wait-y ignorują wyjątki np. noSuchElemenetExepction
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
        checkIn.until(ExpectedConditions.visibilityOfElementLocated(By.name("checkin")));

        // ustawianie daty za pomocą wybieraniu dnia z kalendarzu
        WebElement dateCheckIn = driver.findElement(By.name("checkin"));
        dateCheckIn.click();
        dateCheckIn.isSelected();

        driver.findElements(By.xpath("//td[@class='day ' and text()='20']"))
                .stream()
                .filter(element -> element.isDisplayed())
                .findFirst()
                .ifPresent(element -> element.click());
        // Po wybraniu daty 'Checkin', odrazu pojawia się kalendarz 'Checkout'

        driver.findElements(By.xpath("//td[@class='day ' and text()='22']"))
                .stream()
                .filter(element2 -> element2.isDisplayed())
                .findFirst()
                .ifPresent(element2 -> element2.click());

        driver.findElement(By.xpath("//span[@class='guest_hotels']")).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='roomInc' and i[@class='la la-plus']]")));
        driver.findElement(By.xpath("//div[@class='roomInc' and i[@class='la la-plus']]")).click();
        // Potrzeba była użycia lambdy ponieważ było więcej niz 1 wyników, więć pierwszy widoczny element zostałwybrany w danym elemencie

        driver.findElements(By.xpath("//div[@class='qtyInc']"))
                .stream()
                .filter(element -> element.isDisplayed())
                .findFirst()
                .ifPresent(element -> element.click());

        //Nie dało się znaleść pojedyńczego pola do lokalizaji z racji braku orginalnego identyfikatora
        //Więc gdy pośiadaliśmy 8 elementów z tym samym indentyfikatorem, stworzona lista Webelementów pomogła w zlokalizowaniu tego jednego
        List<WebElement> addChildButton = driver.findElements(By.xpath("//div[@class='qtyInc']"));
        addChildButton.get(2).click();

        List<WebElement> childAgeInput = driver.findElements(By.xpath("//div[@class='input-items']"));
        childAgeInput.get(1).click();


        Select childAge = new Select(driver.findElement(By.id("ages1")));
        Assert.assertEquals(17, childAge.getOptions().size());
        childAge.selectByIndex(3);

        driver.findElement(By.id("submit")).click();
        driver.findElement(By.xpath("//h2[@class='sec__title_list']"));
        Assert.assertEquals("Search Hotels in dubai", "Search Hotels in dubai", "Header is wrong");

        List<String> hotelNames = driver.findElements(By.xpath("//h3[@class='card-title']"))
                .stream()
                .map(el -> el.getAttribute("textContent")).collect(Collectors.toList());
        System.out.println(hotelNames.size());
        hotelNames.forEach(el -> System.out.println(el));
        Assert.assertEquals("Oasis Beach Tower                  \n" +
                "                  ", hotelNames.get(0));
        Assert.assertEquals("Malmaison Manchester                  \n" +
                "                  ", hotelNames.get(1));
        Assert.assertEquals("Madinah Moevenpick Hotel                  \n" +
                "                  ", hotelNames.get(2));
        Assert.assertEquals("Rose Rayhaan Rotana                  \n" +
                "                  ", hotelNames.get(3));
        Assert.assertEquals("Jumeirah Beach Hotel                  \n" +
                "                  ", hotelNames.get(4));
        Assert.assertEquals("Hyatt Regency Perth                  \n" +
                "                  ", hotelNames.get(5));

    }

    // Metoda ta może być użyta to sprwdzenia czy dane elementy o podanym lokatorze istnieją na stronie. Aby ja użyc wystarczy ja potem wywołać i podać lokator.
    //E.G waitForElementToExist(By.xpath("//div[@class='input-items']"));
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
}


