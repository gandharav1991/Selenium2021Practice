package com.olx.submitAdd;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;
import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.olx.util.TestUtil;

public class TestCase_carAdd extends TestSuiteBase{
	
	/*XWPFDocument document=null;
	FileOutputStream fos= null;*/
	
	String runmodes[]=null;
	static int count=-1;

	
	// Runmode of test case in a suite
	@BeforeTest
	public void checkTestSkip(){
		
		if(!TestUtil.isTestCaseRunnable(suiteSubmitAddxls,this.getClass().getSimpleName())){
			Log.info("Skipping Test Case"+this.getClass().getSimpleName()+" as runmode set to NO");//logs
			throw new SkipException("Skipping Test Case"+this.getClass().getSimpleName()+" as runmode set to NO");//reports
		}
		// load the runmodes off the tests
		runmodes=TestUtil.getDataSetRunmodes(suiteSubmitAddxls, this.getClass().getSimpleName());
	}
	
	@Test(dataProvider="getTestData")
	public void nonAutomatedBuyFlow(
			            String browserType,
						String AdTitle,
						String AdDescription,
						String Name,
						String Phone,
						String Url,
						String Enteracity
							) throws Exception{
		// test the runmode of current dataset
		count++;
		if(!runmodes[count].equalsIgnoreCase("Y")){
			skip=true;
			throw new SkipException("Runmode for test set data set to no "+count);
		}
		
		document = new XWPFDocument();
		fos = new FileOutputStream(new File(System.getProperty("user.dir")+"\\Documented Screenshots\\ComNonAutomated_"+browserType+".docx"));
	
		   Log.info("================================ Executing TestCase_carAdd for Test Data: "+browserType+"======================================================");
		   Log.info("==================================================================================================================================================");
		
		  
		
//======================================WebDriver Code ================================================================

		
	    openBrowser(browserType);
		navigate(Url);
		click("btn_SubmitAFreeAdd_xpath");
		fluentWait("txtbx_AdTitle_xpath", 60, 2);
		input("txtbx_AdTitle_xpath",AdTitle);
		click("lnk_Category_id");
		
		
		closeBrowser();
			
//===============================================================================================================================================		
	
	}
	
	@AfterMethod
	public void reportDataSetResult() throws IOException{

		if(skip)
			TestUtil.reportDataSetResult(suiteSubmitAddxls, this.getClass().getSimpleName(), count+2, "SKIP");
		else if(fail){
			isTestPass=false;
			
			TestUtil.reportDataSetResult(suiteSubmitAddxls, this.getClass().getSimpleName(), count+2, "FAIL");
		}
		else
			TestUtil.reportDataSetResult(suiteSubmitAddxls, this.getClass().getSimpleName(), count+2, "PASS");
		if(!skip)
		{
		document.write(fos);
	    fos.flush();
		fos.close();
		}
		skip=false;
		fail=false;	
	}
	@AfterTest
	public void reportTestResult(){
		if(isTestPass)
			TestUtil.reportDataSetResult(suiteSubmitAddxls, "Test Cases", TestUtil.getRowNum(suiteSubmitAddxls,this.getClass().getSimpleName()), "PASS");
		else
			TestUtil.reportDataSetResult(suiteSubmitAddxls, "Test Cases", TestUtil.getRowNum(suiteSubmitAddxls,this.getClass().getSimpleName()), "FAIL");
	}
	@DataProvider
	public Object[][] getTestData(){
		return TestUtil.getData(suiteSubmitAddxls, this.getClass().getSimpleName()) ;
	}
	
	
	
}
