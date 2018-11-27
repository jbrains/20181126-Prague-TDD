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
        if ("".equals(barcode)) {
            renderTextInMemory.renderText(englishLanguageCzechRepublicMessageFormat.formatScannedEmptyBarcodeMessage());
            return;
        }

        final String priceAsText = catalog.findPrice(barcode);
        if (priceAsText == null) {
            renderTextInMemory.renderText(englishLanguageCzechRepublicMessageFormat.formatProductNotFoundMessage(barcode));
        } else {
            renderTextInMemory.renderText(englishLanguageCzechRepublicMessageFormat.formatProductFoundMessage(priceAsText));
        }
    }
}
