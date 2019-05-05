package com.sqac.test.ui.tests;

import com.codeborne.selenide.ElementsCollection;
import com.sqac.test.ui.pages.HomePage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Search Tests")
@Tag("chrome")
@Tag("firefox")
public class SearchTests extends BaseTest {

	@DisplayName("Verify search results displayed")
	@Test
	public void verifySearchResultsDisplayed() {
		final HomePage homePage = new HomePage();
		String query = "Purina";
		final ElementsCollection results = homePage.search(query).getSearchResults();
		assertThat(results.size()).as("Amount of results for search term '%s' is less then expected.", query)
				.isGreaterThan(20);
	}
}
