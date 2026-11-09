package com.alihamza.appium.core;

import java.nio.file.Path;

/** Run settings from JVM system properties, with defaults for a local emulator. */
public final class Config {

    private Config() {
    }

    public static String appiumUrl() {
        return System.getProperty("appiumUrl", "http://127.0.0.1:4723");
    }

    public static String appPath() {
        return Path.of(System.getProperty("app", "apps/sauce-labs-sample.apk")).toAbsolutePath().toString();
    }

    public static String deviceName() {
        return System.getProperty("deviceName", "Android Emulator");
    }

    public static String udid() {
        return System.getProperty("udid", "");
    }

    public static int timeoutSeconds() {
        return Integer.parseInt(System.getProperty("timeout", "15"));
    }

    public static String password() {
        return System.getProperty("password", "secret_sauce");
    }
}
