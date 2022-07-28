package phptravel.demo.tests;

import org.testng.annotations.Test;
import phptravel.demo.pages.LoginPage;

public class LoginTest extends BaseTest{

    @Test
    public void LoginTest(){

        LoginPage loginPage = new LoginPage(driver);
        loginPage.openLoginPage();
        loginPage.setEmailLoginInput("");
        loginPage.setPasswordLoginInput("1234");
        loginPage.performLogin();



    }
}
