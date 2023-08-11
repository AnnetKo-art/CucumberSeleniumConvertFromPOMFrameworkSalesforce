package com.automation.steps;

import java.util.Properties;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;

import com.automation.pages.contacts.ContactsPage;
import com.automation.pages.home.HomePage;
import com.automation.pages.login.LoginPage;
import com.automation.pages.userprofile.UserProfilePage;
import com.automation.utility.Log4JUtility;
import com.automation.utility.PropertiesUtility;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.BeforeAll;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;

public class SalesforceStepDef {
	
	protected static Logger log;
	public  static WebDriver driver;
	protected static Log4JUtility logObject=Log4JUtility.getInstance();
	LoginPage login;
	HomePage home;
	ContactsPage contacts;
	UserProfilePage userprofile;
	
	public  void launchBrowser(String browserName)  {
		switch(browserName) {
		case "firefox":
			WebDriverManager.firefoxdriver().browserVersion("109.0.1").setup();
			driver = new FirefoxDriver();
			driver.manage().window().maximize();
			
			
			break;
		case "chrome":
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			driver.manage().window().maximize();
			
			break;
		}
		System.out.println(browserName+" browser opened");
	}
	
	public  void goToUrl(String url) {
		driver.get(url);
		log.info(url+ "is entered");
	}

	public  void closeBrowser() {
		driver.close();
		log.info("current browser closed");
	}
	
	@BeforeAll
	public static void setUpBeforeAllScenarios() {
		log=logObject.getLogger();
	}
	@Before
	public void setUpEachScenario()  {
		
		launchBrowser("chrome");
		
	}
	@After
	public void tearDown() {
		closeBrowser();
	}
	//--------------------------TEST 1 -------------------------
	//Launch salesforce,check PageTitle, enter username,clear password field, enter password, click LoginButton, check title of Homepage
	
	@Given("User open salesforce application")
	public void user_open_salesforce_application() {
		PropertiesUtility pro=new PropertiesUtility();
		Properties appProp= pro.loadFile("applicationDataProperties");
		String url= appProp.getProperty("url");
		goToUrl(url);
		System.out.println("driver in baseTest="+driver);
	   // throw new io.cucumber.java.PendingException();
	}

	
	//we take cre about a driver creating different objects
	@When("user on {string}")
	public void user_on(String page) {
		 if(page.equalsIgnoreCase("loginpage"))
		    	login=new LoginPage(driver);
		    else if(page.equalsIgnoreCase("homepage"))
		    	home=new HomePage(driver);
	    //throw new io.cucumber.java.PendingException();
	}

	@When("expected title of loginpage we can see {string}")
	public void expected_title_of_loginpage_we_can_see(String  expectedText)  {		
		System.out.println("Expected_title_of_loginpage check");
		String actualText= login.getTitleOfThePAge();
		Assert.assertEquals(actualText, expectedText);
		System.out.println("Expected_title_of_loginpage check");
		
	}

	@When("User enters value into text box username as {string}")
	public void user_enters_value_into_text_box_username_as(String username) throws InterruptedException {
		Thread.sleep(3000);
		System.out.println("Entering username");
		//login.enterUsername(string);
		login.enterUsername(username);
	}
	

	@When("User enters value into text box password as {string}")
	public void user_enters_value_into_text_box_password_as(String password) throws InterruptedException {
		Thread.sleep(3000);
		login.enterPassword(password);
		
	}

