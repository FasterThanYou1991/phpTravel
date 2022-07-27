package phptravel.demo.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import phptravel.demo.tests.BaseTest;

public class LoggedUserPage extends BaseTest {

    @FindBy(xpath = "//h2[text()='Hi, ' and text()=' Welcome Back']")
        private WebElement heading;

    public LoggedUserPage(WebDriver driver){
        PageFactory.initElements(driver, this);
    }

    public String getHeadingText(){
        return heading.getText();
    }
}
