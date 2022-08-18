package vectorentries;
import interfaces.DeepCloneable;

public interface VectorEntry<T> extends DeepCloneable<T>{
    public void add(T e);
    public void subtract(T e);
    public void multiplyBy(T e);
    public void divideBy(T e);
}