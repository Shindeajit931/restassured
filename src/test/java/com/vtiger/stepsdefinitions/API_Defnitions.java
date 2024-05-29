package com.vtiger.stepsdefinitions;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Scanner;

import org.apache.commons.collections4.bag.SynchronizedSortedBag;

import com.codoid.products.exception.FilloException;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.path.json.config.JsonPathConfig;
import io.restassured.path.json.config.JsonPathConfig.NumberReturnType;
import io.restassured.response.Response;

public class API_Defnitions extends BaseDefinition {
	
	public String Endpoint;
	public  Response resp;
	public StringBuilder sb;
	

@Given("url")
public void url_is() {
	Endpoint = prop.getProperty("APIEndPoint")+apidt.get(TCName).get("Path");
	System.out.println(Endpoint);
	logger.info("Endpoint ="+Endpoint);
}
@When("perform GET operation")
public void perform_get_operation() {
    resp = RestAssured.get(Endpoint);
    logger.info("Response ="+resp.getBody().asPrettyString());
}

@When("perform DELETE operation")
public void perform_delete_operation() {
    resp = RestAssured.delete(Endpoint);
    logger.info("Response ="+resp.getBody().asPrettyString());
}



@When("perform POST operation")
public void perform_post_operation() {
   
    resp = RestAssured.given()
    .baseUri(prop.getProperty("APIEndPoint"))
    .contentType(ContentType.JSON) // Specify content type as JSON
    .body(sb.toString()) // Set the request body
    .post(apidt.get(TCName).get("Path"));
    logger.info("Request ="+sb.toString());
    logger.info("Response ="+resp.getBody().asPrettyString());
    
}

@When("perform PUT operation")
public void perform_put_operation() {
   
    resp = RestAssured.given()
    .baseUri(prop.getProperty("APIEndPoint"))
    .contentType(ContentType.JSON) // Specify content type as JSON
    .body(sb.toString()) // Set the request body
    .put(apidt.get(TCName).get("Path"));
    logger.info("Request ="+sb.toString());
    logger.info("Response ="+resp.getBody().asPrettyString());
}

@When("perform PATCH operation")
public void perform_patch_operation() {
    
    resp = RestAssured.given()
    .baseUri(prop.getProperty("APIEndPoint"))
    .contentType(ContentType.JSON) // Specify content type as JSON
    .body(sb.toString()) // Set the request body
    .patch(apidt.get(TCName).get("Path"));
    logger.info("Request ="+sb.toString());
    logger.info("Response ="+resp.getBody().asPrettyString());
}

@Then("construct dynamic request")
public void construct_dynamic_request() throws FileNotFoundException {
	// JsonPathConfig config = new JsonPathConfig(NumberReturnType.BIG_DECIMAL);
	
	File f = new File(System.getProperty("user.dir")+"/src/test/resources/Templates/"+apidt.get(TCName).get("Template"));
	Scanner scan = new Scanner(f);
	sb = new StringBuilder("");
	while(scan.hasNext())
	{
		String p = scan.nextLine();
		if(p.contains("%name%"))
		{
		String s = p.replace("%name%", apidt.get(TCName).get("name"));		
		sb.append(s);
		}
		else if(p.contains("%job%"))
		{
		String s1 = p.replace("%job%", apidt.get(TCName).get("job"));
		sb.append(s1);
		}
		else
		{
			sb.append(p);
		}
	}
	
	System.out.println(sb.toString());
	/*
     JsonPath jsonPath = new JsonPath(new File(System.getProperty("user.dir")+"/src/test/resources/Templates/"+apidt.get(TCName).get("Template")));
     String str = jsonPath.toString().replace("%name%", apidt.get(TCName).get("name"));
     System.out.println(str.toString());
    */
}

@Then("verify status code")
public void status_code_should_be() {
    System.out.println(resp.getStatusCode());
    if(resp.getStatusCode()==Integer.parseInt(apidt.get(TCName).get("StatusCode")))
    {
    	logger.pass("Expected status code "+apidt.get(TCName).get("StatusCode")+" matched with actual status code "+resp.getStatusCode());
    }
    else
    {
    	logger.fail("Expected status code "+apidt.get(TCName).get("StatusCode")+" did not match with actual status code "+resp.getStatusCode());
    }
    
  
}
@Then("validate response")
public void validate_response() throws FilloException {
    System.out.println(resp.getBody().asPrettyString());
    Map<String,String> mp = apidt.get(TCName);
    for( Entry entry: mp.entrySet())
    {
    	 if(entry.getKey().toString().contains("Validation"))
    	 {
    		 String data = entry.getValue().toString();
    		 System.out.println(data);
    		 if(data.length()>0)
    		 {
    			 String[] ar = data.split("==");
    			
    			String actdata =  resp.getBody().jsonPath().get(ar[0]).toString();
    			if(ar[1].startsWith("Extract"))
    			{
    				String[] dt = ar[1].split("_");
    				//storeOutput(dt[1],actdata,dt[2]);
    			}
    			else
    			{
    				if(actdata.equals(ar[1]))
        			{
        				logger.pass(actdata+ " is matched with expected data " +ar[1]);
        			}
        			else
        			{
        				logger.fail(actdata+ " did not match with expected data " +ar[1]);
        			}
    			}
    			
    			
    			
    		
    		 }
    	 }
    }
    
}





}
