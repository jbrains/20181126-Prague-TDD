package ca.jbrains.pos;

public class Sale {
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
