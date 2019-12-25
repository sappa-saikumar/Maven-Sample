package data;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Data
{

 public static Properties pro = new Properties();
	public static void  loadPropertyFile() throws FileNotFoundException, IOException
	{
		pro.load(new FileInputStream("Config.properties"));
		System.out.println("statement to learn git basics. can be removed later.");
		System.out.println("statement to learn git basics. can be removed later brach:REMB-123");
		System.out.println("statement to learn git basics. can be removed later brach:REMB-1234");		
		
	}
	public static String ReadFromPropertyFile(String property_data)
	{
		return pro.getProperty(property_data);
	}
	public static String getData(String columnname) throws IOException
	{
		File src = new File("input.xlsx");
		FileInputStream fis = new FileInputStream(src);
		
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		XSSFSheet sh = wb.getSheetAt(0);
		
		int colcount= sh.getRow(0).getPhysicalNumberOfCells();
				
		String str="";
		int j=0;
		for(int i=0;i<colcount;)
		{
			String s1=sh.getRow(j).getCell(i).getStringCellValue();
			
		
			if(s1.equalsIgnoreCase(columnname))
			{
				str=sh.getRow(j+1).getCell(i).getStringCellValue();
			}
			i=i+1;
		}		
		return str;
	}
}
