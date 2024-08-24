package org.kafein.steps;

import com.thoughtworks.gauge.Step;
import org.kafein.methods.BaseMethods;
import org.kafein.methods.CimriProductDetailsMethods;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class CimriProductDetailsSteps {
    private final BaseMethods baseMethods = new BaseMethods();
    private final CimriProductDetailsMethods productMethods = new CimriProductDetailsMethods();

    @Step("Click on the marketplace of the cheapest product")
    public void clickCheapestProduct() {
        By priceLocator = baseMethods.getLocatorFromPage("cimri product details", "offer prices");
        By cardContainerLocator = baseMethods.getLocatorFromPage("cimri product details", "offer card container");

        List<WebElement> priceElements = productMethods.getElementsOrThrow(priceLocator, "price");
        List<WebElement> containerElements = productMethods.getElementsOrThrow(cardContainerLocator, "product card");

        int minPriceIndex = productMethods.findMinPriceIndex(priceElements);
        productMethods.clickMarketplaceLogo(containerElements, minPriceIndex);
    }
}
