import java.util.Random;

import vectorentries.ComplexNumber;
import vectorentries.Fraction;
public class Main{
    public static void main(String[] args){
        Fraction f = new Fraction(1,3);
        System.out.println(f);

        ComplexNumber cn = new ComplexNumber("3/-2", "4/-1");
        ComplexNumber cn2 = new ComplexNumber("12/1", "-1/1");
        System.out.println(cn);
        System.out.println(cn2);
        cn.divideBy(cn2);
        System.out.println(cn);





        Random r = new Random();

        Fraction fractions[] = new Fraction[r.nextInt(5)+1];
        for(int i = 0; i < fractions.length; ++i){
            int num = r.nextInt(40) - 20;
            int den = r.nextInt(19) + 1;
            fractions[i] = new Fraction(num, den);
        }
        ColumnVector<Fraction> cv = new ColumnVector<Fraction>(fractions);
        System.out.println(cv);

        Fraction fractionMatrix[][] = new Fraction[r.nextInt(5)+1][r.nextInt(5)+1];
        for(Fraction[] row: fractionMatrix){
            for(int i = 0; i < row.length; ++i){
                int num = r.nextInt(40) - 20;
                int den = r.nextInt(19) + 1;
                row[i] = new Fraction(num, den);
            }
        }

        Matrix<Fraction> m = new Matrix<Fraction>(fractionMatrix);
        System.out.println(m);
    }
}