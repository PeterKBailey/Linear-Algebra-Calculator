import java.lang.reflect.Array;
import vectorentries.VectorEntry;

public class ColumnVector<VectorEntry> implements Vector{
    private int numEntries;
    private VectorEntry[] entries;

    public ColumnVector(int n){
        numEntries = n;
    }
    
}