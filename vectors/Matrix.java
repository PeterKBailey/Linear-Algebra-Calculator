package vectors;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import vectorentries.VectorEntry;

public class Matrix<V extends VectorEntry<V>> implements Vector<V>{
    private ArrayList<LinearVector<V>> rows = new ArrayList<LinearVector<V>>(); 
    private ArrayList<LinearVector<V>> columns = new ArrayList<LinearVector<V>>(); 

    /**
     * Constructor, a matrix is a list of columns or a list of rows
     * @param vectors each row or column vector
     * @param isColumns true if the provided vectors are columns, false if rows
     */
    public Matrix(List<LinearVector<V>> vectors, boolean isColumns){
        // we can receive a list of rows or a list of vectors, we set up both lists from this
        ArrayList<LinearVector<V>> givenVectors;
        ArrayList<LinearVector<V>> crossVectors;
        // get the reference to the proper list based on which we were provided
        if(isColumns){
            givenVectors = this.columns;
            crossVectors = this.rows;
        } else {
            givenVectors = this.rows;
            crossVectors = this.columns;
        }

        // comments will work with the example that we are given a list of columns

        // loop over the length of the given vectors
        // for example, over the height of the columns
        for(int i = 0; i<vectors.get(0).size(); i++){
            // the cross vector will be the length of how many given vectors there are
            // for example, the each row has as many entries as there are columns
            var crossVector = new LinearVector<V>();

            // loop over every given vector
            // for example, over each column
            for(int j = 0; j<vectors.size(); j++){
                // the given vectors are populated along the length of the cross vectors
                // this means on the first pass each vector will need to be initialized
                // the cross vectors are filled entirely and then inserted
                LinearVector<V> vector;
                if(j < givenVectors.size()){
                    vector = givenVectors.get(j);
                }
                else {
                    givenVectors.add(new LinearVector<V>());
                    vector = givenVectors.get(j);
                }
                
                // the rows and columns need to reference the same entry object
                V entry = vectors.get(j).get(i).deepClone();
                // the given vectors will all have length i by the end of this internal loop
                vector.add(entry);
                // this single cross vector will have length vectors.size() by the end of this internal loop
                crossVector.add(entry);
            }
            crossVectors.add(crossVector);
        }
    }

    public V get(int columnIndex, int rowIndex){
        return columns.get(columnIndex).get(rowIndex);
    }

    public V set(int columnIndex, int rowIndex, V element){
        return columns.get(columnIndex).set(rowIndex, element);
    }
    
    public void scalarMultiply(V multiple){
        for (var column : columns) {
            column.scalarMultiply(multiple);
        }
    }

    public int size(){
        return columns.size()*rows.size();
    }

    /**
     * 
     * @return
     */
    private int longestEntrySize(){
        int maxSize = 0;
        for (var col: columns) {
            int colSize = col.longestEntrySize();
            if(colSize > maxSize){
                maxSize = colSize;
            }
        }
        return maxSize;
    }

    // row operations 
    /**
     * Swaps row A and row B in the matrix, zero-indexed
     * @param rowIndexA the first row selection
     * @param rowIndexB the second row selection
     */
    public void rowSwap(int rowIndexA, int rowIndexB){
        // swap the 2 values in each column
        for(var column: columns){
            V temp = column.set(rowIndexA, column.get(rowIndexB));
            column.set(rowIndexB, temp);
        }
        // swap the 2 rows
        var temp = rows.set(rowIndexA, rows.get(rowIndexB));
        rows.set(rowIndexB, temp);
    }

    /**
     * Multiply a row by a nonzero scalar
     * @param rowIndex the row being multiplied by a scalar, zero-indexed
     * @param scalarMultiple the scalar multiple being applied to the row
     */
    public void rowScalarMultiply(int rowIndex, V scalarMultiple){
        if(scalarMultiple.isEqualTo(scalarMultiple.getZero())) throw new RuntimeException("The scalar multiple must be non zero!");
        // multiply the values in the entire row
        // rows and columns point to the same VectorEntry so only need to do this operation once
        for(var entry: rows.get(rowIndex)){
            entry.multiplyBy(scalarMultiple);
        }
    }

