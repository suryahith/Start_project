package com.zoho.testcases;

import java.util.Hashtable;

import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import com.relevantcodes.extentreports.LogStatus;
import com.zoho.base.DataUtil;
import com.zoho.base.Xls_Reader;
import com.zoho.base.basetest;

public class LoginTest extends basetest{
	String testCaseName="TestA";
	Xls_Reader xls;
 SoftAssert softAssert;
  @Test(dataProvider="getData")
	public void dologintest(Hashtable<String,String> data)
	{
		softAssert = new SoftAssert();
		test = rep.startTest("DummyTestB");
		
		test.log(LogStatus.INFO, "Starting the test test B");
		test.log(LogStatus.INFO,data.toString());
		if(!DataUtil.isRunnable(testCaseName, xls) ||  data.get("Runmode").equals("N"))
		{
			test.log(LogStatus.SKIP, "Skipping the test as runmode is N");
			throw new SkipException("Skipping the test as runmode is N");
		}
			openBrowser(data.get("Browser"));
			navigate("appurl");
			
			boolean actualResult=dologin(data.get("Username"), data.get("Password"));
			boolean expectedresult =false;
			if(data.get("Expectedresult").equals("Y"))
			{
				expectedresult=true;
			}
			else {
				expectedresult=false;
				
			}
			
			if(actualResult!=expectedresult)
			{
				reportFailure("logintestfailed");
				
			}
			else
			{
				
			}reportPass("Login Test Passed");
		
	}
			@BeforeMethod
			public void init(){
				super.init();
				softAssert = new SoftAssert();
			}
			
			
			@AfterMethod
			public void quit(){
				try{
					softAssert.assertAll();
				}catch(Error e){
					test.log(LogStatus.FAIL, e.getMessage());
				}
				if(rep!=null){
					rep.endTest(test);
					rep.flush();
				}
				
				if(driver!=null)
					driver.quit();
			}
		
		
		@DataProvider(parallel=true)
			public Object[][] getData()
			{		
				super.init();	
				xls = new Xls_Reader(prop.getProperty("xlspath"));
				return DataUtil.getTestData(xls, testCaseName);
				
			}


		}
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	
