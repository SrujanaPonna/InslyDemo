package stepDefinitions;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import utilities.GenericActions;

public class SignupSteps 
{

GenericActions obj = new GenericActions();
	
	@Given("^Browser is opened$")
	public void Browser_is_opened()
	{
		obj.initializeReport();
		obj.initializeScreenshot();
		obj.invokeBrowser();
	}
	
	@When("^User navigated to signup page$")
	public void User_navigated_to_signup_page()
	{
		obj.openApplication();
	}
	
	@Then("^Sign up and Start Using title should be shown$")
	public void Sign_up_and_Start_Using_title_should_be_shown() throws Exception
	{
		obj.isApplicationInvoked();
	}
	
	@Given("^Company Details secion is available$")
	public void Company_Details_section_is_available() throws Exception
	{
		obj.checkCompanyDetailsExistance();
	}
	
	@When("^User fills in the details in company section$")
	public void User_fills_in_the_details_in_company_section() throws Exception
	{
		obj.fillInCompanyDetails();
	}
	
	@And("^insly address should get filled in automatically and other details should get filled properly as per input$")
	public void insly_address_should_get_filled_in_automatically_and_other_details_should_get_filled_properly_as_per_input() throws Exception
	{
		obj.checkAddressFilledIn();
	}
	
	@Given("^Administrator Account Details Section is available$")
	public void Administrator_Account_Details_Section_is_available() throws Exception
	{
		obj.isAdministratorAccountSectionExist();
	}
	
	@When("^User fills in all the data in administrator section$")
	public void User_fills_in_all_the_data_in_administrator_section() throws InterruptedException
	{
		obj.fillInAdminAccountDetails();
	}
	
	@Then("^All the data should be filled in properly$")
	public void All_the_data_should_be_filled_in_properly()
	{
		obj.isDataFilledInAdminAccountDetails();
	}
	
	@Given("^Terms and Conditions Section is available$")
	public void Terms_and_Conditions_Section_is_available() throws Exception
	{
		obj.isTermsConditionsSectionExist();
	}
	
	@When("^we click on terms & conditions and privacy policy as per expectation after scrolling down$")
	public void we_click_on_terms_conditions_and_privacy_policy_as_per_expectation_after_scrolling_down() throws InterruptedException
	{
		obj.terms_and_Conditions();
	}
	
	@Then("^Signup Button should get enabled$")
	public void Signup_Button_should_get_enabled() throws Exception
	{
		obj.isSignupEnabled();
	}
	
	@When("^you click on sign up button$")
	public void you_click_on_sign_up_button()
	{
		obj.signup();
	}
	
	@Then("^Instance creation should be created and it should start to work as expected$")
	public void Instance_creation_should_be_created_and_it_should_start_to_work_as_expected() throws Exception
	{
		obj.validateSignup();
	}
	
	@And("^close the Browser$")
	public void close_the_Browser()
	{
		obj.closeBrowser();
	}
	
}
