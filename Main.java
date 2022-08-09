import vectorentries.ComplexNumber;
import vectorentries.Fraction;
public class Main{
    public static void main(String[] args){
        Fraction f = new Fraction(1,3);
        System.out.println(f);

        ComplexNumber cn = new ComplexNumber("3/1", "4/1");
        ComplexNumber cn2 = new ComplexNumber("12/1", "-1/1");
        System.out.println(cn);
        System.out.println(cn2);
        cn.divideBy(cn2);
        System.out.println(cn);
    }
}