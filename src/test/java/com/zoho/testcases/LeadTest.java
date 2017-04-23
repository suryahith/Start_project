package com.zoho.testcases;

import java.util.Hashtable;

import org.testng.SkipException;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.relevantcodes.extentreports.LogStatus;
import com.zoho.base.DataUtil;
import com.zoho.base.Xls_Reader;
import com.zoho.base.basetest;

public class LeadTest extends basetest {
	Xls_Reader xls;
	SoftAssert softAssert;
	


@Test(priority=1)
public void createLeadTest(Hashtable<String,String> data){
		
	test = rep.startTest("CreateLeadTest");
	test.log(LogStatus.INFO, data.toString());
	if(!DataUtil.isRunnable("CreateLeadTest", xls) ||  data.get("Runmode").equals("N")){
		test.log(LogStatus.SKIP, "Skipping the test as runmode is N");
		throw new SkipException("Skipping the test as runmode is N");
	}
	
	openBrowser(data.get("Browser"));
	navigate("appurl");
	dologin(prop.getProperty("username"), prop.getProperty("password"));
	click("crmlink_xpath");
	click("leadsTab_xpath");
	click("newLeadButton_xpath");
	type("leadCompany_xpath", data.get("LeadCompany"));
	type("leadLastName_xpath", data.get("LeadLastName"));
	click("leadSaveButton_xpath");
	

	clickAndWait("leadsTab_xpath","newLeadButton_xpath");
	int rNum=getLeadRowNum(data.get("LeadLastName"));
	if(rNum==-1)
		reportFailure("Lead not found in lead table "+ data.get("LeadLastName"));

	reportPass("Lead found in lead table "+ data.get("LeadLastName")); 
	takeScreenShot();
}
@Test(priority=2,dataProvider="getData")
public void convertLeadTest(Hashtable<String,String> data){

	
	test = rep.startTest("ConvertLeadTest");
	test.log(LogStatus.INFO, data.toString());
	if(!DataUtil.isRunnable("ConvertLeadTest", xls) ||  data.get("Runmode").equals("N")){
		test.log(LogStatus.SKIP, "Skipping the test as runmode is N");
		throw new SkipException("Skipping the test as runmode is N");
	}
	
	openBrowser(data.get("Browser"));
	navigate("appurl");
	dologin(prop.getProperty("username"), prop.getProperty("password"));
	click("crmlink_xpath");
	click("leadsTab_xpath");
	clickOnLead(data.get("LeadLastName"));
	click("convertLead_xpath");
	click("convertLeadandSave_xpath");
}   
@Test(priority=3,dataProvider="getDataDeleteLead")
public void deleteLeadAccountTest(Hashtable<String,String> data){
				

	test = rep.startTest("DeleteLeadAccountTest");
	test.log(LogStatus.INFO, data.toString());
	if(!DataUtil.isRunnable("DeleteLeadAccountTest", xls) ||  data.get("Runmode").equals("N")){
		test.log(LogStatus.SKIP, "Skipping the test as runmode is N");
		throw new SkipException("Skipping the test as runmode is N");
	}
	
	openBrowser(data.get("Browser"));
	navigate("appurl");
	dologin(prop.getProperty("username"), prop.getProperty("password"));
	click("crmlink_xpath");
	click("leadsTab_xpath");
	clickOnLead(data.get("LeadLastName"));
	waitForPageToLoad();
	click("deleteLead_xpath");
	acceptAlert();
	wait(3);
	click("backButtonLead_xpath");
	int rNum=getLeadRowNum(data.get("LeadLastName"));
	if(rNum!=-1)
		reportFailure("Could not delete the lead");
	
	reportPass("Lead deleted successfully");
	takeScreenShot();
	
	System.out.println("xx");
}
}