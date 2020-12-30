import java.lang.reflect.Array;
import vectorentries.VectorEntry;

public class ColumnVector<T> implements Vector{
    private int numEntries;
    private T[] entries;

    public ColumnVector(Class<T> type, int n){
        numEntries = n;
        //ensure that the class type is a proper VectorEntry class
        if( !VectorEntry.class.isAssignableFrom(type) ){
            throw new IllegalArgumentException();

        }
        // if(type != Fraction.class || type != ComplexNumber.class){
        //     throw new IllegalArgumentException();
        // }

        //this should be fine because we ensure that T is implementing the VectorEntry interface
        @SuppressWarnings("unchecked") T[] entries = (T[]) Array.newInstance(type, numEntries);
        
    }
    public placeVal(T )
}