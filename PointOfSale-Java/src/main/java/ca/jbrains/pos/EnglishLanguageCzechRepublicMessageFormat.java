package ca.jbrains.pos;

public class EnglishLanguageCzechRepublicMessageFormat {
    public EnglishLanguageCzechRepublicMessageFormat() {
    }

    public String formatProductNotFoundMessage(String barcode) {
        return String.format("Product not found: %s", barcode);
    }

    public String formatProductFoundMessage(String priceAsText) {
        return priceAsText;
    }

    public String formatScannedEmptyBarcodeMessage() {
        return "Scanning error: empty barcode";
    }
}