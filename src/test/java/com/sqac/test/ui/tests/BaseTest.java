package com.sqac.test.ui.tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import com.sqac.test.extension.FrameworkTestWatcher;
import com.sqac.test.ui.configuration.PropertiesHelper;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.Properties;

import static com.codeborne.selenide.Selenide.close;
import static com.codeborne.selenide.Selenide.open;
import static com.sqac.test.ui.configuration.PropertiesHelper.configureTestOn;

@ExtendWith(FrameworkTestWatcher.class)
public abstract class BaseTest {

	private static String appURL;

	@BeforeAll
	public static void setUp() {
		SelenideLogger.addListener("allure", new AllureSelenide());
		Properties properties = PropertiesHelper.read();
		appURL = properties.getProperty("app.url");
		Configuration.browser = properties.getProperty("browser");

		String testOn = properties.getProperty("test.on", "local");
		configureTestOn(testOn, properties);
	}

	@AfterAll
	public static void tearDown() {
		SelenideLogger.removeListener("allure");
	}

	@BeforeEach
	public void openApp() {
		open(appURL);
	}

	@AfterEach
	public void clearData() {
		close();
	}
}
