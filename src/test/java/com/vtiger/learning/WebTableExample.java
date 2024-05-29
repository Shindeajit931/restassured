package com.vtiger.learning;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class WebTableExample {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		String x = "//table[@class='FormBorder']/tbody/tr[5]/td[4]";
		
		  WebDriver driver = new ChromeDriver();	    
		    driver.get("http://localhost:100");
		    driver.manage().window().maximize();
		    driver.findElement(By.name("user_name")).sendKeys("admin");
	        driver.findElement(By.name("user_password")).sendKeys("admin");        
	        driver.findElement(By.name("Login")).click();
	        driver.findElement(By.linkText("Leads")).click();
	        
	        String dt = "dsfsf2141";
	        
	        driver.findElement(By.linkText(dt)).getText();
	        
	        for(int i = 1; i<=20;i++)
	        {
	        	String txt  = driver.findElement(By.xpath("//table[@class='FormBorder']/tbody/tr["+(i+4)+"]/td[4]")).getText();
	             System.out.println(txt);
	             if(txt.equals("company"))
	             {
	            	 driver.findElements(By.name("selected_id")).get(i-1).click();
	            	 driver.findElement(By.xpath("//input[@value='Delete']")).click();
	            	 break;
	             }
	        
	        }

	}

}
