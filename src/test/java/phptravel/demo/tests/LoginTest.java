package phptravel.demo.tests;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.testng.annotations.Test;
import phptravel.demo.pages.HotelSearchPage;
import phptravel.demo.pages.LoggedUserPage;
import phptravel.demo.pages.LoginPage;
import phptravel.demo.utils.SeleniumHelper;

import java.io.IOException;

public class LoginTest extends BaseTest{

    @Test
    public void LoginTest() throws IOException {
        ExtentTest test = extentReports.createTest("Login Test");

        HotelSearchPage hotelSearchPage = new HotelSearchPage(driver);
        hotelSearchPage.selectAccountDropdown();
        hotelSearchPage.selectCustomerLogin();
        LoginPage loginPage = new LoginPage(driver);
        loginPage.getLoginHeader();
        test.log(Status.PASS, "We're on the login page: ", SeleniumHelper.getScreenshot(driver));
        loginPage.setEmailLoginInput("amadeusz01@gmail.com");
        loginPage.setPasswordLoginInput("Karta1234");
        loginPage.performLogin();
        LoggedUserPage loggedUserPage = new LoggedUserPage(driver);
        loggedUserPage.getWelcomeBackHeader();
        test.log(Status.PASS, "We're logged in: ", SeleniumHelper.getScreenshot(driver));


    }
}