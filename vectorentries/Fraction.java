package vectorentries;

public class Fraction implements VectorEntry<Fraction>{
    int num;
    int den;
    public Fraction(int num, int den){
        this.num = num;
        this.den = den;
    }

    public Fraction add(Fraction a, Fraction b){
        a.verifyCompatibility(b);
        
        int num = b.num;
        int den = b.den;

        num*=a.den;
        den*=a.den;

        num+=a.num*b.den;

        Fraction result = new Fraction(num, den);
        result.reduce();
        return result;
    }
    
    public Fraction subtract(Fraction a, Fraction b){
        a.verifyCompatibility(b);
        int num = b.num;
        int den = b.den;

        num*=a.den;
        den*=a.den;

        num-=a.num*b.den;

        Fraction result = new Fraction(num, den);
        result.reduce();
        return result;        
    }

    public Fraction multiply(Fraction a, Fraction b){
        a.verifyCompatibility(b);
        int num = a.num * b.num;
        int den = a.den * b.den;

        Fraction result = new Fraction(num, den);
        result.reduce();
        return result;
    }
    
    public Fraction divide(Fraction a, Fraction b){
        a.verifyCompatibility(b);

        int num = a.num * b.den;
        int den = a.den * b.num;

        Fraction result = new Fraction(num, den);
        result.reduce();
        return result;
    }

    public Fraction copy(){
        return new Fraction(this.num, this.den);
    }

    public String toString(){
        return this.num + "/" + this.den;
    }

    public void verifyCompatibility(Fraction e){
        if(!e.getClass().equals(this.getClass())){
            throw new ArithmeticException("The provided value must be of the same type as the calling value!");
        }
    }

    //reduce the calling fraction to its "lowest" form
    public void reduce(){
        //if they're both negative, it divides out. If the den is negative and the num isn't then switch it
        if(num < 0 && den < 0 || den < 0 && num >= 0){
            num*=-1;
            den*=-1;
        }

        int gcd = gcd(Math.abs(num), Math.abs(den));
        num = num/gcd;
        den = den/gcd;
    }

    private int gcd(int a, int b){
        int gcd = 1;
        for(int n = Math.min(a, b); n>1;n--){
            if(a%n == 0){
                if(b%n == 0){
                    gcd = n;
                    break;
                }
            }
        }
        return gcd;
    }
}