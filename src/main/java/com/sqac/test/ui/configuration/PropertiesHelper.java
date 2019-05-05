package com.sqac.test.ui.configuration;

import com.codeborne.selenide.Configuration;
import com.sqac.test.ui.web.driver.providers.ChromeMobileEmulation;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class PropertiesHelper {

	public static Properties read() {
		Properties profileProperties = new Properties();

		try {
			profileProperties.load(PropertiesHelper.class.getClassLoader()
					.getResourceAsStream(System.getProperty("profile", "test") + "/" + "config.properties"));

			Properties systemProperties = System.getProperties();

			System.out.println("\n[Properties reading] ---------------------------------------------------------");

			for (Map.Entry entry : profileProperties.entrySet()) {
				String key = String.valueOf(entry.getKey());
				System.out.println(key + " = " + entry.getValue());
				if (systemProperties.containsKey(key)) {
					String value = systemProperties.getProperty(key);

					if (!value.isEmpty()) {
						profileProperties.setProperty(key, value);
						System.out.println(key + " = " + entry.getValue() + " !!! corrected");
					}
				}
			}
			System.out.println("[Properties reading] ---------------------------------------------------------\n");
		} catch (IOException e) {
			System.out.println("Error : config.properties is not exist");
			e.printStackTrace();
		}
		return profileProperties;
	}

	public static void configureTestOn(String testOn, Properties properties) {
		if (testOn.equals("grid")) {
			// Set capabilities for grid.
			String gridBrowser = properties.getProperty("test.grid.browser", "chrome");
			setBasicGridCapabilities(properties);
			if (isChromeOrFirefox(gridBrowser)) {
				// Set grid with chrome or firefox.
				setBrowser(gridBrowser);
			} else if (gridBrowser.equals("chrome_mobile")) {
				// Set grid with chrome_mobile.
				Configuration.browser = "chrome";
				Configuration.browserCapabilities.setCapability(ChromeOptions.CAPABILITY, getMobileChromeOptions());
			}
		} else {
			// Set capabilities for local.
			String browser = properties.getProperty("test.local.browser", "chrome");
			if (isChromeOrFirefox(browser)) {
				// Set local with chrome or firefox.
				setBrowser(browser);
			} else if (browser.equals("chrome_mobile")) {
				// Set local with chrome_mobile.
				Configuration.browser = ChromeMobileEmulation.class.getName();
			}
		}
		Configuration.savePageSource = false;
		Configuration.reportsFolder = "./build/allure-results";
	}

	private static boolean isChromeOrFirefox(String browser) {
		return browser.equals("chrome") || browser.equals("firefox");
	}

	private static void setBrowser(String browser) {
		Configuration.browser = browser;
		Configuration.startMaximized = true;
	}

	private static void setBasicGridCapabilities(Properties properties) {
		Configuration.remote = properties.getProperty("test.grid.url");
		final DesiredCapabilities caps = new DesiredCapabilities();
		caps.setCapability("enableVNC", true);
		Configuration.browserCapabilities = caps;
		System.setProperty("capabilities.marionette", "false");
	}

	private static ChromeOptions getMobileChromeOptions() {
		ChromeOptions chromeOptions = new ChromeOptions();
		chromeOptions.addArguments("disable-infobars");
		Map<String, String> mobileEmulation = new HashMap<>();
		mobileEmulation.put("deviceName", "Nexus 5");
		chromeOptions.setExperimentalOption("mobileEmulation", mobileEmulation);
		return chromeOptions;
	}
}
