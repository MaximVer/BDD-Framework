package com.sqac.test.ui.pages;

import com.codeborne.selenide.ElementsCollection;
import io.qameta.allure.Step;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Selenide.$$;

public class SearchResultPage {

	private final String searchResults_xpath = ".results-products article";

	@Step("Getting search results")
	public ElementsCollection getSearchResults() {
		return $$(searchResults_xpath).shouldBe(sizeGreaterThan(1));
	}
}
