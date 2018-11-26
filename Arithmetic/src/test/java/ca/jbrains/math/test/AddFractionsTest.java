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
    public void notZeroPlusZero() throws Exception {
        Fraction sum = new Fraction(1).plus(new Fraction(0));
        Assert.assertEquals(1, sum.intValue());
    }

    @Test
    public void zeroPlusNotZero() throws Exception {
        Fraction sum = new Fraction(0).plus(new Fraction(4));
        Assert.assertEquals(4, sum.intValue());
    }

    @Test
    public void nonZeroIntegers() throws Exception {
        Fraction sum = new Fraction(3).plus(new Fraction(6));
        Assert.assertEquals(9, sum.intValue());
    }

    public static class Fraction {
        private final int integerValue;

        public Fraction(int integerValue) {
            this.integerValue = integerValue;
        }

        public Fraction plus(Fraction other) {
            if (other.integerValue == 0)
                return this;
            else if (this.integerValue == 0)
                return other;
            else
                return new Fraction(this.integerValue + other.integerValue);
        }

        public int intValue() {
            return integerValue;
        }
    }
}
