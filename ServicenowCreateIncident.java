package assignmentsweek5.day2;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class ServicenowCreateIncident extends BaseClassServiceNow {
	
	@Test(dataProvider="createData")
	public void createIncident(String shortdes, String des) throws InterruptedException, IOException {

		Thread.sleep(3000);
		driver.findElement(By.xpath("(//div[@class='sn-widget-list-title' and text()='All'])[2]")).click();

		WebElement frame2 = driver.findElement(By.xpath("//iframe[@id='gsft_main']"));
		driver.switchTo().frame(frame2);
		System.out.println(driver.getTitle());
		driver.findElement(By.xpath("//button[@id='sysverb_new']")).click();
		driver.findElement(By.id("lookup.incident.caller_id")).click();
		Set<String> windowHandlesSet = driver.getWindowHandles();
		List<String> windowHandlesList = new ArrayList<String>(windowHandlesSet);
		driver.switchTo().window(windowHandlesList.get(1));
		System.out.println(driver.getTitle());

		driver.findElement(By.xpath("//a[text()='System Administrator']")).click();

		driver.switchTo().window(windowHandlesList.get(0));
		driver.switchTo().frame(frame2);
		driver.findElement(By.id("incident.short_description")).sendKeys(shortdes);//My phone software faliure
		driver.findElement(By.id("incident.description"))
				.sendKeys(des);//\can you check my phone within 2 days and update software
		String getIncNo = driver.findElement(By.id("incident.number")).getAttribute("value");
		System.out.println("Incident Number is " + getIncNo);

		driver.findElement(By.id("sysverb_insert_bottom")).click();
		WebElement number = driver.findElement(By.xpath("(//input[@class='form-control'])[1]"));// (getIncNo);
		number.sendKeys(getIncNo);
		number.sendKeys(Keys.ENTER);

		WebElement resultIncNo = driver.findElement(By.xpath("//a[@class='linked formlink']"));
		String resultIncNoChk = resultIncNo.getText();

		if (getIncNo.equalsIgnoreCase(resultIncNoChk)) {
			System.out.println("The incident created successfully");
		} else {
			System.out.println("*****Erro!**** incident is not created");
		}
		resultIncNo.click();
		System.out.println(driver.getTitle());
		File src = driver.getScreenshotAs(OutputType.FILE);
		File dst = new File("./snaps/createIncident.png");
		FileUtils.copyFile(src, dst);

	}
	@DataProvider
	public String[][] createData() throws IOException{
		return ReadXLCreateIncident.readData();
	}

}
