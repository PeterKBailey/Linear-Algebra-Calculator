import java.util.ArrayList;

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

    private int longestEntry(){
        int maxSize = 0;
        for (V entry : vectorEntries) {
            int entrySize = entry.toString().length();
            if(entrySize > maxSize)
                maxSize = entrySize;
        }
        return maxSize;
    }

    public ColumnVector<V> toColumnVector(){
        return this.clone();
    }

    public ColumnVector<V> clone(){
        ArrayList<V> clonedEntries = new ArrayList<V>();
        for (V entry : vectorEntries) {
            clonedEntries.add(entry.deepClone());
        }
        return new ColumnVector<V>(clonedEntries);
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