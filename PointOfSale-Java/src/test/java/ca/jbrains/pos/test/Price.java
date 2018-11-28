package ca.jbrains.pos.test;

public class Price {
    public static Price koruny(int korunyValue) {
        return new Price();
    }

    @Override
    public String toString() {
        return "a Price";
    }
}
