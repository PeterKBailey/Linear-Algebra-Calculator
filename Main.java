import vectorentries.*;
import vectors.*;

public class Main{
    public static void main(String[] args){
        Fraction f = new Fraction(1,3);
        System.out.println(f);
        Column<Fraction> v = new Column<Fraction>(Fraction.class, 2);
        v.set(0,f);
        System.out.println(v.get(0));

        // v.add(v);
        // v.add(new Matrix(2));
    }
}