package com.sqac.test.allure;

import com.codeborne.selenide.Configuration;
import io.qameta.allure.listener.TestLifecycleListener;
import io.qameta.allure.model.TestResult;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import static java.nio.charset.StandardCharsets.UTF_8;

public class CustomTestNameTestLifecycleListener implements TestLifecycleListener {

	@Override
	public void beforeTestSchedule(TestResult result) {
		String browser = Configuration.browser;
		Object chromeOptions = Configuration.browserCapabilities.getCapability("goog:chromeOptions");
		if (browser.contains("ChromeMobileEmulation") || chromeOptions != null) {
			browser = "chrome_mobile";
		}

		String customDisplayName = String.format("%s [%s]", result.getName(), browser);
		result.setName(customDisplayName);
		result.setHistoryId(md5(customDisplayName));
	}

	private String md5(final String source) {
		final byte[] bytes = getMessageDigest().digest(source.getBytes(UTF_8));
		return new BigInteger(1, bytes).toString(16);
	}

	private MessageDigest getMessageDigest() {
		try {
			return MessageDigest.getInstance("md5");
		} catch (NoSuchAlgorithmException e) {
			throw new IllegalStateException("Could not find md5 hashing algorithm", e);
		}
	}
}
