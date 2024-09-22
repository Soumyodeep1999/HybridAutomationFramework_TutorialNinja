package utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Date;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;

public class Utility {

	public static final int IMPLICIT_WAIT_TIME = 10;
	public static final int PAGE_LOAD_TIMEOUT = 10;

	public static String emailGeneratorWithDateAndTime() {

		Date date = new Date();
		return "testemail" + date.toString().replace(" ", "_").replace(":", "_") + "@gmail.com";

	}

	public static String dateTimeAdder() {
		return new Date().toString().replace(" ", "_").replace(":", "_");
	}

	public static Object[][] getDataFromExcel(String sheetName) {

		File excelFile = new File(
				System.getProperty("user.dir") + "\\src\\main\\java\\testdata\\TutorialPointTestData.xlsx");
		XSSFWorkbook workbook = null;

		try {
			FileInputStream fip = new FileInputStream(excelFile);
			workbook = new XSSFWorkbook(fip);
		} catch (Throwable e) {
			e.printStackTrace();
		}

		XSSFSheet sheet = workbook.getSheet(sheetName);
		int rowCount = sheet.getLastRowNum();
		int colCount = sheet.getRow(0).getLastCellNum();

		Object[][] testdata = new Object[rowCount][colCount];

		for (int i = 0; i < rowCount; i++) {

			XSSFRow row = sheet.getRow(i + 1);

			for (int j = 0; j < colCount; j++) {

				XSSFCell cell = row.getCell(j);
				CellType celltype = cell.getCellType();

				switch (celltype) {
				case STRING:
					testdata[i][j] = cell.getStringCellValue();
					break;
				case NUMERIC:
					testdata[i][j] = Integer.toString((int) cell.getNumericCellValue());
					break;
				case BOOLEAN:
					testdata[i][j] = cell.getBooleanCellValue();
				}
			}
		}
		return testdata;
	}

	public static String captureScreenshot(WebDriver driver, String testName) {

		File srcScreenshotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		String destinationPath = System.getProperty("user.dir") + "\\screenshot\\" + testName + Utility.dateTimeAdder()
				+ ".png";

		try {
			FileHandler.copy(srcScreenshotFile, new File(destinationPath));
		} catch (IOException e) {

			e.printStackTrace();
		}

		return destinationPath;

	}

}
