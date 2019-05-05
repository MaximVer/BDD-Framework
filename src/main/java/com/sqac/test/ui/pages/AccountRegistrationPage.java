package com.sqac.test.ui.pages;

import com.sqac.test.ui.domain.User;

import static com.codeborne.selenide.Selenide.$;

public class AccountRegistrationPage {
	private String fullNameField = "#fullName";
	private String emailField = "#email";
	private String passwordField = "#password";
	private String passwordVerifyField = "#passwordVerify";
	private String createAccountBtn = ".js-create-account";

	public void registerUser(User user) {
		$(fullNameField).setValue(user.getFullName());
		$(emailField).setValue(user.getEmail());
		$(passwordField).setValue(user.getPassword());
		$(passwordVerifyField).setValue(user.getPassword());
		$(createAccountBtn).click();
	}
}
