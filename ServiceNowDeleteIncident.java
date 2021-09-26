package assignmentsweek5.day2;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

public class ServiceNowDeleteIncident extends BaseClassServiceNow {

	@Test
	public void deleteIncident() throws InterruptedException, IOException {
		Thread.sleep(3000);
		driver.findElement(By.xpath("(//div[@class='sn-widget-list-title' and text()='All'])[2]")).click();

		WebElement frame2 = driver.findElement(By.xpath("//iframe[@id='gsft_main']"));
		driver.switchTo().frame(frame2);
		System.out.println(driver.getTitle());
		WebElement incidentno = driver.findElement(By.xpath("(//a[@class='linked formlink'])[3]"));
		String getincText = incidentno.getText();
		System.out.println("IncidentNumber: " + getincText);
		incidentno.click();

		driver.findElement(By.id("sysverb_delete")).click();
		driver.findElement(By.id("ok_button")).click();
		Thread.sleep(3000);
		WebElement dltIncNo = driver.findElement(By.xpath("(//input[@class='form-control'])[1]"));
		dltIncNo.sendKeys(getincText);
		dltIncNo.sendKeys(Keys.ENTER);
		String result = driver.findElement(By.xpath("(//table)[2]//tbody//td")).getText();
		System.out.println("The Incident number is " + result);
		if (result.equalsIgnoreCase("No records to display")) {
			System.out.println("The Incident is Deleted Successfully!!!!");
		} else {
			System.out.println("ERROR! Inicident is Not Delted");
		}
		File src = driver.getScreenshotAs(OutputType.FILE);
		File dst = new File("./snaps/deletedIncident.png");
		FileUtils.copyFile(src, dst);
	}

}
