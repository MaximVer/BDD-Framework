package com.sqac.test.ui.pages;

import com.sqac.test.ui.domain.User;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class LoginPage {

	private String emailField_id = "#username";
	private String passwordField_xpath = "#password";
	private String signInButton_xpath = "//input[@value='Sign In']";
	private String errorLoginBanner_xpath = "//h5";
	private String createAccountBtn = ".js-create-account";

	@Step("Logging in with email: \"{user.email}\"")
	public AccountOverviewPage login(User user) {
		loginToAccount(user);
		return new AccountOverviewPage();
	}

	@Step("Logging in with invalid credentials, with email: \"{user.email}\"")
	public LoginPage invalidLogin(User user) {
		loginToAccount(user);
		return new LoginPage();
	}

	@Step("Getting error banner's text")
	public String getErrorLoginBannerTxt() {
		return $x(errorLoginBanner_xpath).getText();
	}

	@Step("Opening Account Registration Page")
	public AccountRegistrationPage openAccountRegistrationPage() {
		$(createAccountBtn).click();
		return new AccountRegistrationPage();
	}

	private void loginToAccount(User user) {
		$(emailField_id).sendKeys(user.getEmail());
		$(passwordField_xpath).sendKeys(user.getPassword());
		$x(signInButton_xpath).click();
	}
}
