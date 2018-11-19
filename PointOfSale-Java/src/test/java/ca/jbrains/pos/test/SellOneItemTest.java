package ca.jbrains.pos.test;

import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class SellOneItemTest {
    @Test
    public void productFound() throws Exception {
        Display display = new Display();
        Sale sale = new Sale(display, new HashMap<String, String>() {{
            put("12345", "EUR 7.65");
        }});

        sale.onBarcode("12345");

        Assert.assertEquals("EUR 7.65", display.getText());
    }

    @Test
    public void anotherProductFound() throws Exception {
        Display display = new Display();
        Sale sale = new Sale(display, new HashMap<String, String>() {{
            put("23456", "EUR 12.50");
        }});

        sale.onBarcode("23456");

        Assert.assertEquals("EUR 12.50", display.getText());
    }

    @Test
    public void productNotFound() throws Exception {
        Display display = new Display();
        Sale sale = new Sale(display, new HashMap<String, String>() {{
            put("12345", "EUR 7.65");
            put("23456", "EUR 12.50");
        }});

        sale.onBarcode("::missing barcode::");

        Assert.assertEquals("Product not found: ::missing barcode::", display.getText());
    }

    @Test
    public void emptyBarcode() throws Exception {
        Display display = new Display();
        Sale sale = new Sale(display, new HashMap<String, String>() {{
            put("12345", "EUR 7.65");
            put("23456", "EUR 12.50");
        }});

        sale.onBarcode("");

        Assert.assertEquals("Scanning error: empty barcode", display.getText());
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

    public static class Sale {
        private Display display;
        private final Map<String, String> pricesByBarcode;

        public Sale(Display display, Map<String, String> pricesByBarcode) {
            this.display = display;
            this.pricesByBarcode = pricesByBarcode;
        }

        public void onBarcode(String barcode) {
            if ("".equals(barcode)) {
                display.setText("Scanning error: empty barcode");
                return;
            }

            String priceAsText = pricesByBarcode.get(barcode);
            if (priceAsText == null) {
                display.setText(String.format("Product not found: %s", barcode));
            } else {
                display.setText(priceAsText);
            }
        }
    }
}
