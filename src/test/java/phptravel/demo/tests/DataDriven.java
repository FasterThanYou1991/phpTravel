package phptravel.demo.tests;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

public class DataDriven {

    public ArrayList<String> getData(String testCase) throws IOException {
        ArrayList<String> arList = new ArrayList<String>();
        FileInputStream file = new FileInputStream("C:\\Users\\ajankows\\Desktop\\HotelSearch.xlsx");
        XSSFWorkbook workbook = new XSSFWorkbook(file);

        int sheets = workbook.getNumberOfSheets();
        for (int i = 0; i < sheets; i++)
        {
            if (workbook.getSheetName(i).equalsIgnoreCase("TestData")) ;
            {
                // Here we are validating if the first sheet name is "TestData"-> then proceed for the below operations
                XSSFSheet sheet = workbook.getSheetAt(i);

                Iterator<Row> rows = sheet.iterator();// Sheet has number of rows
                Row firstRow = rows.next(); // Here we have iterated through the first row only
                Iterator<Cell> cell = firstRow.cellIterator();// cellIterator() is used to scan values in cells.


                int k = 0;
                int column = 0;

                while (cell.hasNext()) // has.Next() is used to check if next cell is present with value
                {
                    Cell value = cell.next();
                    if (value.getStringCellValue().equalsIgnoreCase(testCase)) { // if desired value is present in cell loop condition is finished
                        // When condition will find the value, we have Desired column where this value is put
                        column = k;
                        System.out.println(value);
                    }
                    k++;
                }
                System.out.println(column);
                // Here we are displaying the column index where it found Testcases
                // Now we will iterate through all rows

                //One column is identified then scan entire testcase column to identify searchHotel testcase row
                while (rows.hasNext())
                {
                    Row r = rows.next();
                    if (r.getCell(column).getStringCellValue().equalsIgnoreCase("anotherHotel"))
                    {
                        System.out.println(r);
                        //After you grab searchHotel testcase row = pull all data of that row and feed into test
                        Iterator<Cell> cv = r.cellIterator();
                        while (cv.hasNext())
                        {
                            Cell c = cv.next();
                            //this loop will accept string and double values from sheet
                            if(c.getCellType() == CellType.STRING)
                            {
                                arList.add(c.getStringCellValue());
                            }else
                            {
                                arList.add(NumberToTextConverter.toText(c.getNumericCellValue()));
                            }

                        }
                    }
                }
            }
        }
        return arList;
    }

    public static void main(String[] args) throws IOException {
        DataDriven dataDriven = new DataDriven();
        ArrayList<String> data = dataDriven.getData("TestCases");
        System.out.println(data.get(3));
    }
}
