package com.sqac.test.ui.tests;

import com.sqac.test.ui.domain.User;
import com.sqac.test.ui.pages.AccountRegistrationPage;
import com.sqac.test.ui.pages.HomePage;
import com.sqac.test.ui.pages.LoginPage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Login Tests")
@Tag("chrome")
@Tag("firefox")
public class LoginTests extends BaseTest {

	@DisplayName("Verify user is able to register new account")
	@Test
	public void verifyUserIsAbleToRegisterNewAccount() {
		Random random = new Random();
		User user = new User();
		user.setFullName("randomName" + random.nextInt());
		user.setEmail("random" + random.nextInt() + "@gmail.com");
		user.setPassword("random" + random.nextInt());

		HomePage homePage = new HomePage();
		LoginPage loginPage = homePage.proceedToLogin();
		AccountRegistrationPage accountRegistrationPage = loginPage.openAccountRegistrationPage();
		accountRegistrationPage.registerUser(user);
		// Here should be assert. Not completing registration only because it's production website
	}

	@DisplayName("Verify user is not able to login with wrong credentials")
	@Test
	public void verifyUserNotAbleToLoginWithInvalidCredentials() {
		User invalidUser = new User();
		invalidUser.setEmail("invalid@gmail.com");
		invalidUser.setPassword("invalidPassword");
		String expErrorLoginBannerTxt = "Oops! Your email or password was incorrect. Please try again.";
		LoginPage loginPage = new HomePage().proceedToLogin().invalidLogin(invalidUser);
		assertThat(loginPage.getErrorLoginBannerTxt())
				.as("Error banner text is incorrect or user logged in with invalid credentials")
				.isEqualTo(expErrorLoginBannerTxt);
	}
}
