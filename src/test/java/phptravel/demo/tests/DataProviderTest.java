package phptravel.demo.tests;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import phptravel.demo.pages.HotelSearchPage;
import phptravel.demo.pages.ResultPage;
import phptravel.demo.utils.SeleniumHelper;


import java.io.FileInputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

public class DataProviderTest extends BaseTest{

    LocalDate day = LocalDate.now();
    int currentDayIn = day.getDayOfMonth() + 1;
    int currentDayOut = currentDayIn + 2;

    DataFormatter formatter = new DataFormatter();
    @Test(dataProvider = "driverTest")
    public void testCaseData (String city, String city2) throws IOException {
        ExtentTest test = extentReports.createTest("Search hotel Test");
        HotelSearchPage hotelSearchPage = new HotelSearchPage(driver);
        hotelSearchPage.setCity(city);
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

    @DataProvider(name = "driverTest")
    public Object[] getData() throws IOException {

        FileInputStream file = new FileInputStream("C:\\Users\\ajankows\\Desktop\\DriverTest.xlsx");
        XSSFWorkbook xssfWorkbook = new XSSFWorkbook(file);
        XSSFSheet sheet = xssfWorkbook.getSheetAt(0);
        int rowCount = sheet.getPhysicalNumberOfRows();
        XSSFRow row = sheet.getRow(0);
        int columnCount = row.getLastCellNum();
        Object data[][] = new Object[rowCount -1][columnCount];
        for(int i=0;i<rowCount-1;i++)
        {
            System.out.println("Row loop started");
            // we're getting 2nd row from exel avoiding headers
            row = sheet.getRow(i + 1);
            for(int j=0;j<columnCount;j++)
            {
                //we're getting access to the value cell of the counted row
                XSSFCell cell = row.getCell(j);
                data[i][j]= formatter.formatCellValue(cell);
            }
            System.out.println("Row loop ended here");
        }
        return data;
    }
}

