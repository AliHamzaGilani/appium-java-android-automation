package com.alihamza.appium.screens;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;

public class ProductsScreen extends BaseScreen {

    private static final By TITLE = text("PRODUCTS");
    private static final By ITEM = a11y("test-Item");
    private static final By ADD_TO_CART = a11y("test-ADD TO CART");
    private static final By REMOVE = a11y("test-REMOVE");
    private static final By CART_BADGE =
            AppiumBy.xpath("//android.view.ViewGroup[@content-desc='test-Cart']//android.widget.TextView");

    public ProductsScreen(AndroidDriver driver) {
        super(driver);
    }

    public boolean isLoaded() {
        return visible(TITLE).isDisplayed();
    }

    public int visibleItemCount() {
        visible(ITEM);
        return driver.findElements(ITEM).size();
    }

    /** Adds the first product currently on screen. */
    public ProductsScreen addFirstItemToCart() {
        visible(ADD_TO_CART);
        driver.findElements(ADD_TO_CART).get(0).click();
        return this;
    }

    public ProductsScreen removeFirstItemFromCart() {
        visible(REMOVE);
        driver.findElements(REMOVE).get(0).click();
        return this;
    }

    public String cartCount() {
        return isPresent(CART_BADGE) ? driver.findElement(CART_BADGE).getText() : "0";
    }
}
