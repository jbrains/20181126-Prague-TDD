package ca.jbrains.pos;

public class Display {
    private final EnglishLanguageCzechRepublicMessageFormat englishLanguageCzechRepublicMessageFormat = new EnglishLanguageCzechRepublicMessageFormat();
    private String text;

    public String getText() {
        return text;
    }

    public void displayProductNotFoundMessage(String barcode) {
        this.text = englishLanguageCzechRepublicMessageFormat.formatProductNotFoundMessage(barcode);
    }

    public void displayPrice(String priceAsText) {
        this.text = englishLanguageCzechRepublicMessageFormat.formatProductFoundMessage(priceAsText);
    }

    public void displayScannedEmptyBarcodeMessage() {
        this.text = englishLanguageCzechRepublicMessageFormat.formatScannedEmptyBarcodeMessage();
    }
}
