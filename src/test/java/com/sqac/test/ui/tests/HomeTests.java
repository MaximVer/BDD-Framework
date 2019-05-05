package com.sqac.test.ui.tests;

import com.sqac.test.ui.pages.HomePage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Home Tests")
@Tag("chrome")
@Tag("firefox")
public class HomeTests extends BaseTest {

	@DisplayName("Verify logo text displayed correctly.")
	@Test
	public void verifyLogoText() {
		assertThat(new HomePage().getLogoText()).as("HomePage logo text is incorrect.").isEqualTo("Chewy");
	}
}
