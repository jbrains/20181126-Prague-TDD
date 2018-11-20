package ca.jbrains.pos;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

public class SellOneItemControllerTest {

    private Catalog catalog;
    private Display display;

    @Before
    public void createCollaborators() throws Exception {
        catalog = Mockito.mock(Catalog.class);
        display = Mockito.mock(Display.class);
    }

    @Test
    public void productFound() throws Exception {
        Price matchingPrice = anyValidPrice();
        Mockito.when(catalog.findPrice("::barcode::")).thenReturn(matchingPrice);

        new SellOneItemController(catalog, display).onBarcode("::barcode::");

        Mockito.verify(display).displayPrice(matchingPrice);
    }

    private Price anyValidPrice() {
        return Price.euroCents(795);
    }

    @Test
    public void productNotFound() throws Exception {
        Mockito.when(catalog.findPrice("::missing barcode::")).thenReturn(null);

        new SellOneItemController(catalog, display).onBarcode("::missing barcode::");

        Mockito.verify(display).displayProductNotFoundMessage("::missing barcode::");
    }

    @Test
    public void emptyBarcode() throws Exception {
        new SellOneItemController(null, display).onBarcode("");

        Mockito.verify(display).displayScannedEmptyBarcodeMessage();
    }

    public static class SellOneItemController {
        private final Catalog catalog;
        private final Display display;

        public SellOneItemController(Catalog catalog, Display display) {
            this.catalog = catalog;
            this.display = display;
        }

        public void onBarcode(String barcode) {
            if ("".equals(barcode)) {
                display.displayScannedEmptyBarcodeMessage();
                return;
            }

            Price price = catalog.findPrice(barcode);
            if (price == null)
                display.displayProductNotFoundMessage(barcode);
            else
                display.displayPrice(price);
        }
    }

    public static class Price {
        public static Price euroCents(int euroCentsValue) {
            return new Price();
        }
    }

    public interface Catalog {
        Price findPrice(String barcode);
    }

    public interface Display {
        void displayPrice(Price price);

        void displayProductNotFoundMessage(String barcode);

        void displayScannedEmptyBarcodeMessage();
    }
}
