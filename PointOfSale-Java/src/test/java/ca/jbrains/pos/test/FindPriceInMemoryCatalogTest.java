package ca.jbrains.pos.test;

import ca.jbrains.pos.Catalog;
import ca.jbrains.pos.Price;
import org.junit.Assert;
import org.junit.Test;

import java.util.Collections;
import java.util.Map;

public class FindPriceInMemoryCatalogTest {
    @Test
    public void productFound() throws Exception {
        Price matchingPrice = Price.euroCents(795);
        Catalog catalog = catalogWith("::barcode::", matchingPrice);
        Assert.assertEquals(matchingPrice, catalog.findPrice("::barcode::"));
    }

    private Catalog catalogWith(String barcode, Price matchingPrice) {
        return new InMemoryCatalog(
                Collections.singletonMap(barcode, matchingPrice));
    }

    @Test
    public void productNotFound() throws Exception {
        Catalog catalog = emptyCatalog();
        Assert.assertEquals(null, catalog.findPrice("::missing barcode::"));
    }

    private Catalog emptyCatalog() {
        return new InMemoryCatalog(Collections.emptyMap());
    }

    public static class InMemoryCatalog implements Catalog {
        private final Map<String, Price> pricesByBarcode;

        public InMemoryCatalog(Map<String, Price> pricesByBarcode) {
            this.pricesByBarcode = pricesByBarcode;
        }

        public Price findPrice(String barcode) {
            return pricesByBarcode.get(barcode);
        }
    }
}
