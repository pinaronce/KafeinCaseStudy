package org.kafein.methods;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.kafein.base.DriverManager;
import org.openqa.selenium.*;
import java.util.List;
import java.util.NoSuchElementException;

public class MarketPlaceShoppingCartMethods extends DriverManager {

    private final BaseMethods baseMethods = new BaseMethods();
    protected static final int DEFAULT_TIMEOUT_SECONDS = 20;
    private static final Logger logger = LogManager.getLogger(MarketPlaceShoppingCartMethods.class);

    public List<WebElement> getStockTextElements() {
        try {
            return baseMethods.getElements("market place shopping cart", "basket stock quantity");
        } catch (NoSuchElementException | TimeoutException e) {
            logger.warn("Stock text element not found or timed out. Proceeding with default stock status for all products.");
            return null;
        }
    }

    public boolean shouldIncreaseQuantity(String stockText, WebElement quantityInput) {
        int currentQuantity = Integer.parseInt(quantityInput.getAttribute("value").trim());
        return stockText == null || (!"Son 1 Ürün".equals(stockText) && currentQuantity < 2);
    }

    public void increaseQuantity(WebElement increaseButton, WebElement quantityInput, int index) {
        increaseButton.click();
        waitForQuantityUpdate(quantityInput, index);
    }

    public void waitForQuantityUpdate(WebElement quantityInput, int index) {
        baseMethods.waitBySeconds(2);

        baseMethods.waitForElement("market place shopping cart", "product quantity text", "visible", DEFAULT_TIMEOUT_SECONDS);
        int currentQuantity = Integer.parseInt(quantityInput.getAttribute("value").trim());

        if (currentQuantity == 2) {
            logger.info("Quantity successfully set to 2 for product index: " + index);
        } else {
            logger.error("Failed to set quantity to 2 for product index: " + index);
            throw new AssertionError("Expected quantity to be 2, but found " + currentQuantity);
        }
    }
}
