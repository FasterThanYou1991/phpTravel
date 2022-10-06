package phptravel.demo.tests;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.testng.annotations.Test;
import phptravel.demo.pages.HotelSearchPage;
import phptravel.demo.pages.ResultPage;
import phptravel.demo.utils.SeleniumHelper;

import java.io.IOException;
import java.time.LocalDate;


public class HotelSearchTest extends BaseTest {

    private String cityName = "London";
    LocalDate day = LocalDate.now();
    int currentDayIn = day.getDayOfMonth() + 1;
    int currentDayOut = currentDayIn + 2;
    ExtentTest test = extentReports.createTest("Search hotel Test");

    @Test
    public void hotelSearchTest() throws IOException {

        HotelSearchPage hotelSearchPage = new HotelSearchPage(driver);
        hotelSearchPage.setCity(cityName);
        hotelSearchPage.selectDateCheckIn(String.valueOf(currentDayIn));
        hotelSearchPage.setCheckOut(String.valueOf(currentDayOut));
        hotelSearchPage.setTravelers();
        hotelSearchPage.setNationality();
        test.log(Status.PASS, "City name, checkIn, checkOut, travellers and nationality was set correctly ", SeleniumHelper.getScreenshot(driver));
        hotelSearchPage.performSearch();

        ResultPage resultPage = new ResultPage(driver);
        resultPage.assertionHeader(driver);
        resultPage.getHotelNames();
        test.log(Status.PASS,"Result of hotel names", SeleniumHelper.getScreenshot(driver));
    }

    @Test
    //This test was created for checking new solution for handling date-picker into calendar
    public void approachDatePicker() throws IOException {
        HotelSearchPage hotelSearchPage = new HotelSearchPage(driver);
        hotelSearchPage.setCity(cityName);
        hotelSearchPage.selectDateCheckIn((String.valueOf(currentDayIn)));
        hotelSearchPage.selectDateCheckOut(String.valueOf(currentDayOut));
        hotelSearchPage.setTravelers();
        hotelSearchPage.setNationality();
        test.log(Status.PASS, "City name, checkIn, checkOut, travellers and nationality was set correctly ", SeleniumHelper.getScreenshot(driver));
        hotelSearchPage.performSearch();
    }
}