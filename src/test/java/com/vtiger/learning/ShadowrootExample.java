package com.vtiger.learning;

import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class ShadowrootExample {
	
	public static void main(String[] args) {
		
		
		
			
	    WebDriver driver = new ChromeDriver();	    
	    driver.get("https://books-pwakit.appspot.com/");
	    driver.manage().window().maximize();
	    WebElement shadowHost = driver.findElement (By.cssSelector ("book-app"));
	       SearchContext shadowRoot = shadowHost.getShadowRoot();
	       WebElement shadowContent = shadowRoot.findElement (By.cssSelector ("app-header"));
	       SearchContext shadowRootTwo = shadowContent.getShadowRoot ();
	       WebElement shadowtoolbar = shadowRootTwo.findElement(By.cssSelector("app-toolbar[class='toolbar-bottom']"));
	       SearchContext shadowRootthree = shadowtoolbar.getShadowRoot();
	       WebElement shadowtooldecorator = shadowRootthree.findElement (By.cssSelector ("book-input-decorator"));
	       SearchContext shadowRootfour = shadowtooldecorator.getShadowRoot();
	       WebElement shadowtoolinput = shadowRootfour.findElement (By.cssSelector ("#input"));
	       shadowtoolinput.sendKeys("Math");
	       
	       
	}

}
