package assignmentsweek5.day2;

import java.io.IOException;

import org.openqa.selenium.By;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import Week5.day2.ReadXLCreateLead;

public class CreateLead extends BaseClassLT {
	@Test(dataProvider = "createDataLT")
	public void runCreatelead(String companyName, String lastName, String firstName, String phoneno) throws InterruptedException {
		
	
	driver.findElement(By.linkText("Create Lead")).click();
	
	driver.findElement(By.id("createLeadForm_companyName")).sendKeys(companyName);
	driver.findElement(By.id("createLeadForm_firstName")).sendKeys(firstName);
	driver.findElement(By.id("createLeadForm_lastName")).sendKeys(lastName);
	driver.findElement(By.id("createLeadForm_primaryPhoneNumber")).sendKeys(phoneno);
	driver.findElement(By.name("submitButton")).click();

}
	@DataProvider
	public String[][] createDataLT() throws IOException{
		return  ReadXLCreateLead.readData();
	}
}