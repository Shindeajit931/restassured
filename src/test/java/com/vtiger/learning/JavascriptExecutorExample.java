package com.vtiger.learning;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class JavascriptExecutorExample {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		WebDriver driver = new ChromeDriver();
		driver.get("http://localhost:100");	
		JavascriptExecutor js = ((JavascriptExecutor)driver);
		WebElement userid = driver.findElement(By.name("user_name"));
		WebElement pwd = driver.findElement(By.name("user_password"));
		WebElement login = driver.findElement(By.name("Login"));
		//js.executeScript("document.getElementsByName('user_name').value='admin'", args);
		js.executeScript("arguments[0].value='admin';", userid);
		js.executeScript("arguments[0].value='admin';", pwd);
		js.executeScript("arguments[0].click();", login);
		
		js.executeScript("window.scrollBy(0,500)", "");

	}

}
