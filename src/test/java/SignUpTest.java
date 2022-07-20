import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;
import java.util.stream.Collectors;


public class SignUpTest extends BaseTest{
    @Test
    public void signUpFormTest() {
        driver.findElements(By.xpath("//a[@class='theme-btn theme-btn-small waves-effect' and text()='Signup']"))
                .stream()
                .filter(WebElement::isDisplayed)
                .findFirst()
                .ifPresent(WebElement::click);
        String lastName = "Testowy";
        int randomNubmer = (int) (Math.random()* 1000);
        String email = "tester" + randomNubmer + "@wp.pl";

        driver.findElement(By.name("first_name")).sendKeys("Amadeusz");
        driver.findElement(By.name("last_name")).sendKeys(lastName);
        driver.findElement(By.name("phone")).sendKeys("111333444");
        driver.findElement(By.name("email")).sendKeys(email);
        driver.findElement(By.name("password")).sendKeys("2330");
        driver.findElement(By.xpath("//span[@class='select2-selection__rendered' and @id='select2-account_type-container']")).click();

        WebElement type = driver.findElement(By.xpath("//li[text()='Customer']"));
        if (!type.isSelected()){
            type.click();
        }
        driver.findElement(By.xpath("//button[@type='submit']")).click();

        // 1. Przykład użycia metody lambda, do wyświetlenia nazw linków na stronie
        /*linksList.forEache(el -> System.out.println(el.getText()))*/

        //2. Ten przykład pokazuje jak pozbyć się z wyników spacji w tekstach, ale jednak może czasami się jakaś spacja pojawić
        /*List<>String collectList = linkList
        * .stream()
        *   .filter(ele -> !ele.getText().equals("")) - filtrowanie, aby nie pobierał wartości ze spacjami
        *       .map(ele -> ele.getText()) - mapowanie wartości bez spacji na stronie
        *           .collect(Collectors.toList()); - zbieramy wartości do listy
        *
        * collectList.forEache(ele -> System.out.println(ele)); - wypisanie w konsoli elementów
        * */

        //3. Ten przykład pokazuje jak pobieramy pierwszy widoczny link na stronie
        /*String firstText = linkList.stream().filter(ele -> !ele.getText().equals("")).findFirst().get().getText()
        * System.out.println(firstText)*/

        //4. Ten przykład znajduje jakikolwiek element widoczny na stronie
        /*String findANy = linkList.stream().filter(ele -> !ele.getText().equals("")).findAny().get().getText()
        * System.out.println(findAny)*/

        //5. Pokazania tylko linków z tekstem 'Amazon'
        /*List<String> collect = linkList.stream()
          .filter(ele -> !ele.getText().equals("") && e.e.getText().startsWith("Amazon"))
        * .map(ele -> ele.getText())
        * .collect(Collectors.toList());
        * collectList.forEach(ele -> System.out.println(ele));*/

        //6. Ulepszenie wyciągania textLinków bez spacji
        /*List<String> AllLinkTextList = linkList.stream()
          .filter(ele -> !ele.getText().isEmpty())
          .filter(ele -> !ele.getText().startsWith(""))
        * .map(ele -> ele.getText().trim())
        * .collect(Collectors.toList());
        * AllLinkTextList.forEach(ele -> System.out.println(ele));*/

        // 7. Zaznaczanie checkboxów za pomocą stream
        /*List<WebElement> = checkBoxList = driver.findElements(By.cssSelector("td.select-checkbox"));
        * checkBoxList.stream().forEach(ele -> ele.click());*/

        //8.
        /*public static String switchToWindowTest(WebDriver driver, String title){

	    return	driver.getWindowHandles()
					.stream()
						.map(handler -> driver.switchTo().window(handler).getTitle())
							.filter(ele -> ele.contains(title))
							.findFirst()
								.orElseThrow(() -> {
										throw new RuntimeException("No Such window");
								});*/
    }
}