    /**
     * Adds a multiple of row B to row A, zero-indexed
     * @param rowIndexA row A, the row being added to
     * @param scalarMultiple the multiple being applied to row B before it gets added to A
     * @param rowIndexB row B, the row being scalar multiplied and getting added to row A
     */
    public void rowAddMultiple(int rowIndexA, V scalarMultiple, int rowIndexB){
        // multiply the values in the entire row
        for(int i = 0; i < columns.size(); ++i){
            // apply the multiple against entry in B
            var multiple = VectorEntry.multiply(scalarMultiple, rows.get(rowIndexB).get(i));
            // add it to the entry in A
            // rows and columns point to the same VectorEntry so only need to do this operation once
            rows.get(rowIndexA).get(i).add(multiple);
        }
    }

    public String toString(){
        String representation = "";
        int maxSize = longestEntrySize();
        // for each row
        for (var row: rows){
            representation += "| ";
            // for each column in the row
            for(var entry: row){
                representation += String.format("%" + (maxSize) + "s ", entry);
            }
            representation += "|\n";
        }
        return representation;
    }


    /**
     * Reduce the matrix to RREF
     */
    public void rowReduce(){
        for(int colIndex = 0, rowIndex = 0; colIndex < columns.size(); ++colIndex){
            // only move on to next row if we can make a pivot in the current one
            if(makePivotAt(colIndex, rowIndex))
                rowIndex++;
        }
    }

    /**
     * Makes a pivot in the specified column and row if possible, looks only from this row and below
     * @param pivotColumnIndex the index of the column requiring a pivot
     * @param pivotRowIndex the index of the row requiring a pivot
     * @return true if it was possible to place a pivot in this position, false otherwise
     */
    private boolean makePivotAt(int pivotColumnIndex, int pivotRowIndex){
        // starting from the pivot row, loop over each row
        for(int i = pivotRowIndex; i < rows.size(); ++i){
            var entry = rows.get(i).get(pivotColumnIndex);
            // find first row with a value in the column
            if(!entry.isEqualTo(entry.getZero())){
                // row i has a value for this column, swap it up to row we want pivot in
                if(i != pivotRowIndex)
                    rowSwap(pivotRowIndex, i);
                rowScalarMultiply(pivotRowIndex, rows.get(pivotRowIndex).get(pivotColumnIndex).getReciprocal());
                // subtract it from all other rows
                for(int j = 0; j < rows.size(); ++j){
                    if(j == pivotRowIndex) continue;
                    // determine the ratio between the vals in both rows
                    V multiple = rows.get(j).get(pivotColumnIndex).getNegation();
                    rowAddMultiple(j, multiple, pivotRowIndex);
                }
                return true;
            }
        }
        return false;
    }

    public Map<String, String> getLinearEquationSolnSet(){
        var solutions = new HashMap<String, String>();
        var duplicateMatrix = this.deepClone();
        duplicateMatrix.rowReduce();
        // loop over each row to build up the solution set
        for(int rowIndex = 0; rowIndex < duplicateMatrix.rows.size(); ++rowIndex){    
            int pivot = -1;
            String equation = duplicateMatrix.rows.get(rowIndex).get(duplicateMatrix.columns.size() -1).toString();
            // for each entry along the row
            for(int colIndex = 0; colIndex < duplicateMatrix.columns.size() - 1; ++colIndex){
                var entry = duplicateMatrix.columns.get(colIndex).get(rowIndex);

                // if this is the pivot
                if(entry.isEqualTo(entry.getOne()) && pivot == -1){
                    pivot = colIndex + 1;
                }
                // otherwise if non zero add to the eqn
                else if(!entry.isEqualTo(entry.getZero())){
                    String freeVar = "x" + (colIndex+1);
                    equation += " + " + entry.getNegation() + freeVar;
                    solutions.put(freeVar, "free");
                }
                
            }
            if(pivot == -1){
                solutions.clear();
                solutions.put("Row" + (rowIndex + 1), "inconsistent");
                return solutions; 
            }
            solutions.put("x" + pivot, equation);     
        }
        return solutions;
    }


    public Matrix<V> deepClone(){
        return new Matrix<V>(columns, true);
    }
}