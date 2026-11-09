package com.alihamza.appium.tests;

import com.alihamza.appium.core.Config;
import com.alihamza.appium.screens.ProductsScreen;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class CartTest extends BaseTest {

    @Test(groups = {"smoke", "regression"}, description = "Cart badge counts added items")
    public void addingItemUpdatesCartBadge() {
        ProductsScreen products = loginScreen.loginAs("standard_user", Config.password());

        products.addFirstItemToCart();

        assertThat(products.cartCount()).isEqualTo("1");
    }

    @Test(groups = "regression", description = "Removing an item clears the cart badge")
    public void removingItemClearsCartBadge() {
        ProductsScreen products = loginScreen.loginAs("standard_user", Config.password());

        products.addFirstItemToCart().removeFirstItemFromCart();

        assertThat(products.cartCount()).isEqualTo("0");
    }
}
