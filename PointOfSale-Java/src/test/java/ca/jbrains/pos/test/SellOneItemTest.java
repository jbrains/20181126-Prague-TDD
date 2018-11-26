package ca.jbrains.pos.test;

import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class SellOneItemTest {
    @Test
    public void productFound() throws Exception {
        final Display display = new Display();
        final Sale sale = new Sale(display, new HashMap<String, String>() {{
            put("12345", "CZK 780.00");
            put("23456", "CZK 1249.00");
        }});

        sale.onBarcode("12345");

        Assert.assertEquals("CZK 780.00", display.getText());
    }

    @Test
    public void anotherProductFound() throws Exception {
        final Display display = new Display();
        final Sale sale = new Sale(display, new HashMap<String, String>() {{
            put("12345", "CZK 780.00");
            put("23456", "CZK 1249.00");
        }});

        sale.onBarcode("23456");

        Assert.assertEquals("CZK 1249.00", display.getText());
    }

    @Test
    public void productNotFound() throws Exception {
        final Display display = new Display();
        final Sale sale = new Sale(display, new HashMap<String, String>() {{
            put("12345", "CZK 780.00");
            put("23456", "CZK 1249.00");
        }});

        sale.onBarcode("99999");

        Assert.assertEquals("Product not found: 99999", display.getText());
    }

    @Test
    public void emptyBarcode() throws Exception {
        final Display display = new Display();
        final Sale sale = new Sale(display, null);

        sale.onBarcode("");

        Assert.assertEquals("Scanning error: empty barcode", display.getText());
    }

    public static class Sale {
        private Display display;
        private final Map<String, String> pricesByBarcode;

        public Sale(Display display, final HashMap<String, String> pricesByBarcode) {
            this.display = display;
            this.pricesByBarcode = pricesByBarcode;
        }

        public void onBarcode(String barcode) {
            if ("".equals(barcode)) {
                displayScannedEmptyBarcodeMessage();
                return;
            }

            final String priceAsText = findPrice(barcode);
            if (priceAsText != null) {
                displayPrice(priceAsText);
            } else {
                displayProductNotFoundMessage(barcode);
            }
        }

        private String findPrice(String barcode) {
            return pricesByBarcode.get(barcode);
        }

        private void displayProductNotFoundMessage(String barcode) {
            display.setText(String.format("Product not found: %s", barcode));
        }

        private void displayPrice(String priceAsText) {
            display.setText(priceAsText);
        }

        private void displayScannedEmptyBarcodeMessage() {
            display.setText("Scanning error: empty barcode");
        }
    }

    public static class Display {
        private String text;

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }
    }
}
