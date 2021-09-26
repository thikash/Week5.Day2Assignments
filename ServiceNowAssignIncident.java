package assignmentsweek5.day2;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class ServiceNowAssignIncident extends BaseClassServiceNow {

	@Test(dataProvider="assignData")
	public void assign(String shortdes) throws InterruptedException {
		Thread.sleep(3000);
		

		driver.findElement(By.xpath("(//div[@class='sn-widget-list-title' and text()='Open'])[1]")).click();
		WebElement frame2 = driver.findElement(By.xpath("//iframe[@id='gsft_main']"));
		driver.switchTo().frame(frame2);
		Thread.sleep(2000);
		driver.findElement(By.xpath("(//a[@class='linked formlink'])[2]")).click();

		driver.findElement(By.id("lookup.incident.assignment_group")).click();
		Set<String> windowHandlesSet = driver.getWindowHandles();
		List<String> windowHandlesList = new ArrayList<String>(windowHandlesSet);
		driver.switchTo().window(windowHandlesList.get(1));

		WebElement searchBox = driver.findElement(By.xpath("(//input[@class='form-control'])[1]"));
		searchBox.sendKeys("software");
		searchBox.sendKeys(Keys.ENTER);
		Thread.sleep(3000);
		driver.findElement(By.xpath("(//a[@class='glide_ref_item_link'])[1]")).click();

		driver.switchTo().window(windowHandlesList.get(0));
		WebElement frame3 = driver.findElement(By.xpath("//iframe[@id='gsft_main']"));
		driver.switchTo().frame(frame3);
		driver.findElement(By.id("activity-stream-textarea")).sendKeys(shortdes);
		driver.findElement(By.id("sysverb_update")).click();

		String assignmentGrp = driver.findElement(By.xpath("(//a[@class='linked'])[4]")).getText();
		System.out.println("The Assignment Group update is " + assignmentGrp);

	
	}
	@DataProvider
	public String[][] assignData() throws IOException{
		return ReadXLassign.readData();
	}
}
