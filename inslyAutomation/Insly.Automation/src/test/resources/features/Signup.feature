Feature: Signup

Scenario: Validate the signup page

Given Browser is opened
When User navigated to signup page
Then Sign up and Start Using title should be shown

Scenario: Complete the Company Details in Signup page

Given Company Details secion is available
When User fills in the details in company section
Then insly address should get filled in automatically and other details should get filled properly as per input

Scenario: Administrator Account Details

Given Administrator Account Details Section is available
When User fills in all the data in administrator section
Then All the data should be filled in properly

Scenario: Terms and Conditions

Given Terms and Conditions Section is available
When we click on terms & conditions and privacy policy as per expectation after scrolling down
Then Signup Button should get enabled

Scenario: Signing up

When you click on sign up button
Then Instance creation should be created and it should start to work as expected
And close the Browser 