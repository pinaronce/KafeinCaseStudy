package org.kafein.methods;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.kafein.base.DriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class MarketPlaceShoppingCartMethods extends DriverManager {

    private static final Logger logger = LogManager.getLogger(MarketPlaceShoppingCartMethods.class);
    private final BaseMethods baseMethods = new BaseMethods();
    private double originalPrice;

    public void adjustProductQuantityBasedOnStock() {
        WebDriver driver = getDriver();

        try {
            originalPrice = getPrice(driver);
            logger.info("Original price of the product: " + originalPrice);

            WebElement stockTextElement = driver.findElement(By.cssSelector("span[class='pb-quantity']"));
            String stockText = stockTextElement.getText().trim();
            logger.info("Stock status: " + stockText);

            if ("son 1 ürün".equalsIgnoreCase(stockText)) {
                logger.info("Stock is limited to 1 item only. No further action will be taken.");
            } else {
                setQuantity(driver, 2);
            }
        } catch (NoSuchElementException e) {
            logger.info("No stock quantity element found, adjusting quantity to 2.");
            setQuantity(driver, 2);
        }
    }

    public void setQuantity(WebDriver driver, int quantity) {
        WebElement quantityInput = null;
        boolean success = false;
        int attempts = 0;

        while (!success && attempts < 3) {
            try {
                quantityInput = new WebDriverWait(driver, Duration.ofSeconds(10))
                        .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@aria-label='Ürün adedi arttırma']/ancestor::div[contains(@class, 'ty-numeric-counter')]/input[@class='counter-content']")));
                logger.info("Setting quantity to: " + quantity);
                clearAndSetQuantity(driver, quantityInput, quantity);
                success = true;
            } catch (StaleElementReferenceException e) {
                attempts++;
                logger.warn("Trying to recover from a stale element: " + e.getMessage());
            }
        }

        if (!success) {
            throw new RuntimeException("Failed to interact with the quantity input after 3 attempts.");
        }
    }

    private void clearAndSetQuantity(WebDriver driver, WebElement quantityInput, int quantity) {
        logger.info("Clearing the quantity input field.");
        quantityInput.click();
        baseMethods.waitBySeconds(2);
        quantityInput.sendKeys(Keys.END);
        baseMethods.waitBySeconds(2);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].value = '';", quantityInput);
        baseMethods.waitBySeconds(2);
        logger.info("Setting new quantity: " + quantity);
        quantityInput.sendKeys(String.valueOf(quantity));
        quantityInput.sendKeys(Keys.ENTER);
        baseMethods.waitBySeconds(2);
    }

    public void verifyQuantity(WebDriver driver, WebElement quantityInput, int expectedQuantity) {
        baseMethods.waitBySeconds(1);
        String currentValue = quantityInput.getAttribute("value");
        logger.info("Verifying quantity. Expected: " + expectedQuantity + ", Actual: " + currentValue);

        if (String.valueOf(expectedQuantity).equals(currentValue)) {
            logger.info("Quantity successfully verified as " + expectedQuantity);
            double newPrice = getPrice(driver);
            double calculatedPrice = originalPrice * expectedQuantity;
            logger.info("Calculated total price: " + calculatedPrice);
            logger.info("New displayed price: " + newPrice);

            if (Double.compare(newPrice, calculatedPrice) == 0) {
                logger.info("Price verification successful. New price is as expected: " + newPrice);
            } else {
                logger.error("Price verification failed. Expected: " + calculatedPrice + ", but got: " + newPrice);
                throw new AssertionError("Price verification failed. Expected: " + calculatedPrice + ", but got: " + newPrice);
            }
        } else {
            logger.error("Quantity verification failed. Expected " + expectedQuantity + ", but was " + currentValue);
            throw new AssertionError("Quantity verification failed. Expected " + expectedQuantity + ", but was " + currentValue);
        }
    }

    private double getPrice(WebDriver driver) {
        String priceText = driver.findElement(By.cssSelector("div[class='pb-basket-item-price']")).getText();
        double price = Double.parseDouble(priceText.replaceAll("[^0-9,]", "").replace(",", "."));
        logger.info("Retrieved price: " + price);
        return price;
    }

    private WebDriver getDriver() {
        return driver;
    }
}
