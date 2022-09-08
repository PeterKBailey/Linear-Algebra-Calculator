package vectorentries;

public class Fraction implements VectorEntry<Fraction>{
    private int num;
    private int den;


    public Fraction(){
        this.num = 0;
        this.den = 1;
    }
    public Fraction(int num, int den){
        if(den == 0)
            throw new RuntimeException("Fraction can not have 0 for its denominator.");
        this.num = num;
        this.den = den;
        this.reduce();
    }
    public Fraction(String representation){
        this(Fraction.parseNum(representation), Fraction.parseDen(representation));
    }
    public Fraction(Fraction other){
        this(other.num, other.den);
    }



    public void add(Fraction other){
        long tempNum = this.num * (other.den);
        long tempOtherNum = other.num * (this.den);
        long den = this.den * other.den;

        tempNum += tempOtherNum;

        // if we've overflowed then throw an error
        if(Math.abs(tempNum) > 2147483647 || Math.abs(den) > 2147483647)
            throw new RuntimeException("Addition int overflow.");
        
        this.num = (int)tempNum;
        this.den = (int)den;
        this.reduce();
    }

    public void subtract(Fraction other){
        long lcm = (this.den * other.den) / this.gcd(this.den, other.den);

        long tempNum = this.num * (this.den/lcm);
        long tempOtherNum = other.num * (other.den/lcm);

        tempNum -= tempOtherNum;

        // if we've overflowed then throw an error
        if(Math.abs(tempNum) > 2147483647 || Math.abs(lcm) > 2147483647)
            throw new RuntimeException("Subtraction int overflow.");
        
        this.num = (int)tempNum;
        this.den = (int)lcm;
        this.reduce();
    }

    public void multiplyBy(Fraction other){
        long numProduct = this.num * other.num;
        long denProduct = this.den * other.den;
        if(Math.abs(numProduct) > 2147483647 || Math.abs(denProduct) > 2147483647)
            throw new RuntimeException("Multiplication int overflow");
        
        this.num = (int)numProduct;
        this.den = (int)denProduct;
        this.reduce();
    }

    public void divideBy(Fraction other){
        long numQuotient = this.num * other.den;
        long denQuotient = this.den * other.num;
        if(Math.abs(numQuotient) > 2147483647 || Math.abs(denQuotient) > 2147483647)
            throw new RuntimeException("Division int overflow");
        
        this.num = (int)numQuotient;
        this.den = (int)denQuotient;
        this.reduce();
    }

    public String toString(){
        if(this.den == 1)
            return this.num + "";
        return this.num + "/" + this.den;
    }

    public Fraction deepClone(){
        return new Fraction(this);
    }

    private static void verifyValidFraction(String representation){
        int barIndex = representation.indexOf('/');
        if(barIndex == 0 || barIndex == representation.length() -1)
            throw new RuntimeException("Invalid Fraction: Must be formatted as 'num/den'");
    }
    private static int parseNum(String representation){
        int barIndex = representation.indexOf('/');
        if(barIndex == -1)
            barIndex = representation.length();
        return Integer.parseInt(representation.substring(0, barIndex));
    }
    private static int parseDen(String representation){
        int barIndex = representation.indexOf('/');
        return barIndex == -1 ? 1 : Integer.parseInt(representation.substring(barIndex+1));
    }

    public static Fraction parseFraction(String representation){
        Fraction.verifyValidFraction(representation);
        return new Fraction(Fraction.parseNum(representation), Fraction.parseDen(representation));
    }

    public void reduce(){
        if(this.num == 0){
            this.den = 1;
            return;
        }

        boolean numBelow = num<0;
        boolean denBelow = den<0;
        int gcd = this.gcd(num, den);

        num = num/gcd;
        den = den/gcd;

        // If they're both negative, it divides out. 
        // If the den is negative and the num isn't then switch it
        if(numBelow&&denBelow||denBelow&&!numBelow){
            num*=-1;
            den*=-1;
        }
    }

    // Euclidean
    private int gcd(int a, int b){
        if(a == 0) return b;

        a = Math.abs(a);
        b = Math.abs(b);

        while(b!=0){
            if(a > b)
                a -= b;
            else
                b -= a;
        }
        return a;
    }

    // Static arithmetic functions for ease of use in multi-step equations

    /*public static Fraction add(Fraction a, Fraction b){
        Fraction ret = new Fraction(a.num, a.den);
        ret.add(b);
        return ret;
    }

    public static Fraction subtract(Fraction a, Fraction b){
        Fraction ret = new Fraction(a.num, a.den);
        ret.subtract(b);
        return ret;
    }    
    
    public static Fraction multiply(Fraction a, Fraction b){
        Fraction ret = new Fraction(a.num, a.den);
        ret.multiplyBy(b);
        return ret;
    }
    
    public static Fraction divide(Fraction a, Fraction b){
        Fraction ret = new Fraction(a.num, a.den);
        ret.divideBy(b);
        return ret;
    }*/
}