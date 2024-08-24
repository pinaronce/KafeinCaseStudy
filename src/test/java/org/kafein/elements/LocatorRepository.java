package org.kafein.elements;

import org.openqa.selenium.By;

import java.util.HashMap;
import java.util.Map;

public class LocatorRepository {
    private static Map<String, Map<String, By>> pageElementLocators = new HashMap<>();

    static {
        pageElementLocators.put("cimri home", CimriHomePageElements.LOCATORS);
        pageElementLocators.put("cimri search", CimriSearchPageElements.LOCATORS);
        pageElementLocators.put("cimri product details", CimriProductDetailsElements.LOCATORS);
        pageElementLocators.put("market place login", MarketPlaceLoginPageElements.LOCATORS);
        pageElementLocators.put("market place account", MarketPlaceAccountPageElements.LOCATORS);
        pageElementLocators.put("market place navbar", MarketPlaceNavbarElements.LOCATORS);
        pageElementLocators.put("market place search", MarketPlaceSearchPageElements.LOCATORS);
        pageElementLocators.put("market place product details", MarketPlaceProductDetailsElements.LOCATORS);
        pageElementLocators.put("market place shopping cart", MarketPlaceShoppingCartPageElements.LOCATORS);
    }

    public static Map<String, Map<String, By>> getPageElementLocators() {
        return pageElementLocators;
    }
}
