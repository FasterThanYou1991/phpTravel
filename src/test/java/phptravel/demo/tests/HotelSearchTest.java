package phptravel.demo.tests;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.testng.annotations.Test;
import phptravel.demo.pages.HotelSearchPage;
import phptravel.demo.pages.ResultPage;



public class HotelSearchTest extends BaseTest {


    @Test
    public void hotelSearchTest() {

        ExtentTest test = extentReports.createTest("Search hotel Test");
        HotelSearchPage hotelSearchPage = new HotelSearchPage(driver);
        hotelSearchPage.setCity("Dubai")
            .setCheckIn()
            .setTravelers()
            .performSearch();
        //hotelSearchPage.assertionHeader();
        test.log(Status.PASS, "Searching the hotels is done");

        ResultPage resultPage = new ResultPage(driver);
        System.out.print(resultPage.getHotelNames().size());
    }
}


