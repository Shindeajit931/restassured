package com.vtiger.stepsdefinitions;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import com.vtiger.pages.LoginPage;
import com.vtiger.pages.PageObjectManager;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class loginStepsDefinitions extends BaseDefinition {
	public PageObjectManager pageobjectmanager;
	
@Before
public void getScenarioName(Scenario scenario)
{
	TCName = scenario.getName();
	initiation();
	logger = extent.createTest(TCName);
}

@After
public void closeApp()
{
	extent.flush();
	
}
	
	

@Given("user should be on login page")
public void user_should_be_on_login_page() {
	
	
	if(driver==null)
	{
	launchApp();
	}
	
	pageobjectmanager = new PageObjectManager(driver);
 
    
	
}

@When("user can verify the title")
public void verifytitle() {
	pageobjectmanager.getLoginPage().verifyTitle(dt.get(TCName).get("Titile"));	
}

@When("user can verify the logo")
public void verifylogo() {
	pageobjectmanager.getLoginPage().verifylogo();	
}

@When("user can verify the Keymodule text")
public void userKeymoduletext() {
	pageobjectmanager.getLoginPage().verifylogo();	
}

@When("user enters valid credentials")
public void user_enters_valid_credentials() {
	pageobjectmanager.getLoginPage().setUsername(prop.getProperty("userid"));
	pageobjectmanager.getLoginPage().setPassword(prop.getProperty("password"));
	
}
@When("click on login button")
public void click_on_login_button() {
 
	pageobjectmanager.getLoginPage().clickLogin();
   
}
@Then("user should be navigated to home page")
public void user_should_be_navigated_to_home_page() {
	driver.findElement(By.linkText("Home")).isDisplayed();
   
}
@Then("user can click logout link")
public void user_can_click_logout_link() {
    driver.findElement(By.linkText("Logout")).click();
}



@When("user enters invalid credentials")
public void user_enters_invalid_credentials() {
	pageobjectmanager.getLoginPage().setUsername("admin232");
	pageobjectmanager.getLoginPage().setPassword("admin346");
}
@When("user can validate error message on login page")
public void user_can_validate_error_message_on_login_page() {
    driver.findElement(By.xpath("//*[contains(text(),'You must specify')]")).isDisplayed();
}


@When("user enters userid as {string} and password as {string} credentials")
public void user_enters_userid_as_and_password_as_credentials(String uid, String pwd) {
	pageobjectmanager.getLoginPage().setUsername(uid);
	pageobjectmanager.getLoginPage().setPassword(pwd);
}


@When("user can verify the existing text {string}")
public void verify_all_text(String txt) {
	pageobjectmanager.getLoginPage().verifyalltext(txt);
}





}
