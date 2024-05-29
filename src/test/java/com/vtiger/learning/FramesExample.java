package com.vtiger.learning;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class FramesExample {
	
	
	public static void main(String[] args) throws InterruptedException, IOException {
		
		
		
		 WebDriver driver = new ChromeDriver();	    
		    driver.get("https://demoqa.com/frames");
		    driver.manage().window().maximize();
		    driver.switchTo().frame("frame1");
		    String s = driver.findElements(By.xpath("//*[@id='sampleHeading']")).get(0).getText();
		    System.out.println(s);
		    driver.switchTo().defaultContent();
		    
		    driver.switchTo().frame("frame2");
		    String s1 = driver.findElements(By.xpath("//*[@id='sampleHeading']")).get(0).getText();
		    System.out.println(s1);
		    driver.switchTo().defaultContent();
		    
		    driver.findElement(By.xpath("//*[text()='Elements']")).click();
		    
	}

}
