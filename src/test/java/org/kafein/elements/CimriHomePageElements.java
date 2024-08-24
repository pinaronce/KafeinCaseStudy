package org.kafein.elements;

import org.openqa.selenium.By;

import java.util.HashMap;
import java.util.Map;

public class CimriHomePageElements {
    public static final Map<String, By> LOCATORS = new HashMap<>();

    static {
        LOCATORS.put("header logo", By.cssSelector("a.Header_cimri__pqrCt"));
        LOCATORS.put("latest discounts text", By.xpath("//section[@class='WidgetBlock_widgetBlockContainer__AOs7d'][1]/div[1]/div"));
        LOCATORS.put("cookie accept button", By.id("onetrust-accept-btn-handler"));
        LOCATORS.put("search box", By.cssSelector("div.SearchBox_searchBarContainer__XlvBP"));
        LOCATORS.put("search input", By.id("search-input"));
    }

}


