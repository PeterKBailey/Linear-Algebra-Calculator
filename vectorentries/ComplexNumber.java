package vectorentries;

public class ComplexNumber implements VectorEntry<ComplexNumber>{
    private Fraction realPart;
    private Fraction imaginaryPart;

    public ComplexNumber(){
        this.realPart = new Fraction();
        this.imaginaryPart = new Fraction();
    }

    public ComplexNumber(Fraction realPart, Fraction imaginaryPart){
        this.realPart = realPart;
        this.imaginaryPart = imaginaryPart;
    }

    public ComplexNumber(String realPartRepStr, String imaginaryPartStr){
        this.realPart = Fraction.parseFraction(realPartRepStr);
        this.imaginaryPart = Fraction.parseFraction(imaginaryPartStr);
    }

    public ComplexNumber(ComplexNumber other){
        this.realPart = other.realPart.deepClone();
        this.imaginaryPart = other.imaginaryPart.deepClone();
    }

    public void add(ComplexNumber other){
        this.realPart.add(other.realPart);
        this.imaginaryPart.add(other.imaginaryPart);
    }

    public void subtract(ComplexNumber other){
        this.realPart.subtract(other.realPart);
        this.imaginaryPart.subtract(other.imaginaryPart);
    }

    // (a + bi)(c + di) = ac + adi + bci + bdi^2 = ac - bd + (ad + bc)i
    // because i^2 = -1
    public void multiplyBy(ComplexNumber other){
        Fraction realPart = VectorEntry.multiply(this.realPart, other.realPart);
        realPart.subtract(VectorEntry.multiply(this.imaginaryPart, other.imaginaryPart));

        Fraction imaginaryPart = VectorEntry.multiply(this.realPart, other.imaginaryPart);
        imaginaryPart.add(VectorEntry.multiply(this.imaginaryPart, other.realPart));

        this.realPart = realPart;
        this.imaginaryPart = imaginaryPart;
    }

    // Multiply num and den by den's conjugate
    // den will be other.realPart^2 + 1
    // num will be this.realPart/(other.realPart^2 + 1) + this.imaginaryPart/(other.realPart^2 + 1)i
    public void divideBy(ComplexNumber other){
        ComplexNumber numerator = new ComplexNumber(this.realPart, this.imaginaryPart);
        numerator.multiplyBy(other.conjugateValue());

        ComplexNumber denominator = new ComplexNumber(other.realPart, other.imaginaryPart);
        denominator.multiplyBy(other.conjugateValue());

        numerator.realPart.divideBy(denominator.realPart);
        numerator.imaginaryPart.divideBy(denominator.realPart);

        this.realPart = numerator.realPart;
        this.imaginaryPart = numerator.imaginaryPart;
    }

    public boolean isEqualTo(ComplexNumber other){
        return this.realPart.isEqualTo(other.realPart) && this.imaginaryPart.isEqualTo(other.imaginaryPart);
    }

    public boolean isZero(){
        return this.realPart.isZero() && this.imaginaryPart.isZero();
    }

    public ComplexNumber getReciprocal(){
        return VectorEntry.divide(new ComplexNumber("1/1", "0/1"), (this));
    }

    public ComplexNumber getInverse(){
        ComplexNumber inverse = new ComplexNumber(this);
        inverse.realPart = inverse.realPart.getInverse();
        inverse.imaginaryPart = inverse.imaginaryPart.getInverse();
        return inverse;
    }

    public void conjugate(){
        Fraction f = new Fraction (-1,1);
        this.imaginaryPart.multiplyBy(f);
    }

    public ComplexNumber conjugateValue(){
        Fraction f = new Fraction (-1,1);
        Fraction real = new Fraction(this.realPart);
        ComplexNumber conjugate = new ComplexNumber(real, VectorEntry.multiply(this.imaginaryPart,f));
        return conjugate;
    }

    public ComplexNumber deepClone(){
        return new ComplexNumber(this);
    }

    public String toString(){
        return realPart.toString() + " + " + imaginaryPart.toString() + "i";
    }

}