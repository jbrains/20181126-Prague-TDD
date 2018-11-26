package ca.jbrains.math.test;

import org.junit.Assert;
import org.junit.Test;

public class AddFractionsTest {
    @Test
    public void zeroPlusZero() throws Exception {
        Fraction sum = new Fraction(0).plus(new Fraction(0));
        Assert.assertEquals(new Fraction(0), sum);
    }

    @Test
    public void notZeroIntegerPlusZero() throws Exception {
        Fraction sum = new Fraction(1).plus(new Fraction(0));
        Assert.assertEquals(new Fraction(1), sum);
    }

    @Test
    public void zeroPlusNotZeroInteger() throws Exception {
        Fraction sum = new Fraction(0).plus(new Fraction(4));
        Assert.assertEquals(new Fraction(4), sum);
    }

    @Test
    public void nonZeroIntegers() throws Exception {
        Fraction sum = new Fraction(3).plus(new Fraction(6));
        Assert.assertEquals(new Fraction(9), sum);
    }

    @Test
    public void sameDenominators() throws Exception {
        Fraction sum = new Fraction(2, 7).plus(new Fraction(3, 7));
        Assert.assertEquals(new Fraction(5, 7), sum);
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

        @Override
        public boolean equals(Object other) {
            if (other instanceof Fraction) {
                Fraction that = (Fraction) other;
                return this.numerator * that.denominator
                        == that.numerator * this.denominator;
            } else {
                return false;
            }
        }

        @Override
        public int hashCode() {
            return 762;
        }
    }
}
