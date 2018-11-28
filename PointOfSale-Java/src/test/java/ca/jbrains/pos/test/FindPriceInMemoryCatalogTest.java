package ca.jbrains.pos.test;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class FindPriceInMemoryCatalogTest extends FindPriceInCatalogContract {

    @Override
    protected Catalog catalogWith(final String barcode, Price matchingPrice) {
        return new InMemoryCatalog(new HashMap<String, Price>() {{
            put("not " + barcode, Price.koruny(0));
            put(barcode, matchingPrice);
            put("certainly not " + barcode, Price.koruny(0));
            put("I told you, not " + barcode + ", you idiot.", Price.koruny(0));
        }});
    }

    @Override
    protected Catalog catalogWithout(String barcodeToAvoid) {
        return new InMemoryCatalog(new HashMap<String, Price>() {{
            put("not " + barcodeToAvoid, Price.koruny(0));
            put("certainly not " + barcodeToAvoid, Price.koruny(0));
            put("I told you, not " + barcodeToAvoid + ", you idiot.", Price.koruny(0));
        }});
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
