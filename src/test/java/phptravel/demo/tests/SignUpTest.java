package phptravel.demo.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import phptravel.demo.model.User;
import phptravel.demo.pages.HotelSearchPage;
import phptravel.demo.pages.LoggedUserPage;
import phptravel.demo.pages.SignUpPage;
import phptravel.demo.tests.BaseTest;


public class SignUpTest extends BaseTest {

 /*   @Test
    public void signUpFormTest() {

        SignUpPage signUpPage = new SignUpPage(driver);
        signUpPage.openSignUpForm();
        signUpPage.setFirstName("");
        signUpPage.setLastName("");
        signUpPage.setPhoneInput("456233445");
        signUpPage.setEmailInput();
        signUpPage.setPasswordInput("1234");
        signUpPage.accountTypeDropdown("Agent");
        signUpPage.performSubmit();

        LoggedUserPage loggedUserPage = new LoggedUserPage(driver);
        System.out.println(loggedUserPage.getHeadingText());*/

        @Test
        public void signUpFormTest2() {

            String lastName = "Testowy";
            int randomNumber = (int) (Math.random()* 1000);
            String randomEmail = "tester" + randomNumber + "@wp.pl";
            HotelSearchPage hotelSearchPage = new HotelSearchPage(driver);
            hotelSearchPage.openSignUpForm();

            User user = new User();
            user.setFirstName("Janusz");
            user.setLastName(lastName);
            user.setPhone("123456789");
            user.setEmail(randomEmail);
            user.setPassword("1234");

            SignUpPage signUpPage = new SignUpPage(driver);
            signUpPage.accountTypeDropdown("Agent");
            signUpPage.fillSignUpForm(user);

    }
}


