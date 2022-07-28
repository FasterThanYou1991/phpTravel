package phptravel.demo.tests;

import org.testng.annotations.Test;
import phptravel.demo.pages.HotelSearchPage;
import phptravel.demo.pages.ResultPage;



public class HotelSearchTest extends BaseTest {


    @Test
    public void hotelSearchTest() {

        HotelSearchPage hotelSearchPage = new HotelSearchPage(driver);
        hotelSearchPage.setCity("Dubai")
            .setCheckIn()
            .setTravelers()
            .performSearch();
        //hotelSearchPage.assertionHeader();

        ResultPage resultPage = new ResultPage(driver);
        System.out.print(resultPage.getHotelNames().size());
    }
}


