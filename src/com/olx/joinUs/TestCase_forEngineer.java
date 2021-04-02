package com.olx.joinUs;
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

public class TestCase_forEngineer extends TestSuiteBase{
	
	
	String runmodes[]=null;
	static int count=-1;

	
	// Runmode of test case in a suite
	@BeforeTest
	public void checkTestSkip(){
		
		if(!TestUtil.isTestCaseRunnable(suiteJoinUsxls,this.getClass().getSimpleName())){
			Log.info("Skipping Test Case"+this.getClass().getSimpleName()+" as runmode set to NO");//logs
			throw new SkipException("Skipping Test Case"+this.getClass().getSimpleName()+" as runmode set to NO");//reports
		}
		// load the runmodes off the tests
		runmodes=TestUtil.getDataSetRunmodes(suiteJoinUsxls, this.getClass().getSimpleName());
	}
	
	@Test(dataProvider="getTestData")
	public void nonAutomatedBuyFlow(
			            String browserType,
						String Url
						
							) throws Exception{
		// test the runmode of current dataset
		count++;
		if(!runmodes[count].equalsIgnoreCase("Y")){
			skip=true;
			throw new SkipException("Runmode for test set data set to no "+count);
		}
		
		document = new XWPFDocument();
		fos = new FileOutputStream(new File(System.getProperty("user.dir")+"\\Documented Screenshots\\ComNonAutomated_"+browserType+".docx"));
	
		   Log.info("================================ Executing TestCase_forEngineer for Test Data: "+browserType+"======================================================");
		   Log.info("==================================================================================================================================================");
		
		  
		
//======================================WebDriver Code ================================================================

		
	    openBrowser(browserType);
		navigate(Url);
	
		//closeBrowser();
			
//===============================================================================================================================================		
	
	}
	
	@AfterMethod
	public void reportDataSetResult() throws IOException{

		if(skip)
			TestUtil.reportDataSetResult(suiteJoinUsxls, this.getClass().getSimpleName(), count+2, "SKIP");
		else if(fail){
			isTestPass=false;
			
			TestUtil.reportDataSetResult(suiteJoinUsxls, this.getClass().getSimpleName(), count+2, "FAIL");
		}
		else
			TestUtil.reportDataSetResult(suiteJoinUsxls, this.getClass().getSimpleName(), count+2, "PASS");
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
			TestUtil.reportDataSetResult(suiteJoinUsxls, "Test Cases", TestUtil.getRowNum(suiteJoinUsxls,this.getClass().getSimpleName()), "PASS");
		else
			TestUtil.reportDataSetResult(suiteJoinUsxls, "Test Cases", TestUtil.getRowNum(suiteJoinUsxls,this.getClass().getSimpleName()), "FAIL");
	}
	@DataProvider
	public Object[][] getTestData(){
		return TestUtil.getData(suiteJoinUsxls, this.getClass().getSimpleName()) ;
	}
	
	
	
}
