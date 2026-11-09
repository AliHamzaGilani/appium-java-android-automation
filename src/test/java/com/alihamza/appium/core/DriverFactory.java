package com.alihamza.appium.core;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;

import java.net.MalformedURLException;
import java.net.URI;
import java.time.Duration;

/** One AndroidDriver per thread, configured with UiAutomator2. */
public final class DriverFactory {

    private static final ThreadLocal<AndroidDriver> DRIVER = new ThreadLocal<>();

    private DriverFactory() {
    }

    public static AndroidDriver getDriver() {
        AndroidDriver driver = DRIVER.get();
        if (driver == null) {
            throw new IllegalStateException("Driver not started. Call DriverFactory.start() first.");
        }
        return driver;
    }

    public static void start() {
        UiAutomator2Options options = new UiAutomator2Options()
                .setDeviceName(Config.deviceName())
                .setApp(Config.appPath())
                .setAppPackage("com.swaglabsmobileapp")
                .setAppActivity("com.swaglabsmobileapp.MainActivity")
                .setAutoGrantPermissions(true)
                .setNewCommandTimeout(Duration.ofSeconds(120))
                .setUiautomator2ServerInstallTimeout(Duration.ofSeconds(120))
                .setFullReset(false)
                .setNoReset(false);

        if (!Config.udid().isBlank()) {
            options.setUdid(Config.udid());
        }

        try {
            DRIVER.set(new AndroidDriver(URI.create(Config.appiumUrl()).toURL(), options));
        } catch (MalformedURLException e) {
            throw new IllegalArgumentException("Invalid Appium URL: " + Config.appiumUrl(), e);
        }
    }

    public static void quit() {
        AndroidDriver driver = DRIVER.get();
        if (driver != null) {
            driver.quit();
            DRIVER.remove();
        }
    }
}
