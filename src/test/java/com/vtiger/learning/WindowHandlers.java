package com.vtiger.learning;

import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class WindowHandlers {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		
		
		WebDriver driver = new ChromeDriver();	    
	    driver.get("http://localhost:100");
	    driver.manage().window().maximize();
	    driver.findElement(By.name("user_name")).sendKeys("admin");
        driver.findElement(By.name("user_password")).sendKeys("admin");        
        driver.findElement(By.name("Login")).click();
        driver.findElement(By.linkText("New Account")).click();
        driver.findElement(By.name("btn1")).click();
        Thread.sleep(5000);
        
        Set<String> set = driver.getWindowHandles();
        Iterator<String> iter = set.iterator();
        String parentwind = iter.next();
        System.out.println(parentwind);
        String childwind = iter.next();
        System.out.println(childwind);
        
        
       
        driver.switchTo().window(childwind);
        driver.findElement(By.linkText("vtiger")).click();
        driver.switchTo().window(parentwind);
       System.out.println(driver.findElement(By.name("account_name")).getAttribute("value")); 
		
		

	}

}
