package com.part3.ReadDataFromExcelSheet;

import java.util.List;
import java.util.stream.Collectors;

import org.junit.BeforeClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Question1 {
	static

	@BeforeMethod
	public void invocation() {
//		WebDriver driver = new ChromeDriver();
	}

	@Test
	public void retrieveThelinkByForEachLoop() {
		 WebDriver driver = new ChromeDriver();
		System.setProperty("webdriver.chrome.driver", "F:\\Automation Practice\\ReadDataFromExcelSheet\\Resources");

		driver.get("https://www.flipkart.com/");

		// Find all anchor tags on the webpage
		List<WebElement> anchorTags = driver.findElements(By.tagName("a"));

		// Iterate over each anchor tag to retrieve and print the link
		for (WebElement anchorTag : anchorTags) {
			String link = anchorTag.getAttribute("href");
			System.out.println(link);
		}

		// Close the browser
		driver.quit();
	}

	@Test
	public void retrieveTheLinkByStream() {
		 WebDriver driver = new ChromeDriver();
			System.setProperty("webdriver.chrome.driver", "F:\\Automation Practice\\ReadDataFromExcelSheet\\Resources");
		// Set path to chromedriver.exe

		// Initialize Chrome driver

		// Open the webpage
		driver.get("https://www.flipkart.com/");

		// Find all <a> elements
		List<WebElement> linkElements = driver.findElements(By.tagName("a"));

		// Extract links using Java Streams
		List<String> links = linkElements.stream().map(element -> element.getAttribute("href"))
				.collect(Collectors.toList());

		// Print the links
		links.forEach(System.out::println);

		// Close the browser
		driver.quit();

	}

	@Test
	public void retrieveTheLinkByParallelStream() {
		
		 WebDriver driver = new ChromeDriver();
			System.setProperty("webdriver.chrome.driver", "F:\\Automation Practice\\ReadDataFromExcelSheet\\Resources");

		// Initialize Chrome driver
//	        WebDriver driver = new ChromeDriver();

		// Open the webpage
		driver.get("https://www.flipkart.com/");

		// Find all <a> elements
		List<WebElement> linkElements = driver.findElements(By.tagName("a"));

		// Extract links using Java Parallel Streams
		List<String> links = linkElements.parallelStream().map(element -> element.getAttribute("href"))
				.collect(Collectors.toList());

		// Print the links
		links.forEach(System.out::println);

		// Close the browser
		driver.quit();
	}

	@Test
	public void retrieveTheLinkByLambadaExpressions() {
		 WebDriver driver = new ChromeDriver();
			System.setProperty("webdriver.chrome.driver", "F:\\Automation Practice\\ReadDataFromExcelSheet\\Resources");
		// Initialize Chrome driver
//	        WebDriver driver = new ChromeDriver();

		// Open the webpage
		driver.get("https://www.flipkart.com/");

		// Find all anchor elements on the webpage
		List<WebElement> anchorElements = driver.findElements(By.tagName("a"));

		// Extract links using lambda expressions
		List<String> links = anchorElements.stream().map(element -> element.getAttribute("href"))
				.filter(href -> href != null && !href.isEmpty()) // Filter out empty href attributes
				.collect(Collectors.toList());

		// Print the links
		links.forEach(System.out::println);

		// Close the browser
		driver.quit();
	}

}
