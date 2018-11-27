package ca.jbrains.pos;

public class Display {
    public final EnglishLanguageCzechRepublicMessageFormat englishLanguageCzechRepublicMessageFormat;
    public final RenderTextInMemory renderTextInMemory;

    public Display(final EnglishLanguageCzechRepublicMessageFormat englishLanguageCzechRepublicMessageFormat, final RenderTextInMemory renderTextInMemory) {
        this.englishLanguageCzechRepublicMessageFormat = englishLanguageCzechRepublicMessageFormat;
        this.renderTextInMemory = renderTextInMemory;
    }

    public void displayProductNotFoundMessage(String barcode) {
        this.renderTextInMemory.renderText(englishLanguageCzechRepublicMessageFormat.formatProductNotFoundMessage(barcode));
    }

    public void displayPrice(String priceAsText) {
        this.renderTextInMemory.renderText(englishLanguageCzechRepublicMessageFormat.formatProductFoundMessage(priceAsText));
    }

    public void displayScannedEmptyBarcodeMessage() {
        this.renderTextInMemory.renderText(englishLanguageCzechRepublicMessageFormat.formatScannedEmptyBarcodeMessage());
    }
}
