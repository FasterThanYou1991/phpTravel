package phptravel.demo.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;
import java.util.stream.Collectors;

public class ResultPage {

    @FindBy(xpath = "//h3[@class='card-title']")
       private List<WebElement> hotelList;

    public ResultPage(WebDriver driver){
        PageFactory.initElements(driver, this);
    }

    public List<String> getHotelNames(){
       return hotelList.stream()
                .map(el -> el.getAttribute("textContent"))
                .collect(Collectors.toList());
    }
}
