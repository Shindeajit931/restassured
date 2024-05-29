package com.vtiger.stepsdefinitions;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.codoid.products.exception.FilloException;
import com.codoid.products.fillo.Connection;
import com.codoid.products.fillo.Fillo;
import com.codoid.products.fillo.Recordset;
import com.vtiger.pages.LoginPage;
import com.vtiger.pages.PageObjectManager;



public class BaseDefinition  {
	
	public static WebDriver driver;
	public static Properties prop;
	public static Map<String,Map<String,String>> dt;
	public static Map<String,Map<String,String>> apidt;
	public static String TCName;
	public static ExtentReports extent;
	public static ExtentTest logger;
	
	
	
	
	public void initiation()
	{
		if(extent==null)
		createExtentReport();
		readproperties();
		dt = readExcelData(System.getProperty("user.dir")+"/src/test/resources/Data/testdata.xlsx","Sheet1");
		apidt = readExcelData(System.getProperty("user.dir")+"/src/test/resources/Data/api_data.xlsx","Sheet1");
		System.out.println(apidt);		
		
	}
	
	public void launchApp()
	{
		if(prop.getProperty("browser").equals("firefox"))
		{
			
			driver = new FirefoxDriver();
		}
		else if(prop.getProperty("browser").equals("edge"))
		{
			
			driver = new EdgeDriver();
		}
		else if(prop.getProperty("browser").equals("headless"))
		{
			ChromeOptions option = new ChromeOptions();
			option.addArguments("--headless=new");
			driver = new ChromeDriver(option);
		}
		else
		{
			
			driver = new ChromeDriver();
		}
		driver.get(prop.getProperty("AppUrl"));
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Integer.parseInt(prop.getProperty("implicitwait"))));
		
	}
	
	public void readproperties() {
		 prop = new Properties();
		try {
			FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"/src/test/resources/config/settings.properties");
		    prop.load(fis);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	public Map<String,Map<String,String>> readExcelData(String file, String Sheet)
	{
		 Map<String,Map<String,String>> dt = new HashMap<>();
		try
		{
			
	   		
		Fillo fillo=new Fillo();
		Connection connection=fillo.getConnection(file);
		String strQuery="Select * from "+Sheet;
		Recordset recordset=connection.executeQuery(strQuery);
		int rowcount = recordset.getCount();
		System.out.println("row count="+rowcount);
		List<String> lst  = recordset.getFieldNames();
		int clmcount = lst.size();
		 
		while(recordset.next()){
		
		Map<String,String> rowdata = new HashMap<>();
		
		  for(int i=0;i<clmcount;i++)
		  {
			  rowdata.put(lst.get(i), recordset.getField(lst.get(i)));
		  }
		
		  dt.put(recordset.getField("TCName"), rowdata);
		}
		 
		
		recordset.close();
		connection.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return dt;
	}
	
	
	public void storeOutput(String variable, String value, String rowid) throws FilloException
	{
	int id = Integer.parseInt(rowid);
	Fillo fillo=new Fillo();
	Connection connection=fillo.getConnection(System.getProperty("user.dir")+"/src/test/resources/Data/api_data.xlsx");
	String strQuery="Update Output set "+variable+"='"+value+"' where RowID='"+id+"'"; 
	connection.executeUpdate(strQuery);
	 
	connection.close();
	}
	
	public void createExtentReport()
	{
		Date d = new Date();
		DateFormat ft = new SimpleDateFormat("ddMMyyyyhhmmss");
		String fileName = ft.format(d);
		ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir") + "/src/test/java/com/vtiger/reports/ExtentReport"+fileName+".html");
    	// Create an object of Extent Reports
		extent = new ExtentReports();  
		extent.attachReporter(htmlReporter);
		extent.setSystemInfo("Host Name", "Automation Test Hub");
		    	extent.setSystemInfo("Environment", "Test");
		extent.setSystemInfo("User Name", "Rajesh U");
		htmlReporter.config().setDocumentTitle("Title of the Report Comes here "); 
		            // Name of the report
		htmlReporter.config().setReportName("Name of the Report Comes here "); 
		            // Dark Theme
		htmlReporter.config().setTheme(Theme.DARK); 
		
	}
	
	

}
