package com.vtiger.learning;

import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class HandlesBrowserTabs {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		
		WebDriver driver = new ChromeDriver();	    
	    driver.get("http://localhost:100");
	    driver.manage().window().maximize();
	    driver.findElement(By.linkText("vtiger Customer Portal")).click();
	    Thread.sleep(5000);
	    Set<String> set = driver.getWindowHandles();
        Iterator<String> iter = set.iterator();
        String firsttab = iter.next();
      
        String secondtab = iter.next();
        driver.switchTo().window(secondtab);
        driver.findElement(By.id("login")).click();
        driver.close();
        driver.switchTo().window(firsttab);
        driver.findElement(By.name("user_password")).sendKeys("admin");
        
        

	}

}
