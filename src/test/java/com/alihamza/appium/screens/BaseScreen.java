package com.alihamza.appium.screens;

import com.alihamza.appium.core.Config;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

/** Shared waits and actions for every screen object. */
public abstract class BaseScreen {

    protected final AndroidDriver driver;
    protected final WebDriverWait wait;

    protected BaseScreen(AndroidDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(Config.timeoutSeconds()));
    }

    /** The sample app exposes "test-*" accessibility ids (content-desc) on its controls. */
    protected static By a11y(String id) {
        return AppiumBy.accessibilityId(id);
    }

    protected static By text(String visibleText) {
        return AppiumBy.androidUIAutomator("new UiSelector().text(\"" + visibleText + "\")");
    }

    protected WebElement visible(By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    protected void tap(By locator) {
        wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
    }

    protected void type(By locator, String value) {
        WebElement field = visible(locator);
        field.clear();
        field.sendKeys(value);
    }

    protected boolean isPresent(By locator) {
        return !driver.findElements(locator).isEmpty();
    }

    protected void hideKeyboardIfShown() {
        if (driver.isKeyboardShown()) {
            driver.hideKeyboard();
        }
    }
}
