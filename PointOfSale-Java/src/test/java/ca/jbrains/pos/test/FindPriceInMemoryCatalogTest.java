package ca.jbrains.pos.test;

import org.junit.Assert;
import org.junit.Test;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class FindPriceInMemoryCatalogTest {
    @Test
    public void productFound() throws Exception {
        final Price matchingPrice = Price.koruny(2700);
        final Catalog catalog = catalogWith("::barcode::", matchingPrice);
        Assert.assertEquals(matchingPrice, catalog.findPrice("::barcode::"));
    }

    private Catalog catalogWith(final String barcode, Price matchingPrice) {
        return new InMemoryCatalog(new HashMap<String, Price>() {{
            put(barcode, matchingPrice);
        }});
    }

    @Test
    public void productNotFound() throws Exception {
        final Catalog catalog = emptyCatalog();
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
