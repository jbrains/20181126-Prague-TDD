package ca.jbrains.pos.test;

import ca.jbrains.pos.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;

public class SellOneItemTest {

    private RenderTextInMemory renderTextInMemory;

    @Before
    public void setUp() throws Exception {
        renderTextInMemory = new RenderTextInMemory();
    }

    @Test
    public void productFound() throws Exception {
        final Display display = new Display(new EnglishLanguageCzechRepublicMessageFormat(), renderTextInMemory);
        final Sale sale = new Sale(new Catalog(new HashMap<String, String>() {{
            put("12345", "CZK 780.00");
            put("23456", "CZK 1249.00");
        }}), display);

        sale.onBarcode("12345");

        Assert.assertEquals("CZK 780.00", renderTextInMemory.getText());
    }

    @Test
    public void anotherProductFound() throws Exception {
        final Display display = new Display(new EnglishLanguageCzechRepublicMessageFormat(), renderTextInMemory);
        final Sale sale = new Sale(new Catalog(new HashMap<String, String>() {{
            put("12345", "CZK 780.00");
            put("23456", "CZK 1249.00");
        }}), display);

        sale.onBarcode("23456");

        Assert.assertEquals("CZK 1249.00", renderTextInMemory.getText());
    }

    @Test
    public void productNotFound() throws Exception {
        final Display display = new Display(new EnglishLanguageCzechRepublicMessageFormat(), renderTextInMemory);
        final Sale sale = new Sale(new Catalog(new HashMap<String, String>() {{
            put("12345", "CZK 780.00");
            put("23456", "CZK 1249.00");
        }}), display);

        sale.onBarcode("99999");

        Assert.assertEquals("Product not found: 99999", renderTextInMemory.getText());
    }

    @Test
    public void emptyBarcode() throws Exception {
        final Display display = new Display(new EnglishLanguageCzechRepublicMessageFormat(), renderTextInMemory);
        final Sale sale = new Sale(new Catalog(null), display);

        sale.onBarcode("");

        Assert.assertEquals("Scanning error: empty barcode", renderTextInMemory.getText());
    }
}
