package ca.jbrains.pos;

public class Display {
    private String text;

    public String getText() {
        return text;
    }

    public void displayProductNotFoundMessage(String barcode) {
        this.text = String.format("Product not found: %s", barcode);
    }

    public void displayPrice(String priceAsText) {
        this.text = priceAsText;
    }

    public void displayScannedEmptyBarcodeMessage() {
        this.text = "Scanning error: empty barcode";
    }
}
