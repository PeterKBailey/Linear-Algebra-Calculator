package vectorentries;

import java.math.BigInteger;

public class Fraction implements VectorEntry<Fraction>{
    private BigInteger num;
    private BigInteger den;


    public Fraction(){
        this.num = BigInteger.valueOf(0);
        this.den = BigInteger.valueOf(1);
    }

    public Fraction(BigInteger num, BigInteger den){
        if(den.equals(BigInteger.ZERO))
            throw new RuntimeException("Fraction can not have 0 for its denominator.");
        this.num = num;
        this.den = den;
        this.reduce();
    }

    public Fraction(long num, long den){
        this(BigInteger.valueOf(num), BigInteger.valueOf(den));
    }

    public Fraction(Fraction other){
        this(other.num, other.den);
    }

    public Fraction(String representation){
        this(Fraction.parseFraction(representation));
    }

    public void add(Fraction other){
        BigInteger tempNum = this.num.multiply(other.den);
        BigInteger tempOtherNum = other.num.multiply(this.den);

        this.den = this.den.multiply(other.den);
        this.num = tempNum.add(tempOtherNum);

        this.reduce();
    }

    public void subtract(Fraction other){
        BigInteger lcm = (this.den.multiply(other.den)).divide(this.gcd(this.den, other.den));

        BigInteger tempNum = this.num.multiply(this.den.divide(lcm));
        BigInteger tempOtherNum = other.num.multiply(other.den.divide(lcm));

        this.num = tempNum.subtract(tempOtherNum);
        this.den = lcm;
        this.reduce();
    }

    public void multiplyBy(Fraction other){
        this.num = this.num.multiply(other.num);
        this.den = this.den.multiply(other.den);
        this.reduce();
    }

    public void divideBy(Fraction other){
        this.num = this.num.multiply(other.den);
        this.den = this.den.multiply(other.num);
        this.reduce();
    }

    public boolean isEqualTo(Fraction other){
        return this.num == other.num && this.den == other.den;
    }

    public boolean isZero(){
        return this.num.equals(BigInteger.ZERO);
    }
    
    public Fraction getReciprocal(){
        if(this.isZero()) return new Fraction(0, 1);
        return VectorEntry.divide(new Fraction("1/1"), (this));
    }

    public Fraction getInverse(){
        Fraction inverse = new Fraction(this);
        inverse.num = inverse.num.negate();
        inverse.reduce();
        return inverse;
    }

    public String toString(){
        if(this.den.equals(BigInteger.ONE))
            return this.num + "";
        return this.num + "/" + this.den;
    }

    public Fraction deepClone(){
        return new Fraction(this);
    }

    /**
     * Throws a runtime exception if the string can not be parsed as a valid fraction
     * @param representation the string representation of the fraction
     */
    private static void verifyValidFraction(String representation){
        if(!representation.matches("\\d+\\/?\\d+"))
            throw new RuntimeException("Fraction must follow the format \"(int)/(int)\".");
    }

    public static Fraction parseFraction(String representation){
        Fraction.verifyValidFraction(representation);
        int barIndex = representation.indexOf('/');

        return new Fraction(
            new BigInteger(representation.substring(0, barIndex)), 
            new BigInteger(representation.substring(barIndex + 1))
        );
    }

    public void reduce(){
        if(this.num.equals(BigInteger.ZERO)){
            this.den = BigInteger.ONE;
            return;
        }

        boolean numBelow = num.compareTo(BigInteger.ZERO) == -1;
        boolean denBelow = den.compareTo(BigInteger.ZERO) == -1;
        BigInteger gcd = this.gcd(num, den);

        num = num.divide(gcd);
        den = den.divide(gcd);

        // If they're both negative, it divides out. 
        // If the den is negative and the num isn't then switch it
        if(numBelow&&denBelow||denBelow&&!numBelow){
            num = num.negate();
            den = den.negate();
        }
    }

    // Euclidean
    private BigInteger gcd(BigInteger a, BigInteger b){
        if(a.equals(BigInteger.ZERO)) return b;

        a = a.abs();
        b = b.abs();

        while(!b.equals(BigInteger.ZERO)){
            // a > b
            if(a.compareTo(b) == 1)
                a = a.subtract(b);
            else
                b = b.subtract(a);
        }
        return a;
    }
}