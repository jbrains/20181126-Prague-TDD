package ca.jbrains.pos.test;

import ca.jbrains.pos.*;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;

public class SellOneItemTest {
    @Test
    public void productFound() throws Exception {
        final Display display = new Display(new EnglishLanguageCzechRepublicMessageFormat(), new RenderTextInMemory());
        final Sale sale = new Sale(new Catalog(new HashMap<String, String>() {{
            put("12345", "CZK 780.00");
            put("23456", "CZK 1249.00");
        }}), display);

        sale.onBarcode("12345");

        Assert.assertEquals("CZK 780.00", display.getText());
    }

    @Test
    public void anotherProductFound() throws Exception {
        final Display display = new Display(new EnglishLanguageCzechRepublicMessageFormat(), new RenderTextInMemory());
        final Sale sale = new Sale(new Catalog(new HashMap<String, String>() {{
            put("12345", "CZK 780.00");
            put("23456", "CZK 1249.00");
        }}), display);

        sale.onBarcode("23456");

        Assert.assertEquals("CZK 1249.00", display.getText());
    }

    @Test
    public void productNotFound() throws Exception {
        final Display display = new Display(new EnglishLanguageCzechRepublicMessageFormat(), new RenderTextInMemory());
        final Sale sale = new Sale(new Catalog(new HashMap<String, String>() {{
            put("12345", "CZK 780.00");
            put("23456", "CZK 1249.00");
        }}), display);

        sale.onBarcode("99999");

        Assert.assertEquals("Product not found: 99999", display.getText());
    }

    @Test
    public void emptyBarcode() throws Exception {
        final Display display = new Display(new EnglishLanguageCzechRepublicMessageFormat(), new RenderTextInMemory());
        final Sale sale = new Sale(new Catalog(null), display);

        sale.onBarcode("");

        Assert.assertEquals("Scanning error: empty barcode", display.getText());
    }
}
