package com.sqac.test.ui.pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.page;

public class HomePage {

	private final String logo_className = ".cw-logo-alt";
	private final String queryInput_xpath = "#header-searchform input";
	private final String accountTab_className = "a .top-nav-account";

	@Step("Getting logo text")
	public String getLogoText() {
		final SelenideElement logoElement = $(logo_className);
		return logoElement.getText();
	}

	@Step("Searching for: \"{query}\"")
	public SearchResultPage search(String query) {
		$(queryInput_xpath).setValue(query).pressEnter();
		return page(SearchResultPage.class);
	}

	@Step("Opening Login page")
	public LoginPage proceedToLogin() {
		$(accountTab_className).click();
		return new LoginPage();
	}

}
