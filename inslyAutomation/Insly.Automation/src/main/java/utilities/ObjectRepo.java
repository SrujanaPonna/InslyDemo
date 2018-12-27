package utilities;

public class ObjectRepo 
{
	public static final String signupCaption_LABEL = "//h1[text()='Sign up and start using']";
	public static final String companyName_TEXTFIELD = "//table[@class='insly-form']/tbody/tr[@id='field_broker_name']/td[2]/div/input";
	public static final String companyDetailsSection = "//table[@class='insly-form']/tbody//tr//td[text()='Company']";
	public static final String countryField_COMBOBOX = "//select[@id='broker_address_country']";
	public static final String companyProfile_COMBOBOX = "//select[@id='prop_company_profile']";
	public static final String num_of_emp_COMBOBOX = "//select[@id='prop_company_no_employees']";
	public static final String desc_Yourself_COMBOBOX = "//select[@id='prop_company_person_description']";
	public static final String insly_address_TEXTFIELD = "//input[@id='broker_tag']";
	public static final String adminAccount_PANEL = "//table[@class='insly-form']/tbody//tr//td[text()='Administrator account details']";
	public static final String adminEmail_TEXTFIELD = "//input[@id='broker_admin_email']";
	public static final String adminAccMgrName_TEXTFIELD = "//input[@id='broker_admin_name']";
	public static final String adminPassword_TEXTFIELD = "//input[@id='broker_person_password']";
	public static final String adminPasswordRepeat_TEXTFIELD = "//input[@id='broker_person_password_repeat']";
	public static final String adminPhone_TEXTFIELD = "//input[@id='broker_admin_phone']";
	public static final String adminSuggest_LINK = "//a[text()='suggest a secure password']";
	public static final String adminSuggest_BUTTON = "//button[text()='OK']";
	public static final String termsConditions_PANEL = "//table[@class='insly-form']/tbody//tr//td[text()='Terms and conditions']";
	public static final String termsConditions_LINK = "//a[text()='terms and conditions']";
	public static final String modalDialog = "//div[@role='dialosg' and @class='ui-dialog ui-widget ui-widget-content ui-corner-all ui-draggable ui-dialog-buttons']/*[last()]";
	public static final String agree_BUTTON = "//button[text()='I agree']";
	public static final String privacyPolicy_LINK = "//a[text()='privacy policy']";
	public static final String privacy_CHECKBOX = "//label//input[@id='agree_privacypolicy']/following-sibling::span";
	public static final String personalData_CHECKBOX = "//label//input[@id='agree_data_processing']/following-sibling::span";
	public static final String signUP_BUTTON = "//button[@id='submit_save']";
	public static final String terms_CHECKBOX = "//label//input[@id='agree_termsandconditions']/following-sibling::span";
	public static final String afterSignup_PANEL = "//div//p[text()='Please take a minute to go through a few slides to see how Insly helps you win.']";
	public static final String homePage_BUTTON = "//div[@class='appcues-actions-right']/a[text()='Next']";
	public static final String sigingUP = "//h1[text()='Sign up and start using']";
	public static final String passwordSuggest = "//a[text()='suggest a secure password']";
	public static final String passwordDialogCaption = "//span[text()='Insly']";
	public static final String passwordDialog = "//div[@class='ui-dialog ui-widget ui-widget-content ui-corner-all ui-draggable ui-dialog-buttons']/div[@id='insly_alert']/b";
	public static final String passwordDialogBTN = "//button[text()='OK']";
	public static final String companyAddressTEXTFIELD = "//input[@id='broker_tag']";
}
