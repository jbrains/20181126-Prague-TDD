package ca.jbrains.pos.test;

import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;

public class SellOneItemTest {
    @Test
    public void productFound() throws Exception {
        final Display display = new Display();
        final Sale sale = new Sale(new Catalog(new HashMap<String, String>() {{
            put("12345", "CZK 780.00");
            put("23456", "CZK 1249.00");
        }}), display);

        sale.onBarcode("12345");

        Assert.assertEquals("CZK 780.00", display.getText());
    }

    @Test
    public void anotherProductFound() throws Exception {
        final Display display = new Display();
        final Sale sale = new Sale(new Catalog(new HashMap<String, String>() {{
            put("12345", "CZK 780.00");
            put("23456", "CZK 1249.00");
        }}), display);

        sale.onBarcode("23456");

        Assert.assertEquals("CZK 1249.00", display.getText());
    }

    @Test
    public void productNotFound() throws Exception {
        final Display display = new Display();
        final Sale sale = new Sale(new Catalog(new HashMap<String, String>() {{
            put("12345", "CZK 780.00");
            put("23456", "CZK 1249.00");
        }}), display);

        sale.onBarcode("99999");

        Assert.assertEquals("Product not found: 99999", display.getText());
    }

    @Test
    public void emptyBarcode() throws Exception {
        final Display display = new Display();
        final Sale sale = new Sale(new Catalog(null), display);

        sale.onBarcode("");

        Assert.assertEquals("Scanning error: empty barcode", display.getText());
    }

    public static class Sale {
        private final Catalog catalog;
        private final Display display;

        public Sale(Catalog catalog, Display display) {
            this.catalog = catalog;
            this.display = display;
        }

        public void onBarcode(String barcode) {
            if ("".equals(barcode)) {
                display.displayScannedEmptyBarcodeMessage();
                return;
            }

            final String priceAsText = catalog.findPrice(barcode);
            if (priceAsText == null) {
                display.displayProductNotFoundMessage(barcode);
            } else {
                display.displayPrice(priceAsText);
            }
        }
    }

    public static class Display {
        private String text;

        public String getText() {
            return text;
        }

        public void displayProductNotFoundMessage(String barcode) {
            this.text = String.format("Product not found: %s", barcode);
        }

        public void displayPrice(String priceAsText) {
            this.text = priceAsText;
        }

        public void displayScannedEmptyBarcodeMessage() {
            this.text = "Scanning error: empty barcode";
        }
    }
}
