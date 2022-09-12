package phptravel.demo.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import phptravel.demo.utils.SeleniumHelper;

public class LoggedUserPage extends SeleniumHelper {
    private WebDriver driver;

    @FindBy(xpath = "//span[text()='Welcome Back']")
    private WebElement welcomeBackHeader;

    public LoggedUserPage(WebDriver driver){
        PageFactory.initElements(driver,this);
        this.driver = driver;
    }

    public void getWelcomeBackHeader(){
        waitMethod("//span[text()='Welcome Back']", driver);
        welcomeBackHeader.getText();
        Assert.assertEquals(welcomeBackHeader.getText(),"Welcome Back");
    }
}