package ca.jbrains.pos.test;

import ca.jbrains.pos.Catalog;
import ca.jbrains.pos.Price;
import org.junit.Assert;
import org.junit.Test;

public abstract class FindPriceInCatalogContract {
    @Test
    public void productFound() throws Exception {
        Price matchingPrice = Price.euroCents(795);
        Assert.assertEquals(
                matchingPrice,
                catalogWith("::barcode::", matchingPrice)
                        .findPrice("::barcode::"));
    }

    @Test
    public void productNotFound() throws Exception {
        Assert.assertEquals(
                null,
                catalogWithout("::missing barcode::")
                        .findPrice("::missing barcode::"));
    }

    protected abstract Catalog catalogWith(String barcode, Price matchingPrice);
    protected abstract Catalog catalogWithout(String barcodeToAvoid);
}
