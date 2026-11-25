package Util;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ReadExcel
{

    public List<HashMap<String, String>> getExcelData() throws IOException
    {
        List<HashMap<String,String>> map = new ArrayList<>();


        FileInputStream fis = new FileInputStream("E:/Logincreds.xlsx");
        XSSFWorkbook workbook = new XSSFWorkbook(fis);
        XSSFSheet sheet = workbook.getSheetAt(0);

        int rows = sheet.getPhysicalNumberOfRows();

        for(int i = 1; i < rows; i++)  //1 se 3 , 2 se 3
        {
            int columns = sheet.getRow(i).getLastCellNum();
            HashMap<String,String> data = new HashMap<>();
            for(int j = 0; j < columns; j++)   // 0 1
            {
                String key = sheet.getRow(0).getCell(j).getStringCellValue(); //0 0   , 0,1
                String value = sheet.getRow(i).getCell(j).getStringCellValue();       //1 0   , 1,1
                data.put(key, value);
                // email	password
                // cmayank309@gmail.com	eventHub@123
               //  garg.mayank400@gmail.com	eventHub@123
            }
            map.add(data);
        }

        workbook.close();
        fis.close();
        return map;
    }
}