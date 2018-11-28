package ca.jbrains.pos.test;

import org.junit.Test;
import org.mockito.Mockito;

public class SellOneItemControllerTest {
    @Test
    public void productFound() throws Exception {
        final Catalog catalog = Mockito.mock(Catalog.class);
        final Display display = Mockito.mock(Display.class);

        final Price matchingPrice = Price.koruny(180);

        Mockito.when(catalog.findPrice("::barcode::")).thenReturn(matchingPrice);

        new SellOneItemController(catalog, display).onBarcode("::barcode::");

        Mockito.verify(display).displayPrice(matchingPrice);
    }

    interface Catalog {
        Price findPrice(String barcode);
    }

    interface Display {
        void displayPrice(Price price);
    }

    public static class SellOneItemController {
        private final Catalog catalog;
        private final Display display;

        public SellOneItemController(Catalog catalog, Display display) {
            this.catalog = catalog;
            this.display = display;
        }

        public void onBarcode(String barcode) {
            display.displayPrice(catalog.findPrice(barcode));
        }
    }

    public static class Price {
        public static Price koruny(int korunyValue) {
            return new Price();
        }
    }
}
