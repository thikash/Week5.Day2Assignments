package assignmentsweek5.day2;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class ServiceNowUpdateIncident extends BaseClassServiceNow {

	@Test(dataProvider="updateData")
	public void updateIncident(String incNO) throws InterruptedException, IOException {
		Thread.sleep(3000);
		driver.findElement(By.xpath("(//div[@class='sn-widget-list-title' and text()='All'])[2]")).click();

		WebElement frame2 = driver.findElement(By.xpath("//iframe[@id='gsft_main']"));
		driver.switchTo().frame(frame2);
		System.out.println(driver.getTitle());
		WebElement NumsearchBox = driver.findElement(
				By.xpath("//span[@class='input-group-addon input-group-select']/following-sibling::input"));
		NumsearchBox.sendKeys(incNO);//INC0010021
		NumsearchBox.sendKeys(Keys.ENTER);

		driver.findElement(By.xpath("//a[@class='linked formlink']")).click();
		WebElement category = driver.findElement(By.xpath("(//select[@role='listbox'])[1]"));
		Select dropcategory = new Select(category);
		dropcategory.selectByValue("software");
		WebElement contact = driver.findElement(By.xpath("//select[@id='incident.contact_type']"));
		Select con = new Select(contact);
		con.selectByVisibleText("Phone");
		WebElement state = driver.findElement(By.id("incident.state"));
		Select s = new Select(state);
		s.selectByValue("2");
		WebElement impact = driver.findElement(By.id("incident.impact"));
		Select i = new Select(impact);
		i.selectByValue("2");
		WebElement subCategory = driver.findElement(By.id("incident.subcategory"));
		Select sc = new Select(subCategory);
		sc.selectByValue("os");

		// WebElement frame3 =
		// driver.findElement(By.xpath("//iframe[@id='gsft_main']"));
		// driver.switchTo().frame(frame3);
		// driver.findElement(By.id("activity-stream-work_notes-textarea")).sendKeys("All
		// are updated");
		// WebElement frame4 =
		// driver.findElement(By.xpath("//iframe[@id='gsft_main']"));
		// driver.switchTo().frame(frame4);
		driver.findElement(By.id("sysverb_update")).click();

		String priority = driver.findElement(By.xpath("(//table)[2]//tbody//td[7]")).getText();
		System.out.println("The priority is  " + priority);
		if (priority.equalsIgnoreCase("4 - Low")) {
			System.out.println("The Priority is Same");
		} else {
			System.out.println("***ERROR!******");
		}
		String state1 = driver.findElement(By.xpath("(//table)[2]//tbody//td[8]")).getText();
		System.out.println("The State is  " + state1);
		if (state1.equalsIgnoreCase("In Progress")) {
			System.out.println("The state is Same");
		} else {
			System.out.println("*****ERROR!*********");
		}
		String cat = driver.findElement(By.xpath("(//table)[2]//tbody//td[9]")).getText();
		System.out.println("The Category is  " + cat);
		if (cat.equalsIgnoreCase("Software")) {
			System.out.println("The Category is Same");
		} else {
			System.out.println("*****ERROR!*********");
		}
		WebElement incidentnum = driver.findElement(By.xpath("//a[@class='linked formlink']"));
		String text = incidentnum.getText();
		System.out.println(text);
		incidentnum.click();
		File src = driver.getScreenshotAs(OutputType.FILE);
		File dst = new File("./snaps/updateincident.png");
		FileUtils.copyFile(src, dst);
	}
	
	@DataProvider
	public String[][] updateData() throws IOException{
		return ReadXLUpdateIncident.readData();
	}
	

}
