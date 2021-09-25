package assignmentsweek5.day2;
import java.io.IOException;


import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.Test;


public class ReadXLDeleteLead {
	

	@Test
		public static String[][] deleteData() throws IOException {
			// Steps: locate the workbook (set up the path)
			XSSFWorkbook wb = new XSSFWorkbook("./excel/DeleteLead.xlsx");

			// Step2: get into the worksheet
			XSSFSheet ws = wb.getSheet("Sheet1");

			int lastRowNum = ws.getLastRowNum();
			// System.out.println(lastRowNum);

			int cellCount = ws.getRow(0).getLastCellNum();
			// System.out.println(cellCount);
			String[][] data = new String[lastRowNum][cellCount];
			
			for (int i = 1; i <= lastRowNum; i++) {
				for (int j = 0; j < cellCount; j++) {

					String stringCellValue2 = ws.getRow(i).getCell(j).getStringCellValue();
					System.out.println(stringCellValue2);
					// i-1 is starting row index is 1
					data[i-1][j] = stringCellValue2;
				}
			}
			wb.close();
			return data; 
		
		}
	}

