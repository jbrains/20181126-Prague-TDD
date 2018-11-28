package ca.jbrains.pos.test;

import org.junit.Assert;
import org.junit.Test;

public abstract class FindPriceInCatalogContract {
    @Test
    public void productFound() throws Exception {
        final Price matchingPrice = Price.koruny(2700);
        final Catalog catalog = catalogWith("::barcode::", matchingPrice);
        Assert.assertEquals(matchingPrice, catalog.findPrice("::barcode::"));
    }

    @Test
    public void productNotFound() throws Exception {
        final Catalog catalog = emptyCatalog();
        Assert.assertEquals(null, catalog.findPrice("::missing barcode::"));
    }

    protected abstract Catalog catalogWith(String barcode, Price matchingPrice);

    protected abstract Catalog emptyCatalog();
}
