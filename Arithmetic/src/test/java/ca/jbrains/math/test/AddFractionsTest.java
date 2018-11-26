package ca.jbrains.math.test;

import org.junit.Assert;
import org.junit.Test;

public class AddFractionsTest {
    @Test
    public void zeroPlusZero() throws Exception {
        Fraction sum = new Fraction(0).plus(new Fraction(0));
        Assert.assertEquals(0, sum.intValue());
    }

    @Test
    public void notZeroIntegerPlusZero() throws Exception {
        Fraction sum = new Fraction(1).plus(new Fraction(0));
        Assert.assertEquals(1, sum.intValue());
    }

    @Test
    public void zeroPlusNotZeroInteger() throws Exception {
        Fraction sum = new Fraction(0).plus(new Fraction(4));
        Assert.assertEquals(4, sum.intValue());
    }

    @Test
    public void nonZeroIntegers() throws Exception {
        Fraction sum = new Fraction(3).plus(new Fraction(6));
        Assert.assertEquals(9, sum.intValue());
    }

    @Test
    public void sameDenominators() throws Exception {
        Fraction sum = new Fraction(2, 7).plus(new Fraction(3, 7));
        Assert.assertEquals(5, sum.getNumerator());
        Assert.assertEquals(7, sum.getDenominator());
    }

    public static class Fraction {
        private int numerator;
        private int denominator;

        public Fraction(int integerValue) {
            this(integerValue, 1);
        }

        public Fraction(int numerator, int denominator) {
            this.numerator = numerator;
            this.denominator = denominator;
        }

        public Fraction plus(Fraction other) {
            if (this.denominator == 1 || other.denominator == 1)
                return new Fraction(this.numerator + other.numerator);
            else
                return new Fraction(this.numerator + other.numerator, this.denominator);
        }

        public int intValue() {
            return numerator;
        }

        public int getNumerator() {
            return numerator;
        }

        public int getDenominator() {
            return denominator;
        }
    }
}
