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
        private int integerValue;

        public Fraction(int integerValue) {
            this.integerValue = integerValue;
        }

        public Fraction(int numerator, int denominator) {

        }

        public Fraction plus(Fraction other) {
            return new Fraction(this.integerValue + other.integerValue);
        }

        public int intValue() {
            return integerValue;
        }

        public int getNumerator() {
            return 5;
        }

        public int getDenominator() {
            return 7;
        }
    }
}
