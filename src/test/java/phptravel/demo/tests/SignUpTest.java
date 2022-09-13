package phptravel.demo.tests;

import org.testng.annotations.Test;
import phptravel.demo.pages.SignUpPage;


public class SignUpTest extends BaseTest {

    @Test
    public void signUpTest() {

        SignUpPage signUpPage = new SignUpPage(driver);
        signUpPage.selectAccountDropdown();
        signUpPage.selectCustomerSignup();
        signUpPage.setFirstName("")
            .setLastName("")
            .setPhoneInput("456233445")
            .setEmailInput("")
            .setPasswordInput("1234")
            .accountTypeDropdown("Agent");
        signUpPage.selectCheckboxRecaptcha();
        //signUpPage.performSubmit();


/*        @Test
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
            signUpPage.fillSignUpForm(user);*/

    }
}


