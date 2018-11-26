package ca.jbrains.pos.test;

import org.junit.Assert;
import org.junit.Test;

public class SellOneItemTest {
    @Test
    public void productFound() throws Exception {
        final Display display = new Display();
        final Sale sale = new Sale();

        sale.onBarcode("12345");

        Assert.assertEquals("CZK 780.00", display.getText());
    }

    public static class Sale {
        public void onBarcode(String barcode) {

        }
    }
    public static class Display {
        public String getText() {
            return "CZK 780.00";
        }
    }
}
