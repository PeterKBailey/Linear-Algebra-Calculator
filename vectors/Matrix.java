package vectors;

import java.util.ArrayList;

import vectorentries.VectorEntry;

//implements.?
public class Matrix<V extends VectorEntry<V>> implements Vector<V>{
    // Problem: The rows need to all have the same length
    private ArrayList<ArrayList<V>> vectorEntries = new ArrayList<ArrayList<V>>(); 

    // rows by columns
    public Matrix(V[][] entries){
        for(V[] row : entries) {
            ArrayList<V> rowList = new ArrayList<V>(row.length);
            vectorEntries.add(rowList);
            for(V entry : row){
                rowList.add(entry);            
            }
        }
    }

    
    public void scalarMultiply(V multiple){
        for (ArrayList<V> row : vectorEntries) {
            for(V entry : row ){
                entry.multiplyBy(multiple);
            }
        }
    }

    private int longestEntry(){
        int maxSize = 0;
        for (ArrayList<V> row : vectorEntries) {
            for(V entry : row ){
                int entrySize = entry.toString().length();
                if(entrySize > maxSize)
                    maxSize = entrySize;
            }
        }
        return maxSize;
    }


    public String toString(){
        String representation = "";
        int maxSize = longestEntry();
        for (ArrayList<V> row : vectorEntries) {
            representation += "| ";
            for(V entry : row ){
                representation += String.format("%" + (maxSize) + "s ", entry);
            }
            representation += "|\n";
        }
        return representation;
    }
}