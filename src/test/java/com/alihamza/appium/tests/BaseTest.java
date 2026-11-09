package com.alihamza.appium.tests;

import com.alihamza.appium.core.DriverFactory;
import com.alihamza.appium.screens.LoginScreen;
import org.openqa.selenium.OutputType;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public abstract class BaseTest {

    protected LoginScreen loginScreen;

    @BeforeMethod(alwaysRun = true)
    public void setUp() {
        DriverFactory.start();
        loginScreen = new LoginScreen(DriverFactory.getDriver()).waitUntilLoaded();
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown(ITestResult result) {
        if (result.getStatus() == ITestResult.FAILURE) {
            saveScreenshot(result.getMethod().getMethodName());
        }
        DriverFactory.quit();
    }

    private void saveScreenshot(String name) {
        try {
            byte[] png = DriverFactory.getDriver().getScreenshotAs(OutputType.BYTES);
            Path dir = Path.of("target", "screenshots");
            Files.createDirectories(dir);
            Files.write(dir.resolve(name + ".png"), png);
        } catch (IOException e) {
            System.err.println("Could not save screenshot: " + e.getMessage());
        }
    }
}
