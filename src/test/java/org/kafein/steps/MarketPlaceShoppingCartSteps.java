package org.kafein.steps;

import com.thoughtworks.gauge.Step;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.kafein.methods.MarketPlaceShoppingCartMethods;

public class MarketPlaceShoppingCartSteps {

    private final MarketPlaceShoppingCartMethods shoppingCartMethods = new MarketPlaceShoppingCartMethods();

    @Step("Adjust product quantity based on stock status and verify price consistency")
    public void adjustProductQuantityBasedOnStock() {
        shoppingCartMethods.adjustProductQuantityBasedOnStock();
    }
}
