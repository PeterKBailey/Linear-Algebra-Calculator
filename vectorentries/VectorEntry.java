package vectorentries;
//public interface VectorEntry<T extends VectorEntry<T>> {

public interface VectorEntry<T> {
    public VectorEntry add(T a, T b);
    public VectorEntry subtract(T a, T b);
    public VectorEntry multiply(T a, T b);
    public VectorEntry divide(T a, T b);
    public VectorEntry copy();
    // public static VectorEntry toEntry(String s);

}