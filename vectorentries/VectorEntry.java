package vectorentries;

public interface VectorEntry<T> {
    public void add(T e);
    public void subtract(T e);
    public void multiplyBy(T e);
    public void divideBy(T e);
}