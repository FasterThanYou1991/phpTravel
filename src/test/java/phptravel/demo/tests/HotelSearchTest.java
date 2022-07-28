package phptravel.demo.tests;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.testng.annotations.Test;
import phptravel.demo.pages.HotelSearchPage;
import phptravel.demo.pages.ResultPage;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;


public class HotelSearchTest extends BaseTest {

    private String cityName = "Dubai";
    @Test
    public void hotelSearchTest() {
        LocalDate day = LocalDate.now();
        int currentDayIn = day.getDayOfMonth();
        int currentDayOut = currentDayIn + 2;

        ExtentTest test = extentReports.createTest("Search hotel Test");
        HotelSearchPage hotelSearchPage = new HotelSearchPage(driver);
        hotelSearchPage.setCity(cityName);
        test.log(Status.PASS, "Setting city is done");
        hotelSearchPage.setCheckIn(String.valueOf(currentDayIn));
        test.log(Status.PASS, "Checkin date is set");
        hotelSearchPage.setCheckOut(String.valueOf(currentDayOut));
        test.log(Status.PASS, "Checkout date is set");
        hotelSearchPage.setTravelers();
        test.log(Status.PASS, "Rooms, Adults and childs are configured ");
        hotelSearchPage.performSearch();
        hotelSearchPage.assertionHeader(cityName);
        test.log(Status.PASS, "Searching the hotels is done");

        ResultPage resultPage = new ResultPage(driver);
        System.out.print(resultPage.getHotelNames().size());
    }
}


