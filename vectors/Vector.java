package vectors;

import interfaces.DeepCloneable;
import vectorentries.VectorEntry;

public interface Vector<V extends VectorEntry<V>> extends DeepCloneable<Vector<V>>{
    /**
     * Applies a scalar multiple to the vector
     * @param multiple the scalar constant to apply
     */
    public void scalarMultiply(V multiple);
    /**
     * Retrieves the number of entries in the vector
     * @return the total number of entries
     */
    public int size();
}