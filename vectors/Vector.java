package vectors;

import vectorentries.VectorEntry;

public interface Vector<V extends VectorEntry<V>>{
    public void scalarMultiply(V multiple);
}