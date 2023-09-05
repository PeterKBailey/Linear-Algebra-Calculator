package vectorentries;
import interfaces.DeepCloneable;

public interface VectorEntry<T> extends DeepCloneable<T>{
    public void add(T e);
    public void subtract(T e);
    public void multiplyBy(T e);
    public void divideBy(T e);
    public boolean isEqualTo(T e);
    public boolean isZero();
    public T getReciprocal();
    public T getInverse();

    public static <T extends VectorEntry<T>> T add(T a, T b){
        T clone = a.deepClone();
        clone.add(b);
        return clone;
    }
    public static <T extends VectorEntry<T>> T subtract(T a, T b){
        T clone = a.deepClone();
        clone.subtract(b);
        return clone;
    }
    public static <T extends VectorEntry<T>> T multiply(T a, T b){
        T clone = a.deepClone();
        clone.multiplyBy(b);
        return clone;
    }
    public static <T extends VectorEntry<T>> T divide(T a, T b){
        T clone = a.deepClone();
        clone.divideBy(b);
        return clone;
    }
}

