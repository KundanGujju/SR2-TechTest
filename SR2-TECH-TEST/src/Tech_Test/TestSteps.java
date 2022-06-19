package Tech_Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import BaseCode.ReadAndWriteFile;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class TestSteps {

	WebDriver driver;
	List<WebElement> summary = null;
	List<WebElement> carDetails = null;

	String firstXpath = "/html/body/div/div/div[2]/div[3]/div[1]/div/span/div[2]/dl[";
	String secondXpath = "]/dt";
	String thirdXpath = "]/dd";

	@SuppressWarnings("deprecation")
	@Given("^I navigate to cartaxcheck website$")
	public void getURL() {
		System.setProperty("webdriver.chrome.driver",
				"C:\\Users\\KR12\\Desktop\\APITesting\\SR2-TECH-TEST\\src\\Resources\\chromedriver.exe");

		driver = new ChromeDriver();
		driver.get("https://cartaxcheck.co.uk/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}

	@When("^I get details of (.*) car reg$")
	public void getCarDetails(int nthCar) throws IOException {
		driver.findElement(By.id("vrm-input")).sendKeys(ReadAndWriteFile.readCarInputFile(nthCar));
		driver.findElement(By.cssSelector("button.jsx-1164392954")).click();
	}

	@Then("^I retireve Car details")
	public void getCarDetails() {

		for (int i = 1; i <= 5; i++) {

			summary.add(driver.findElement(By.xpath(firstXpath + i + secondXpath)));
			carDetails.add(driver.findElement(By.xpath(firstXpath + i + thirdXpath)));
			driver.quit();

		}

	}

	@And("^I write car details in txt file$")
	public void writeCarDetailsInFile() throws IOException {

		FileWriter file = new FileWriter(
				"C:\\Users\\KR12\\Desktop\\APITesting\\SR2-TECH-TEST\\src\\Resources\\Result_car_output.txt");
		file.write(summary.toString());
		file.write(carDetails.toString());
	}

	@And("^I validate the results against car_output file$")
	public void validateResultsAgainstCarOutputFile() throws IOException {

		File Results_car_output = new File(
				"C:\\Users\\KR12\\Desktop\\APITesting\\SR2-TECH-TEST\\src\\Resources\\Result_car_output.txt");
		File car_output = new File(
				"C:\\Users\\KR12\\Desktop\\APITesting\\SR2-TECH-TEST\\src\\Resources\\car_output.txt");

		FileReader r1 = new FileReader(Results_car_output);
		FileReader r2 = new FileReader(car_output);

		BufferedReader reader1 = new BufferedReader(r1);
		BufferedReader reader2 = new BufferedReader(r2);

		String str1 = reader1.readLine();
		String str2 = reader2.readLine();

		boolean areEqual = false;

		while (str1 != null && str2 != null) {
			if (str1.equalsIgnoreCase(str2)) {
				areEqual = true;
			} else {
				areEqual = false;
				break;
			}

			str1 = reader1.readLine();
			str2 = reader2.readLine();
		}
		
		if(areEqual) {
			System.out.println("Files are same");
		} else
			System.out.println("Files are NOT Same");
	}

}
