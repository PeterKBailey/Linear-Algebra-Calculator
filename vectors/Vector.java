
package vectors;

import java.lang.reflect.Array;
import java.lang.reflect.Method;

import vectorentries.*;

public abstract class Vector<T>{
    private int numEntries;
    private T[] entries;

    private Class<T> tClass;

    public Vector(Class<T> c, int n){
        if( n <= 0 )
            throw new IllegalArgumentException("A vector needs at least one entry");

        numEntries = n;

        this.tClass = c;

        @SuppressWarnings("unchecked")
        final T[] entries = (T[]) Array.newInstance(c, numEntries);
        this.entries = entries;
    }

    public T get(int index){
        if(index >= numEntries || index < 0)
            throw new IndexOutOfBoundsException();
        return entries[index];
    }

    public void set(int index, T e){
        if(index >= numEntries || index < 0)
            throw new IndexOutOfBoundsException();
        entries[index] = e;
    }

    public int getNumEntries(){
        return numEntries;
    }

    // public void add(Vector v){
    //     verifyCompatibility(v);
    //     for(int i = 0; i < numEntries; i++){
    //         this.entries[i] = this.entries[0].add( this.entries[i], v.get(i));
    //         // this.entries[i] =  v.get(i);
    //     }
    // }
    
    public void subtract(Vector v){
        verifyCompatibility(v);
        for(int i = 0; i < numEntries; i++){

            // Class tClass = T.class;
            String className = tClass.getName();
            System.out.println(className);

            Object[] obj = {this.entries[0], v.get(i)};
            Method subtract;
            try{
                subtract =  tClass.getMethod("subtract", this.entries[0].getClass(), v.get(i).getClass());
                System.out.println(subtract.invoke(this.entries[i], obj));
            } catch(Exception e) {

            }

            // this.entries[i] = subtract.invoke(this.entries[i], obj);
            // this.entries[i] -= v.numEntries[i];
            // this.entries[i] = this.entries[0].subtract(this.entries[i], v.get(i));
        }
    }

    // public T innerProduct(Vector v, Vector u){
    //     v.verifyCompatibility(u);
    //     T sum = u.get(0).copy();
    //     for(int i = 1; i < v.getNumEntries(); i++){
    //         // sum += the multiplication of corresponding entries, using the proper class to do so
    //         sum = v.get(0).add( sum, v.get(0).multiply( v.get(i), u.get(i) ) );
    //     }
    //     // return sum;
    //     return sum;
    // }



    public void verifyCompatibility(Vector v){
        if(!v.getClass().equals(this.getClass())){
            throw new ArithmeticException("The provided vector must be of the same type as the calling vector!");
        }
        if(v.getNumEntries() != this.getNumEntries()){
            throw new ArithmeticException("The provided vector must be of the same size as the calling vector!");
        }
    }

    
/*
    // public VectorEntry get(int index);
    // public void set(int index, VectorEntry e);
    // public int getNumEntries();

    // public void add(Vector v);
    // public void subtract(Vector v);


    // public static VectorEntry innerProduct(Vector<VectorEntry> v, Vector<VectorEntry> u);

*/




    // public String norm();

    // public Vector<VectorEntry> orthogonalProjection(Vector<VectorEntry>[] basis);
    // public void orthogonalDecomposition(Vector<VectorEntry>[] basis);
    // public VectorEntry orthogonalCoefficient(int index, ComplexVector[] basis);

    // public static String distance(Vector<VectorEntry> v, Vector<VectorEntry> u);
    // public static void orthogonalise(Vector<VectorEntry>[] basis);
  
    // public static Vector toVector(String s);

}