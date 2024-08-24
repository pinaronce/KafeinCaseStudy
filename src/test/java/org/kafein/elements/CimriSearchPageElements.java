package org.kafein.elements;

import org.openqa.selenium.By;

import java.util.HashMap;
import java.util.Map;

public class CimriSearchPageElements {
    public static final Map<String, By> LOCATORS = new HashMap<>();

    static {
        LOCATORS.put("product titles", By.cssSelector("h3[class='Title_title__Mt3jb Title_withMargin__q4kli']"));
        LOCATORS.put("first product container", By.cssSelector("#productListContainer>div>article:nth-child(1)"));
        LOCATORS.put("product listing dropdown menu", By.cssSelector("div[class='Dropdown_dropdownWrapper__G5Vss']"));
        LOCATORS.put("lowest price dropdown option", By.xpath("//a[text()='En Düşük Fiyat']"));
    }

}
