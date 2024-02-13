package com.part3.ReadDataFromExcelSheet;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Question3 {

	static WebDriver driver = new ChromeDriver();

	@BeforeClass
	public void invocation() {
		System.setProperty("webdriver.chrome.driver", "F:\\Automation Practice\\ReadDataFromExcelSheet\\Resources");
	}

	@Test
	public static Map<String, String> readDataFromExcelSheet() throws IOException {
		Map<String, String> dataMap = new HashMap<>();
		try {
			FileInputStream file = new FileInputStream("F:\\Automation Practice\\ReadDataFromExcelSheet\\Resources\\Testdata");
			Workbook book = new XSSFWorkbook(file);

			book.getSheetAt(0);// first sheet

			Sheet sheet = book.getSheetAt(0);

			int lastRowNumber = sheet.getLastRowNum();

			for (int i = 0; i <= lastRowNumber; i++) {
				Row row = sheet.getRow(i);
				Cell keyCell = row.getCell(0);
				String key = keyCell.getStringCellValue().trim();

				Cell valueCell = row.getCell(1);
				String value = valueCell.getRichStringCellValue().getString().trim();
				dataMap.put(key, value);
			}

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return dataMap;

	}

	@Test
	public static Map<String, String>  readDataFromSelenium() {
		Map<String, String> dataMap = new HashMap<>();
		driver.get("https://money.rediff.com/losers/bse/daily/groupa%7C%7C");
		
		List<WebElement> currentPrice = driver.findElements(By.xpath("//table[@class='dataTable']//tbody//td[4]"));

		List<WebElement> companyName = driver.findElements(By.xpath("//table[@class='dataTable']//tbody//td[1]"));

		for (int i = 0; i <= currentPrice.size() - 1; i++) {
			dataMap.put(companyName.get(i).getText(), currentPrice.get(i).getText());
		}
		return dataMap;
	}

	@Test
	public void runTC() throws IOException {
		Map<String, String> excelData = readDataFromExcelSheet();

        // Extract data from Selenium WebDriver and store it in another map
        
        Map<String, String> webData = readDataFromSelenium();
        driver.quit();

        // Compare both maps
        boolean isEqual = compareMaps(excelData, webData);
        System.out.println("Maps are equal: " + isEqual);
	}
	
	private static boolean compareMaps(Map<String, String> map1, Map<String, String> map2) {
        return map1.equals(map2);
    }

}
