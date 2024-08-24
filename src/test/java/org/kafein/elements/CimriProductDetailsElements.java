package org.kafein.elements;

import org.openqa.selenium.By;

import java.util.HashMap;
import java.util.Map;

public class CimriProductDetailsElements {

    public static final Map<String, By> LOCATORS = new HashMap<>();

    static {
        LOCATORS.put("view other offers button", By.cssSelector("div[class='OfferList_showAllButton__xb3_8 OfferList_desktop__rcuyH']>span"));
        LOCATORS.put("offer prices", By.cssSelector("div.OfferCard_price___ZtSh"));
        LOCATORS.put("offer card container", By.cssSelector("div.OfferCard_container__fFJy3"));
        LOCATORS.put("marketplace logo", By.cssSelector("div[class='OfferCard_marketPlace__4vsBA OfferCard_desktop__dpDZ5']>img"));
        LOCATORS.put("go to store button", By.cssSelector("button[class='Button_button__ozINu Button_xlarge__9UfJn Button_primary__DAYOu StickyHeaderAction_button__ZyI_0 StickyHeaderAction_withArrow__WJJ3W']"));


    }

}
