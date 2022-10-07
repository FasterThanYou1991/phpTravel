package phptravel.demo.tests;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.testng.annotations.Test;
import phptravel.demo.pages.HotelSearchPage;
import phptravel.demo.pages.ResultPage;
import phptravel.demo.utils.SeleniumHelper;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;


public class HotelSearchTest extends BaseTest {

    private String cityName = "London";
    LocalDate day = LocalDate.now();
    int currentDayIn = day.getDayOfMonth() + 1;
    int currentDayOut = currentDayIn + 2;


    @Test
    public void hotelSearchTest() throws IOException {
        ExtentTest test = extentReports.createTest("Search hotel Test");
        HotelSearchPage hotelSearchPage = new HotelSearchPage(driver);
        hotelSearchPage.setCity(cityName);
        hotelSearchPage.setCheckIn(String.valueOf(currentDayIn));
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
        ExtentTest test = extentReports.createTest("Search hotel Test");
        DataDriven dataDriven = new DataDriven();
        ArrayList<String> data = dataDriven.getData("TestCases");
        HotelSearchPage hotelSearchPage = new HotelSearchPage(driver);
        hotelSearchPage.setCity(data.get(3));
        hotelSearchPage.selectDateCheckIn((String.valueOf(currentDayIn)));
        hotelSearchPage.selectDateCheckOut(String.valueOf(currentDayOut));
        hotelSearchPage.setTravelers();
        hotelSearchPage.setNationality();
        test.log(Status.PASS, "City name, checkIn, checkOut, travellers and nationality was set correctly ", SeleniumHelper.getScreenshot(driver));
        hotelSearchPage.performSearch();

        ResultPage resultPage = new ResultPage(driver);
        resultPage.assertionHeader(driver);
        resultPage.getHotelNames();
        test.log(Status.PASS,"Result of hotel names", SeleniumHelper.getScreenshot(driver));
    }
}