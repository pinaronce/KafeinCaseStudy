package org.kafein.elements;

import org.openqa.selenium.By;

import java.util.HashMap;
import java.util.Map;

public class MarketPlaceLoginPageElements {
    public static final Map<String, By> LOCATORS = new HashMap<>();

    static {
        LOCATORS.put("email input", By.id("login-email"));
        LOCATORS.put("password input", By.id("login-password-input"));
        LOCATORS.put("login button", By.cssSelector("button[class='q-primary q-fluid q-button-medium q-button submit']"));
    }
}
