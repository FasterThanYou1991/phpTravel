package phptravel.demo.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import phptravel.demo.utils.SeleniumHelper;

import java.util.List;
import java.util.stream.Collectors;

public class ResultPage extends SeleniumHelper {

    private static final org.apache.logging.log4j.Logger Logger = LogManager.getLogger();
    @FindBy(xpath = "//h3[@class='card-title']")
       private List<WebElement> hotelList;

    public ResultPage(WebDriver driver){
        PageFactory.initElements(driver, this);
    }

    public List<String> getHotelNames(){
       return hotelList.stream()
                .map(el -> el.getAttribute("textContent"))
                .collect(Collectors.toList());

    }public void assertionHeader(WebDriver driver){
        waitMethod("//h2[contains(text(),'Search Hotels')]", driver);
        WebElement header = driver.findElement(By.xpath("//h2[contains(text(),'Search Hotels')]"));
        header.isDisplayed();
        Logger.info("We're on the hotels page result: " + header.getText());
    }
}
