package org.kafein.elements;

import org.openqa.selenium.By;

import java.util.HashMap;
import java.util.Map;

public class MarketPlaceShoppingCartPageElements {
    public static final Map<String, By> LOCATORS = new HashMap<>();

    static {
        LOCATORS.put("basket item price", By.cssSelector("div[class='pb-basket-item-price']"));
        LOCATORS.put("basket stock quantity", By.cssSelector("span[class='pb-quantity']"));
        LOCATORS.put("remove product button", By.cssSelector("button[class='checkout-saving-remove-button']"));
        LOCATORS.put("increase product quantity button", By.cssSelector("button[class='ty-numeric-counter-button']"));
        LOCATORS.put("product quantity text", By.xpath("//button[@aria-label='Ürün adedi arttırma']/ancestor::div[contains(@class, 'ty-numeric-counter')]/input[@class='counter-content']"));
        LOCATORS.put("go to product details", By.cssSelector("span[data-tracker='seen:almost-sold-out-stamp']"));
    }
}