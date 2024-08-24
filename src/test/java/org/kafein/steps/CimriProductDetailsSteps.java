package org.kafein.steps;

import com.thoughtworks.gauge.Step;
import org.kafein.methods.CimriProductDetailsMethods;

public class CimriProductDetailsSteps {

    private final CimriProductDetailsMethods productMethods = new CimriProductDetailsMethods();

    @Step("Click on the marketplace of the cheapest product")
    public void clickCheapestProduct() {
        productMethods.clickOnCheapestProductMarketplace();
    }
}
