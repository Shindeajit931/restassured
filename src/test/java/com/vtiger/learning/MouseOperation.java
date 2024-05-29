package com.vtiger.learning;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class MouseOperation {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		
		
		
		WebDriver driver = new ChromeDriver();
		driver.get("http://localhost:100");		
		driver.findElement(By.name("user_name")).sendKeys("admin");
		driver.findElement(By.name("user_password")).sendKeys("admin");
		driver.findElement(By.name("Login")).click();
		
		Actions act = new Actions(driver);
		act.moveToElement(driver.findElement(By.id("showSubMenu"))).perform();
		Thread.sleep(2000);
		
		act.click(driver.findElement(By.linkText("New Vendor"))).perform();
		
		act.scrollToElement(driver.findElement(By.linkText("Copyrights"))).perform();
		Thread.sleep(3000);
		act.scrollToElement(driver.findElement(By.linkText("My Account"))).perform();
		act.click(driver.findElement(By.linkText("My Account"))).perform();
		
		act.click(driver.findElement(By.name("Customise"))).perform();
		
		act.dragAndDrop(driver.findElement(By.id("cl2")), driver.findElement(By.id("cl6"))).perform();
		
		if(driver.findElement(By.id("cl6")).getText().equals("Calendar"))
		{
			System.out.println("PASS");
		}
		
			
		

	}

}
