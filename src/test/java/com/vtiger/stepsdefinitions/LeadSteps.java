package com.vtiger.stepsdefinitions;

import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;

import io.cucumber.java.en.When;

public class LeadSteps extends BaseDefinition {
	

@When("user click on new lead link")
public void click_new_lead() {
    driver.findElement(By.linkText("New Lead")).click();
}

@When("fill all mandatory fileds and click on save button")
public void fill_mandatory_lead_form() {
	
	driver.findElement(By.name("lastname")).sendKeys(dt.get(TCName).get("LastName"));	
    driver.findElement(By.name("company")).sendKeys(dt.get(TCName).get("Company"));
    driver.findElement(By.name("button")).click();
}

@When("lead should be created successfully")
public void validate_lead_creation() {
    driver.findElement(By.xpath("//td[text()='Last Name:']/following::td[1]")).getText().equals("Modi");
    driver.findElement(By.xpath("//td[text()='Company:']/following::td[1]")).getText().equals("BJP");
}



@When("user creates multiple leads with {string} and {string} and verified")
public void user_creates_multiple_leads_with_and_and_verified(String string, String string2, io.cucumber.datatable.DataTable dataTable) {
    // Write code here that turns the phrase above into concrete actions
    // For automatic transformation, change DataTable to one of
    // E, List<E>, List<List<E>>, List<Map<K,V>>, Map<K,V> or
    // Map<K, List<V>>. E,K,V must be a String, Integer, Float,
    // Double, Byte, Short, Long, BigInteger or BigDecimal.
    //
    // For other transformations you can register a DataTableType.
	List<Map<String,String>> dt = dataTable.asMaps();
	for(Map<String,String> m : dt)
	{
	driver.findElement(By.linkText("New Lead")).click();
	driver.findElement(By.name("lastname")).sendKeys(m.get("lastname"));	
    driver.findElement(By.name("company")).sendKeys(m.get("company"));
    driver.findElement(By.name("button")).click();
	 driver.findElement(By.xpath("//td[text()='Last Name:']/following::td[1]")).getText().equals(m.get("lastname"));
	 driver.findElement(By.xpath("//td[text()='Company:']/following::td[1]")).getText().equals(m.get("company"));
	}
   
}
@When("click on logout")
public void click_on_logout() {
    driver.findElement(By.linkText("Logout")).click();
}






}
