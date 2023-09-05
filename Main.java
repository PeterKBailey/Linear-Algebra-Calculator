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

        LinearVector<Fraction> cv1 = new LinearVector<>(fractions);
        LinearVector<Fraction> cv2 = new LinearVector<>(fractions2);

        System.out.println("The first vector:");
        System.out.println(cv1);
        System.out.println("The second vector:");
        System.out.println(cv2);
        System.out.println("Their dot product:");
        System.out.println(cv1.dotProduct(cv2));

        int numRows = r.nextInt(2)+3;
        numRows = 3;
        int numCols = r.nextInt(2)+3;
        numCols = 4;
        ArrayList<LinearVector<Fraction>> fractionColumns = new ArrayList<LinearVector<Fraction>>(numCols);

        /*
        // generate some new column vectors
        for(int i = 0; i < numCols; ++i){
            // come up with values
            LinearVector<Fraction> col = new LinearVector<Fraction>();
            for(int j = 0; j < numRows; ++j){
                int num = r.nextInt(40) - 20;
                int den = r.nextInt(19) + 1;
                col.add(new Fraction(num, den));
            }
            // System.out.println(new LinearVector<>(col));
            fractionColumns.add(col);
        }
*/
        int[] values = {1,-2,5,-1,3,0,-1,2,-3,0,-2,4};
        // int[] values = {1,0,0,0,1,0,2,-7,0,0,0,1};
        int index = 0;
        for(int i = 0; i < numCols; ++i){
            // come up with values
            LinearVector<Fraction> col = new LinearVector<Fraction>();
            for(int j = 0; j < numRows; ++j){
                col.add(new Fraction(values[index], 1));
                index++;
            }
            fractionColumns.add(col);
        }

        Matrix<Fraction> m = new Matrix<Fraction>(fractionColumns, true);
        // System.out.println(m);
        m.scalarMultiply(new Fraction(2, 1));
        // System.out.println(m);
        m.rowSwap(0, 2);
        System.out.println(m);
        var m2 = m.deepClone();
        m.rowReduce();
        System.out.println(m);
        var solns = m2.getLinearEquationSolnSet();
        System.out.println(solns);
    }
}