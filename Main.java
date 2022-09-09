import java.util.ArrayList;
import java.util.Random;

import vectorentries.*;
import vectors.*;
public class Main{
    public static void main(String[] args){
        Fraction f = new Fraction(3,2);
        Fraction f2 = new Fraction(4,3);
        System.out.println(VectorEntry.add(f, f2));






        Random r = new Random();
        int vecSize = r.nextInt(5)+1;
        ArrayList<Fraction> fractions = new ArrayList<Fraction>(vecSize);
        ArrayList<Fraction> fractions2 = new ArrayList<Fraction>(vecSize);

        for(int i = 0; i < vecSize; ++i){
            int num = r.nextInt(40) - 20;
            int den = r.nextInt(19) + 1;
            fractions.add(new Fraction(num, den));

            num = r.nextInt(40) - 20;
            den = r.nextInt(19) + 1;
            fractions2.add(new Fraction(num, den));
        }

        ColumnVector<Fraction> cv1 = new ColumnVector<>(fractions);
        ColumnVector<Fraction> cv2 = new ColumnVector<>(fractions2);

        System.out.println("The first vector:");
        System.out.println(cv1);
        System.out.println("The second vector:");
        System.out.println(cv2);
        System.out.println("Their dot product:");
        System.out.println(cv1.dotProduct(cv2));

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