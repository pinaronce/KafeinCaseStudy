package org.kafein.elements;

import org.openqa.selenium.By;

import java.util.HashMap;
import java.util.Map;

public class MarketPlaceProductDetailsElements {
    public static final Map<String, By> LOCATORS = new HashMap<>();

    static {
        LOCATORS.put("add to cart button", By.cssSelector("div[class='add-to-basket-button-text']"));
        LOCATORS.put("cookie accept button", By.id("onetrust-accept-btn-handler"));
        LOCATORS.put("understood button", By.cssSelector("button[class='onboarding-button']"));
        LOCATORS.put("success message", By.cssSelector("span[class='product-preview-status-text']"));
        LOCATORS.put("go to cart button", By.cssSelector("a[class='go-basket-button']"));
        LOCATORS.put("product price", By.cssSelector("div.product-price-container > div > div > span"));
    }
}

