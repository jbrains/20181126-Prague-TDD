package ca.jbrains.pos;

public class Sale {
    private final Catalog catalog;
    private final RenderTextInMemory renderTextInMemory;
    private final EnglishLanguageCzechRepublicMessageFormat englishLanguageCzechRepublicMessageFormat;

    public Sale(Catalog catalog, final RenderTextInMemory renderTextInMemory, final EnglishLanguageCzechRepublicMessageFormat englishLanguageCzechRepublicMessageFormat) {
        this.catalog = catalog;
        this.renderTextInMemory = renderTextInMemory;
        this.englishLanguageCzechRepublicMessageFormat = englishLanguageCzechRepublicMessageFormat;
    }

    public void onBarcode(String barcode) {
        renderTextInMemory.renderText(handleRequest(barcode));
    }

    public String handleRequest(String barcode) {
        if ("".equals(barcode)) {
            return englishLanguageCzechRepublicMessageFormat.formatScannedEmptyBarcodeMessage();
        }

        final String priceAsText = catalog.findPrice(barcode);
        if (priceAsText == null) {
            return englishLanguageCzechRepublicMessageFormat.formatProductNotFoundMessage(barcode);
        } else {
            return englishLanguageCzechRepublicMessageFormat.formatProductFoundMessage(priceAsText);
        }
    }
}
