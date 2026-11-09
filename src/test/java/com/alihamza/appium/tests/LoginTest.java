package com.alihamza.appium.tests;

import com.alihamza.appium.core.Config;
import com.alihamza.appium.screens.ProductsScreen;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class LoginTest extends BaseTest {

    @Test(groups = {"smoke", "regression"}, description = "Standard user reaches the products screen")
    public void standardUserCanLogIn() {
        ProductsScreen products = loginScreen.loginAs("standard_user", Config.password());

        assertThat(products.isLoaded()).isTrue();
        assertThat(products.visibleItemCount()).isPositive();
    }

    @DataProvider(name = "invalidLogins")
    public Object[][] invalidLogins() {
        return new Object[][]{
                {"locked_out_user", Config.password(), "Sorry, this user has been locked out."},
                {"standard_user", "wrong_password", "Username and password do not match any user in this service."},
                {"", Config.password(), "Username is required"},
        };
    }

    @Test(groups = "regression", dataProvider = "invalidLogins",
            description = "Invalid credentials show the right error")
    public void invalidLoginShowsError(String username, String password, String expectedError) {
        String error = loginScreen.submit(username, password).errorMessage();

        assertThat(error).isEqualTo(expectedError);
    }
}
