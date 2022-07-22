package phptravel.demo.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import phptravel.demo.tests.BaseTest;


public class SignUpTest extends BaseTest {

    @Test
    public void signUpFormTest() {

        HotelSearchTest hotelSearchTest = new HotelSearchTest(driver);


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


