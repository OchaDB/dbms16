package btree;

/**
 * Created by chiemi on 2016/05/18.
 */
public class Entry {
    public int pointer;
    public int key;
    public Record record;

    public Entry(Record r){
        this.record = r;
        this.key = r.getKeyValue();
        this.pointer = -1;
    }

    public Entry(int key,int pointer){
        this.key = key;
        this.pointer = pointer;
    }
}
