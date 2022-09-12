package phptravel.demo.tests;

import org.testng.annotations.Test;
import phptravel.demo.pages.HotelSearchPage;
import phptravel.demo.pages.LoggedUserPage;
import phptravel.demo.pages.LoginPage;

public class LoginTest extends BaseTest{

    @Test
    public void LoginTest(){

        HotelSearchPage hotelSearchPage = new HotelSearchPage(driver);
        hotelSearchPage.selectAccountDropdown();
        hotelSearchPage.selectCustomerLogin();
        LoginPage loginPage = new LoginPage(driver);
        loginPage.setEmailLoginInput("amadeusz01@gmail.com");
        loginPage.setPasswordLoginInput("Karta1234");
        loginPage.performLogin();
        LoggedUserPage loggedUserPage = new LoggedUserPage(driver);
        loggedUserPage.getWelcomeBackHeader();


    }
}