	@When("Click on Login button")
	public void click_on_login_button() {	
		driver= login.clickButton();
	}

	
	@Then("expected title of homepage we can see  {string}")
	public void expected_title_of_homepage_we_can_see(String expectedText) {
		String actualText= home.getTitleOfThePAge();
		Assert.assertEquals(actualText, expectedText);
	}


//------------------------- TEST 2,3, 4--------CONTACTS PAGE ----------------------------------
//Test2 Login to salesforce, click Contacts Tub,  Click on Create New View hyperlink ,
	    // Enter the View Unique Name(Unique Name : EFGH), Click on Save	
//Test3 Login to salesforce, click Contacts Tub,  Click on Create New View hyperlink ,
	    // Enter the View Unique Name(Unique Name : EFGH),Name ABCD,  Click Cancel	
//Test4 - Login to salesforce, click Contacts Tub, Select "Recently Modified", refresh the page and check if Recently Modified option is displayed.
	



@Given("user navigates to the loginpage")
public void user_navigates_to_the_loginpage() {
	PropertiesUtility pro=new PropertiesUtility();
	Properties appProp= pro.loadFile("applicationDataProperties");
	String url= appProp.getProperty("url");
	goToUrl(url);
	System.out.println("driver in baseTest="+driver);
}

@When("user is on {string}")
public void user_is_on(String page) {
	 if(page.equalsIgnoreCase("loginpage"))
	    	login=new LoginPage(driver);
	    else if(page.equalsIgnoreCase("homepage"))
	    	home=new HomePage(driver);   
}

@When("user submits username and password as {string},{string}")
public void user_submits_username_and_password_as(String username, String password) {
	System.out.println("Entering username");
	login.enterUsername(username);
	System.out.println("Entering password");
	login.enterPassword(password);
}

@When("clicks on Login button")
public void clicks_on_login_button() {
	driver= login.clickButton();
}

@When("expected title of homepage user can see  {string}")
public void expected_title_of_homepage_user_can_see(String expectedText) {
	String actualText= home.getTitleOfThePAge();
	Assert.assertEquals(actualText, expectedText);
}

@When("user clicks on contacts tab link")
public void user_clicks_on_contacts_tab_link()  {	
	home.clickLink();	
}


@When("popup window handle")
public void popup_window_handle() throws InterruptedException {
	contacts = new ContactsPage(driver); // Initialize the ContactsPage object
	//driver=contacts.popUpWindowHandleWithDriver();
	driver=contacts.popUpWindowHandleWithDriver();
	
}
//Test2 ---------
@Given("new view hyperlink is displayed on the contacts page")
public void new_view_hyperlink_is_displayed_on_the_contacts_page() throws InterruptedException {
	
	contacts.elementCreateNewViewLinkIsDisplayed();
	
}

@When("user clicks on create new view hyperlink")
public void user_clicks_on_create_new_view_hyperlink()  {
	
	contacts.clickCreateNewViewLink();
	
}

@When("puts the data into view unique name as {string}")
public void puts_the_data_into_view_unique_name_as(String uniqeName) {
	contacts.viewNameInsertData(uniqeName);
}

@Then("clicks save button")
public void clicks_save_button() {
	contacts.scrollDownCreateNewView();
	contacts.clickSaveButtonFromCreateNewView();	
}

//Test3---------

@When("click cancel button")
public void click_cancel_button() {
	contacts.scrollDownCreateNewView();
	contacts.clickCancelButtonFromCreateNewView();
}

@Then("view drop down check unique name as {string} should be absent")
public void view_drop_down_check_unique_name_as_should_be_absent(String insertedUniqueName) {
	contacts.dropDownHandleContactsPage(insertedUniqueName);
}


//Test4 --------


@Given("popup recently modified is present on the page")
public void popup_recently_modified_is_present_on_the_page() {
	contacts.dropDownIsDisplayed();
}

@When("user chooses recently created option in the popup")
public void user_chooses_recently_created_option_in_the_popup() {
    contacts.selectDropDownRecentlyCreated();
}

@When("refreshes the page")
public void refreshes_the_page() {
    contacts.pageRefresh();
}

@Then("recently created option as {string} in the popup should be displayed")
public void recently_created_option_as_in_the_popup_should_be_displayed(String expectedOption) {
   String actualOption= contacts.checkVisibleOptionInDropDown();
   Assert.assertEquals(actualOption, expectedOption);
}

//Test5 ------UserProfilePage ----- 
//Login to salesforce, click UserMenu, Select "My Profile" and click,click Edit, add phone number, click SaveAll button
@Given("expected title of home page is  {string}")
public void expected_title_of_home_page_is(String expectedText) {
	String actualText= home.getTitleOfThePAge();
	Assert.assertEquals(actualText, expectedText);
}

@When("click on drop down menu from user lable")
public void click_on_drop_down_menu_from_user_lable() {
	home.dropDownClick();
}

@When("click my profile link in a dropdown")
public void click_my_profile_link_in_a_dropdown() {
	home.selectMyProfileInUserNavDropDown();
}

@When("click edit profile link")
public void click_edit_profile_link() {
	userprofile=new UserProfilePage(driver);
	userprofile.clickLink();
	
}

@When("handle the frame")
public void handle_the_frame() {
	userprofile.frameHandling();
}

@When("check if a textbook for work phone is displayed")
public void check_if_a_textbook_for_work_phone_is_displayed() {
	userprofile.elementIsDisplayed();
}

@When("insert data as {string} into work phone textbox")
public void insert_data_as_into_work_phone_textbox(String phone) {
	userprofile.dataInputPhone(phone);
}

@Then("click saveall button")
public void click_saveall_button() {
	userprofile.clickButton(); 
}

}

