package org.kafein.elements;

import org.openqa.selenium.By;

import java.util.HashMap;
import java.util.Map;

public class MarketPlaceSearchPageElements {
    public static final Map<String, By> LOCATORS = new HashMap<>();

    static {
        LOCATORS.put("search input", By.cssSelector("input[data-testid='suggestion']"));
        LOCATORS.put("product listing dropdown menu", By.cssSelector("div[class='selected-order']"));
        LOCATORS.put("lowest price dropdown option", By.xpath("//span[text()='En düşük fiyat']"));
        LOCATORS.put("first product", By.cssSelector("div:nth-child(1)>div.p-card-chldrn-cntnr.card-border>a"));
    }
}
