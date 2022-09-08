import java.util.ArrayList;
import java.util.Arrays;

import vectorentries.Fraction;
import vectorentries.VectorEntry;

public class ColumnVector<V extends VectorEntry<V>> implements Vector<V>{
    private ArrayList<V> vectorEntries = new ArrayList<V>(3); 

    public ColumnVector(Iterable<V> entries){
        for (V vectorEntry : entries) {
            vectorEntries.add(vectorEntry);            
        }
    }
    
    public void scalarMultiply(V multiple){
        for (V entry : vectorEntries) {
            entry.multiplyBy(multiple);
        }
    }

    // for formatting purposes when printing to console
    private int longestEntry(){
        int maxSize = 0;
        for (V entry : vectorEntries) {
            int entrySize = entry.toString().length();
            if(entrySize > maxSize)
                maxSize = entrySize;
        }
        return maxSize;
    }

    // To prevent side effects of certain operations
    public ColumnVector<V> deepClone(){
        ArrayList<V> clonedEntries = new ArrayList<V>();
        for (V entry : this.vectorEntries) {
            clonedEntries.add(entry.deepClone());
        }
        return new ColumnVector<V>(clonedEntries);
    }

    public V dotProduct(ColumnVector<V> other){
        if(this.vectorEntries.size() < 1 || this.vectorEntries.size() != other.vectorEntries.size())
            throw new RuntimeException("Cannot find the dot product between these vectors!");

        // need a fresh generic to use
        V outVal = this.vectorEntries.get(0).deepClone();
        // set to 0
        outVal.subtract(outVal);
        for (int i = 0; i < this.vectorEntries.size(); ++i) {
            V mult = VectorEntry.multiply(this.vectorEntries.get(i), other.vectorEntries.get(i));
            outVal.add(mult);
        }
        return outVal;
    }


    public String toString(){
        String representation = "";
        int maxSize = longestEntry();
        for (V entry : vectorEntries) {
            representation += String.format("| %" + (maxSize) + "s |\n", entry);
        }
        return representation;
    }
}