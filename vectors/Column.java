package vectors;

import vectorentries.VectorEntry;

public class Column<T> extends Vector<T>{

    public Column(Class<T> c, int n){
        super(c, n);
    }

}