package com.sqac.test.extension;

import com.codeborne.selenide.Configuration;
import io.qameta.allure.Allure;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestWatcher;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

import static com.codeborne.selenide.Selenide.getUserAgent;
import static com.codeborne.selenide.Selenide.screenshot;

public class FrameworkTestWatcher implements TestWatcher {
	@Override
	public void testDisabled(ExtensionContext context, Optional<String> reason) {

	}

	@Override
	public void testSuccessful(ExtensionContext context) {

	}

	@Override
	public void testAborted(ExtensionContext context, Throwable cause) {

	}

	@Override
	public void testFailed(ExtensionContext context, Throwable cause) {
		String browser = Configuration.browser;
		String userAgent = getUserAgent();
		if (browser.contains("ChromeMobileEmulation") || userAgent.contains("Mobile")) {
			browser = "chrome_mobile";
		}
		String screenshotName = browser + "_" + context.getTestMethod().get().getName();
		screenshot(screenshotName);
		Path content = Paths.get("./build/allure-results/" + screenshotName + ".png");
		try (InputStream is = Files.newInputStream(content)) {
			Allure.addAttachment("Screenshot", "image/png", is, "png");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
