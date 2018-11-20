package ca.jbrains.pos.test;

import ca.jbrains.pos.Price;
import org.junit.Assert;
import org.junit.Test;

import java.util.Collections;
import java.util.Map;

public class FindPriceInMemoryCatalogTest {
    @Test
    public void productFound() throws Exception {
        Price matchingPrice = Price.euroCents(795);
        InMemoryCatalog catalog = new InMemoryCatalog(
                Collections.singletonMap("::barcode::", matchingPrice));
        Assert.assertEquals(matchingPrice, catalog.findPrice("::barcode::"));
    }

    public static class InMemoryCatalog {
        private final Map<String, Price> pricesByBarcode;

        public InMemoryCatalog(Map<String, Price> pricesByBarcode) {
            this.pricesByBarcode = pricesByBarcode;
        }

        public Price findPrice(String barcode) {
            return pricesByBarcode.get(barcode);
        }
    }
}
