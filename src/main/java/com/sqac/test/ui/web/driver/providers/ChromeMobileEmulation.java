package com.sqac.test.ui.web.driver.providers;

import com.codeborne.selenide.WebDriverProvider;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.HashMap;
import java.util.Map;

public class ChromeMobileEmulation implements WebDriverProvider {

	static ChromeOptions getChromeOptions() {
		ChromeOptions chromeOptions = new ChromeOptions();
		chromeOptions.addArguments("disable-infobars");
		// TODO Research a purpose of these commented out prefs
		//        Map<String, Object> prefs = new HashMap<String, Object>();
		//        prefs.put("credentials_enable_service", false);
		//        prefs.put("profile.password_manager_enabled", false);
		//        chromeOptions.setExperimentalOption("prefs", prefs);
		Map<String, String> mobileEmulation = new HashMap<>();
		mobileEmulation.put("deviceName", "Nexus 5");
		chromeOptions.setExperimentalOption("mobileEmulation", mobileEmulation);
		return chromeOptions;
	}

	@Override
	public WebDriver createDriver(DesiredCapabilities capabilities) {
		WebDriverManager.chromedriver().setup();
		capabilities.setCapability(ChromeOptions.CAPABILITY, getChromeOptions());
		return new ChromeDriver(getChromeOptions());
	}
}
