package ca.jbrains.pos;

public class Display {
    private final EnglishLanguageCzechRepublicMessageFormat englishLanguageCzechRepublicMessageFormat = new EnglishLanguageCzechRepublicMessageFormat();
    private final RenderTextInMemory renderTextInMemory = new RenderTextInMemory();

    public String getText() {
        return renderTextInMemory.getText();
    }

    public void displayProductNotFoundMessage(String barcode) {
        this.renderTextInMemory.setText(englishLanguageCzechRepublicMessageFormat.formatProductNotFoundMessage(barcode));
    }

    public void displayPrice(String priceAsText) {
        this.renderTextInMemory.setText(englishLanguageCzechRepublicMessageFormat.formatProductFoundMessage(priceAsText));
    }

    public void displayScannedEmptyBarcodeMessage() {
        this.renderTextInMemory.setText(englishLanguageCzechRepublicMessageFormat.formatScannedEmptyBarcodeMessage());
    }
}
