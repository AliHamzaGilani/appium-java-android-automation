package com.alihamza.appium.screens;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;

public class LoginScreen extends BaseScreen {

    private static final By USERNAME = a11y("test-Username");
    private static final By PASSWORD = a11y("test-Password");
    private static final By LOGIN_BUTTON = a11y("test-LOGIN");
    private static final By ERROR_TEXT =
            AppiumBy.xpath("//android.view.ViewGroup[@content-desc='test-Error message']/android.widget.TextView");

    public LoginScreen(AndroidDriver driver) {
        super(driver);
    }

    public LoginScreen waitUntilLoaded() {
        visible(USERNAME);
        return this;
    }

    public ProductsScreen loginAs(String username, String password) {
        submit(username, password);
        return new ProductsScreen(driver);
    }

    public LoginScreen submit(String username, String password) {
        type(USERNAME, username);
        type(PASSWORD, password);
        hideKeyboardIfShown();
        tap(LOGIN_BUTTON);
        return this;
    }

    public String errorMessage() {
        return visible(ERROR_TEXT).getText();
    }
}
