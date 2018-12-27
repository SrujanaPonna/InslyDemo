package utilities;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.NoSuchElementException;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang.RandomStringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class GenericActions
{
	public static WebDriver driver;
	public String browserName;
	public static String driverPath;
	public static String companyNameValue;
	public static String adminAccountEmail;
	public static String adminAccountManagerName;
	public static ExtentReports extent;
	public static ExtentTest logger;
	public static String reportPath;
	public static String currDateTime;
	public static String screenshotPath;
	
	
/*########################################################################################################################################################
 * 	Function Name : initializeReport
 * Function Desc : It does the prelims for reporting by framing the path and initializing extent object
 * Created By : SRUJANA
 *########################################################################################################################################################
 */	
	public void initializeReport()
	{
		String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
		String path = System.getProperty("user.dir")+"/Reports/"+timeStamp+".html";
		extent = new ExtentReports(path);
		logger = extent.startTest("Insly Automation");
	}
	
	
/*########################################################################################################################################################
 * 	Function Name : initializeScreenshot
 * Function Desc : It does the prelims for screenshot by framing the path and creating folder
 * Created By : SRUJANA
 *########################################################################################################################################################
 */	
	public void initializeScreenshot() 
	{
		String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
		screenshotPath = System.getProperty("user.dir")+"/Screenshots/"+timeStamp;
		new File(screenshotPath).mkdir();
	}
	
	
/*########################################################################################################################################################
 * 	Function Name : randomString
 * Function Desc : It generates a random string of length 10
 * Created By : SRUJANA
 *########################################################################################################################################################
 */
	public String randomString()
	{
	    String generatedString = RandomStringUtils.randomAlphabetic(10);
	    return generatedString.toLowerCase();
	}
	
	
/*########################################################################################################################################################
 * 	Function Name : randomInteger
 * Function Desc : It generates a random Integer
 * Created By : SRUJANA
 *########################################################################################################################################################
 */	
	private static int randomInteger() 
	{
		return ThreadLocalRandom.current().nextInt();
	}
	
	
/*########################################################################################################################################################
 * 	Function Name : reporting
 * Function Desc : It generates steps in extent reports
 * Created By : SRUJANA
 *########################################################################################################################################################
 */	
	public void reporting(LogStatus status, String desc)
	{
		logger.log(status, desc);
		extent.flush();
	}
	
	
/*########################################################################################################################################################
 * 	Function Name : closeBrowser
 * Function Desc : It closes all the opened Browsers
 * Created By : SRUJANA
 *########################################################################################################################################################
 */	
	public void closeBrowser()
	{
	   try
	   {
		   driver.quit();
		   reporting(LogStatus.PASS, "Browser closed");
	   }
	   catch(Exception ex)
	   {
		   reporting(LogStatus.FAIL, "Browser not closed");
		   ex.printStackTrace();
	   }
		
	}
	
	
/*########################################################################################################################################################
 * 	Function Name : captureScreenshot
 * Function Desc : It captures a image of current page and assigns it a name passed in the parameter
 * Created By : SRUJANA
 *########################################################################################################################################################
 */	
	public void captureScreenshot(String str) throws IOException
	{
		try
		{
			String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
			File src = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			//String path = System.getProperty("user.dir")+"/Screenshots/"+str+"_"+timeStamp+".png";
			String path = screenshotPath+"/"+str+"_"+timeStamp+".png";
			org.apache.commons.io.FileUtils.copyFile(src, new File(path));
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
	}
	
	
/*########################################################################################################################################################
 * 	Function Name : invokeBrowser
 * Function Desc : It opens the browser based on input
 * Created By : SRUJANA
 *########################################################################################################################################################
 */	
	public void invokeBrowser()
	{
		if(System.getProperty("os.name").toLowerCase().startsWith("mac"))
		{
			if(InputData.browser.equalsIgnoreCase("chrome"))
			{
				driverPath = System.getProperty("user.dir")+"/Drivers/chromedriver";
				System.setProperty("webdriver.chrome.driver", driverPath);
				driver = new ChromeDriver();
			}
			else if(InputData.browser.equalsIgnoreCase("firefox"))
			{
				driverPath = System.getProperty("user.dir")+"/Drivers/geckodriver";
				System.setProperty("webdriver.gecko.driver", driverPath);
				driver = new FirefoxDriver();
			}
		}
		else
		{
			if(InputData.browser.equalsIgnoreCase("chrome"))
			{
				driverPath = System.getProperty("user.dir")+"/Drivers/chromedriver.exe";
				System.setProperty("webdriver.chrome.driver", driverPath);
				driver = new ChromeDriver();
			}
			else if(InputData.browser.equalsIgnoreCase("firefox"))
			{
				driverPath = System.getProperty("user.dir")+"/Drivers/geckodriver.exe";
				System.setProperty("webdriver.gecko.driver", driverPath);
				driver = new FirefoxDriver();
			}
		}
		  driver.manage().window().maximize();
	}
	
	
/*########################################################################################################################################################
 * 	Function Name : openApplication
 * Function Desc : It opens the application page based on the URL
 * Created By : SRUJANA
 *########################################################################################################################################################
 */	
	public void openApplication()
	{
		  driver.get(InputData.url);
		  reporting(LogStatus.PASS,"Signup page launched successfully");
	}
	
	
/*########################################################################################################################################################
 * 	Function Name : isApplicationInvoked
 * Function Desc : It checks whether application opened successfully
 * Created By : SRUJANA
 *########################################################################################################################################################
 */		
	public void isApplicationInvoked() throws IOException
	{
			if(driver.findElement(By.xpath(ObjectRepo.signupCaption_LABEL)).isDisplayed())
			{
				reporting(LogStatus.PASS,"Signup page opened successfully");
			}
			else
			{
				reporting(LogStatus.FAIL,"Signup page not opened successfully");
			}
			captureScreenshot("Initial Signup page");
	}
	
	
/*########################################################################################################################################################
 * 	Function Name : checkCompanyDetailsExistance
 * Function Desc : It checks the existance of CompanyDetails
 * Created By : SRUJANA
 *########################################################################################################################################################
 */	
	public void checkCompanyDetailsExistance() throws IOException
	{
			if(driver.findElement(By.xpath(ObjectRepo.companyDetailsSection)).isDisplayed())
			{
				reporting(LogStatus.PASS,"Company Details Section Exists");
			}
			else
			{
				reporting(LogStatus.FAIL,"Company Details Section does not Exists");
			}
			captureScreenshot("Existance of company Details Section");
	}
	
	
/*########################################################################################################################################################
 * 	Function Name : fillInCompanyDetails
 * Function Desc : It enters the details into company details based on input
 * Created By : SRUJANA
 *########################################################################################################################################################
 */		
	public void fillInCompanyDetails() throws IOException
	{
		try
		{
				companyNameValue = randomString();
				driver.findElement(By.xpath(ObjectRepo.companyName_TEXTFIELD)).click();
				driver.findElement(By.xpath(ObjectRepo.companyName_TEXTFIELD)).sendKeys(companyNameValue);
				
				Select country = new Select(driver.findElement(By.xpath(ObjectRepo.countryField_COMBOBOX)));
				country.selectByVisibleText(InputData.country.trim());
				
				driver.findElement(By.xpath(ObjectRepo.insly_address_TEXTFIELD)).click();
				
				Select company = new Select(driver.findElement(By.xpath(ObjectRepo.companyProfile_COMBOBOX)));
				company.selectByVisibleText(InputData.companyProfile.trim());
				
				Select numOfEmp = new Select(driver.findElement(By.xpath(ObjectRepo.num_of_emp_COMBOBOX)));
				numOfEmp.selectByVisibleText(InputData.numOfEmployees.trim());
				
				Select descYourself = new Select(driver.findElement(By.xpath(ObjectRepo.desc_Yourself_COMBOBOX)));
				descYourself.selectByVisibleText(InputData.yourself.trim());		
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
	}
	
	
/*########################################################################################################################################################
 * 	Function Name : checkAddressFilledIn
 * Function Desc : It checks whether the address field is filled automatically after entering details in various company fields
 * Created By : SRUJANA
 *########################################################################################################################################################
 */
	public void checkAddressFilledIn() throws InterruptedException, IOException
	{
		Thread.sleep(2000);
	    String companyValue = driver.findElement(By.xpath(ObjectRepo.companyName_TEXTFIELD)).getAttribute("value");
	    String addressValue = driver.findElement(By.xpath(ObjectRepo.insly_address_TEXTFIELD)).getAttribute("value");
	    	    
	    if(companyValue.equalsIgnoreCase(addressValue))
	    {
	    	reporting(LogStatus.PASS,"After entering all details in company detail section, ADDRESS IS PREFILLED with CompanyName");
	    }
	    else
	    {
	    	reporting(LogStatus.FAIL,"After entering all details in company detail section, ADDRESS IS NOT PREFILLED with CompanyName");
	    }
	    captureScreenshot("CompanyDetailsSection");
	}
	
	
/*########################################################################################################################################################
 * 	Function Name : isAdministratorAccountSectionExist
 * Function Desc : It checks the existance of AdminAccountSection
 * Created By : SRUJANA
 *########################################################################################################################################################
 */	
	public void isAdministratorAccountSectionExist() throws IOException
	{
		WebElement adminAccount = driver.findElement(By.xpath(ObjectRepo.adminAccount_PANEL));
		if(adminAccount.isDisplayed())
		{
			reporting(LogStatus.PASS,"Administrator Account Section Exists");
		}
		else
		{
			reporting(LogStatus.FAIL,"Administrator Account Section does not Exists");
		}
		captureScreenshot("Existance of Administrator Account Section");
	}

	
/*########################################################################################################################################################
 * 	Function Name : fillInAdminAccountDetails
 * Function Desc : It enters values into AdminAccountDetails section
 * Created By : SRUJANA
 *########################################################################################################################################################
 */		
	public void fillInAdminAccountDetails() throws InterruptedException
	{
		
		try
		{
				adminAccountEmail = randomString()+"@gmail.com";
				driver.findElement(By.xpath(ObjectRepo.adminEmail_TEXTFIELD)).click();
				driver.findElement(By.xpath(ObjectRepo.adminEmail_TEXTFIELD)).sendKeys(adminAccountEmail);
				
				adminAccountManagerName = randomString()+" "+randomString();
				driver.findElement(By.xpath(ObjectRepo.adminAccMgrName_TEXTFIELD)).click();
				driver.findElement(By.xpath(ObjectRepo.adminAccMgrName_TEXTFIELD)).sendKeys(adminAccountManagerName);
				
				driver.findElement(By.xpath(ObjectRepo.passwordSuggest)).click();
				Thread.sleep(1000);
				
				String password = driver.findElement(By.xpath(ObjectRepo.passwordDialog)).getAttribute("value");
				System.out.println(password);
				reporting(LogStatus.PASS,"Password is :- "+password);
				
				driver.findElement(By.xpath(ObjectRepo.adminSuggest_BUTTON)).click();
				Thread.sleep(1000);
			
				int phoneNum = randomInteger();
				
				Thread.sleep(2000);
				driver.findElement(By.xpath(ObjectRepo.adminPhone_TEXTFIELD)).click();
				Thread.sleep(2000);
				driver.findElement(By.xpath(ObjectRepo.adminPhone_TEXTFIELD)).sendKeys(Integer.toString(phoneNum));
				captureScreenshot("AdminAccountDetails");
		}
		catch(Exception ex)
		{
			reporting(LogStatus.FAIL,"Error occured while entering details in AdminAccountDetails");
			ex.printStackTrace();
		}
	}
	

/*########################################################################################################################################################
 * 	Function Name : isDataFilledInAdminAccountDetails
 * Function Desc : It check whether data entered into AdminAccount Section
 * Created By : SRUJANA
 *########################################################################################################################################################
 */	
	public void isDataFilledInAdminAccountDetails()
	{
		if(driver.findElement(By.xpath(ObjectRepo.adminEmail_TEXTFIELD)).getAttribute("value").equalsIgnoreCase(adminAccountEmail))
		{
			reporting(LogStatus.PASS,"Details entered in AdminAccountDetails");
		}
		else
		{
			reporting(LogStatus.FAIL,"Details not entered in AdminAccountDetails");
		}
	}
	
	
/*########################################################################################################################################################
 * 	Function Name : isTermsConditionsSectionExist
 * Function Desc : It checks the existance of Terms&Conditions section
 * Created By : SRUJANA
 *########################################################################################################################################################
 */	
	public void isTermsConditionsSectionExist() throws IOException
	{
		WebElement termsCond = driver.findElement(By.xpath(ObjectRepo.termsConditions_PANEL));
		if(termsCond.isDisplayed())
		{
			reporting(LogStatus.PASS,"Terms&Conditions Section Exists");
			captureScreenshot("Existance of Terms&Conditions Section");
		}
		else
		{
			reporting(LogStatus.FAIL,"Terms&Conditions Section does not Exists");
			captureScreenshot("Existance of Terms&Conditions Section");
		}
	}
	
	
/*########################################################################################################################################################
 * 	Function Name : scrollDown
 * Function Desc : It helps in scrolling down
 * Created By : SRUJANA
 *########################################################################################################################################################
 */
	public void scrollDown() throws IOException
	{
		 WebDriverWait wait = new WebDriverWait(driver, 1);
	      wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("document-content")));
		
	     WebElement element = driver.findElement(By.xpath("//div[@id='document-content']/*[last()]")); 
		 ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
		 captureScreenshot("After scrolling down in privacy policy");
	}
	
	
/*########################################################################################################################################################
 * 	Function Name : terms_and_Conditions
 * Function Desc : It enables the checkbox of terms&Conditions after reading policies
 * Created By : SRUJANA
 *########################################################################################################################################################
 */	
	public void terms_and_Conditions() 
	{
		try
		{
				WebElement privacy = driver.findElement(By.xpath(ObjectRepo.privacyPolicy_LINK));
				privacy.click();
				captureScreenshot("Privacy Policy");
				
				Thread.sleep(2000);
				scrollDown();
				Thread.sleep(2000);
				
				Actions action = new Actions(driver);
				action.sendKeys(Keys.ESCAPE).perform();
				
				WebElement privacyCheck = driver.findElement(By.xpath(ObjectRepo.privacy_CHECKBOX));
				privacyCheck.click();
				
				Thread.sleep(3000);
				
				WebElement personalDataCheck = driver.findElement(By.xpath(ObjectRepo.personalData_CHECKBOX));
				personalDataCheck.click();
				Thread.sleep(3000);
				
				driver.findElement(By.xpath(ObjectRepo.terms_CHECKBOX)).click();
				captureScreenshot("After entering details in TermsANDConditions");
		}
		catch(Exception ex)
		{
				ex.printStackTrace();
		}
		
	}
	
	
/*########################################################################################################################################################
 * 	Function Name : isSignupEnabled
 * Function Desc : It checks whether signup button is enabled after accepting terms and conditions
 * Created By : SRUJANA
 *########################################################################################################################################################
 */	
	public void isSignupEnabled() throws Exception
	{
		if(driver.findElement(By.xpath(ObjectRepo.signUP_BUTTON)).isEnabled())
		{
			reporting(LogStatus.PASS, "SignUP Button Active");
		}
		else
		{
			reporting(LogStatus.PASS, "SignUP Button not Active");
		}
		captureScreenshot("Sign UP Button Activeness");
	}
	

/*########################################################################################################################################################
 * 	Function Name : signup
 * Function Desc : It clicks on sign up button
 * Created By : SRUJANA
 *########################################################################################################################################################
 */	
	public void signup() 
	{
		try
		{
			driver.findElement(By.xpath(ObjectRepo.signUP_BUTTON)).click();
			reporting(LogStatus.PASS,"Details entered and clicked on Signup Button");
			Thread.sleep(1000);
			captureScreenshot("Post Clicking Signup Button");
		}
		catch(Exception ex)
		{
			reporting(LogStatus.FAIL,"Error occured while clicking on Signup Button");
			ex.printStackTrace();
		}
	}
	
	
/*########################################################################################################################################################
 * 	Function Name : validateSignup
 * Function Desc : It validates whether signup is successful
 * Created By : SRUJANA
 *########################################################################################################################################################
 */	
	public void validateSignup() throws Exception
	{
		
		Thread.sleep(30000);
        String expectedURL = "https://"+companyNameValue+".insly.com/dashboard";
        String actualURL = driver.getCurrentUrl();
        System.out.println(expectedURL);
        System.out.println(actualURL);
        if(expectedURL.equalsIgnoreCase(actualURL))
        {
        	reporting(LogStatus.PASS, "Signup successful and URL is as expected :- "+actualURL);
        }
        else
        {
        	reporting(LogStatus.FAIL, "Sign up not successful and URL is not as expected :- "+actualURL);
        }
		captureScreenshot("In Dashboard Page");
	}
	
}
