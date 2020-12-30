package vectorentries;

public class Fraction implements VectorEntry{
    int num;
    int den;
    public Fraction(int num, int den){
        this.num = num;
        this.den = den;
    }
    public void add(VectorEntry e){

    }
    public void subtract(VectorEntry e){

    }
    public void multiply(VectorEntry e){

    }
    public void divideBy(VectorEntry e){

    }

    public String toString(){
        return this.num + "/" + this.den;
    }
}