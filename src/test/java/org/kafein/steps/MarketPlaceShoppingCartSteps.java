package org.kafein.steps;

import com.thoughtworks.gauge.Step;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.kafein.methods.BaseMethods;
import org.kafein.methods.MarketPlaceShoppingCartMethods;
import org.openqa.selenium.WebElement;

import java.util.List;

public class MarketPlaceShoppingCartSteps {
    private static final Logger logger = LogManager.getLogger(MarketPlaceShoppingCartSteps.class);
    private final MarketPlaceShoppingCartMethods cartMethods = new MarketPlaceShoppingCartMethods();
    private final BaseMethods baseMethods = new BaseMethods();

    @Step("Check stock availability and increase product quantity to 2 if possible")
    public void checkStockAndIncrementProductQuantity() {
        List<WebElement> increaseButtons = baseMethods.getElements("market place shopping cart", "increase product quantity button");
        List<WebElement> quantityInputs = baseMethods.getElements("market place shopping cart", "product quantity text");
        List<WebElement> stockTextElements = cartMethods.getStockTextElements();

        for (int i = 0; i < increaseButtons.size(); i++) {
            String stockText = (stockTextElements != null && stockTextElements.size() > i) ? stockTextElements.get(i).getText().trim() : "";

            if (cartMethods.shouldIncreaseQuantity(stockText, quantityInputs.get(i))) {
                cartMethods.increaseQuantity(increaseButtons.get(i), quantityInputs.get(i), i);
            } else if ("Son 1 Ürün".equals(stockText)) {
                logger.info("Stock is limited to 1 for product index: " + i + ". No action taken.");
            }
        }
    }
}
