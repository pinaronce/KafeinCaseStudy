package org.kafein.methods;

import org.kafein.base.DriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.NoSuchElementException;

public class CimriProductDetailsMethods extends DriverManager {

    public void clickMarketplaceLogo(List<WebElement> containerElements, int minPriceIndex) {
        WebElement marketplaceElement = containerElements.get(minPriceIndex).findElement(By.cssSelector("img"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", marketplaceElement);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", marketplaceElement);
        logger.info("Clicked on the marketplace logo of the cheapest product.");
    }

    public int findMinPriceIndex(List<WebElement> priceElements) {
        double minPrice = Double.MAX_VALUE;
        int minPriceIndex = -1;

        for (int i = 0; i < priceElements.size(); i++) {
            String priceText = priceElements.get(i).getText().replaceAll("[^0-9,]", "").replace(",", ".");
            double price = Double.parseDouble(priceText);

            if (price < minPrice) {
                minPrice = price;
                minPriceIndex = i;
            }
        }
        return minPriceIndex;
    }

    public List<WebElement> getElementsOrThrow(By locator, String elementName) {
        List<WebElement> elements = driver.findElements(locator);
        if (elements.isEmpty()) {
            logger.error("No " + elementName + " found.");
            throw new NoSuchElementException("No " + elementName + " found.");
        }
        return elements;
    }
}
