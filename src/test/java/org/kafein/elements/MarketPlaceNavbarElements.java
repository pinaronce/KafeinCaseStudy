package org.kafein.elements;

import org.openqa.selenium.By;

import java.util.HashMap;
import java.util.Map;

public class MarketPlaceNavbarElements {

    public static final Map<String, By> LOCATORS = new HashMap<>();

    static {
        LOCATORS.put("go to login button", By.cssSelector("div.link.account-user"));
        LOCATORS.put("account username", By.cssSelector("div[class='link account-user']"));
        LOCATORS.put("my cart button", By.cssSelector("a[class='link account-basket']"));
    }

}
