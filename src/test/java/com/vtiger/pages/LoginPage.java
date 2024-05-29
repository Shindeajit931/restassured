package com.vtiger.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.vtiger.common.PageActions;
import com.vtiger.stepsdefinitions.BaseDefinition;

public class LoginPage extends PageActions {
	
	public WebDriver driver;
	
	public LoginPage(WebDriver driver)
	{
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(name="user_name")
	WebElement tb_uid;
	
	@FindBy(name="user_password")
	WebElement tb_pass;
	
	@FindBy(name="Login123")
	WebElement btn_login;
	
	@FindBy(name="login_theme")
	WebElement dp_theme;
	
	@FindBy(xpath="//img[@src='include/images/vtiger-crm.gif']")
	WebElement img_logo;
	
	@FindBy(xpath="//font[text()='Key Modules']")
	WebElement txt_KeyModules;
	
	/*
	String uid = "user_name";
	String pass = "user_password";
	String login = "Login";
	*/
	
	/*
	By uid = By.name("user_name");
	By pass = By.name("user_password");
	By login = By.name("Login");
	*/
	
	
	
	
	
	
	public void verifyTitle(String ExpectedTitle)
	{
			validate_Exepected_Actual_Text(ExpectedTitle, driver.getTitle(), "Expected & Actual title validated successfully");			
	}
	
	
	public void verifylogo()
	{
		ElementExist(img_logo,"Logo displayed successfully");	
	}
	
	
	public void verifyKeymoduletext()
	{
		ElementExist(txt_KeyModules,"Text Key Modules displayed successfully");	
	}
	
	public void verifyalltext(String txt)
	{
		try
		{
		WebElement elm = driver.findElement(By.xpath("//*[contains(text(),'"+txt+"')]"));
		ElementExist(elm,"Text "+txt+" displayed successfully on login page");	
		}
		catch(Exception e)
		{
			BaseDefinition.logger.fail("Element not found due to error "+e.getMessage()+"<a href='"+getScreenshot() +"'><span class='label end-time'>Screenshot</span></a>");
		}
	}
	
	
	
	
	public void login(String userid, String pwd) throws InterruptedException
	{
		setUsername(userid);
		setPassword(pwd);
		clickLogin();		
	}
	
	public void login(String userid, String pwd, String theme)
	{
		setUsername(userid);
		setPassword(pwd);
		SelectTheme(theme);
		clickLogin();
		
	}
	
	public void setUsername(String userid)
	{
		SetText(tb_uid, userid,userid+" has been entered successfully in username field");
	}
	
	public void setPassword(String pwd)
	{
		SetText(tb_pass, pwd,pwd+" has been entered successfully in password field");
	}
	
	public void clickLogin()
	{
		clickElement(btn_login,"login button clicked successfully");
	}
	
	public void SelectTheme(String theme)
	{
		SelectText(dp_theme, theme,theme+" selected from theme dropdwon");
	}

}
