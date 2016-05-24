package btree;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chiemi on 2016/05/18.
 */
public class NonLeafNode extends Node {

    ArrayList<Integer> pointers = new ArrayList<Integer>();
    ArrayList<Integer> keys = new ArrayList<Integer>();

    public NonLeafNode(){
        this.type = this.TYPENONLEAF;
    }

    public int getSize(){
        return this.keys.size();
    }

    public int getKey(int index){
        return this.keys.get(index);
    }

    public int getPointer(int index){
        return this.pointers.get(index);
    }

    public int searchPosition(int key){
        for(int kid = 0;kid<this.getSize();kid++){
            int keyvalue = this.getKey(kid);
            if(keyvalue >=  key){
                return kid;
            }
        }
        return this.getSize();
    }

    public int searchChildNode(int key){
        return this.getPointer(searchPosition(key));
    }

    public void init(Integer pointer){
        this.pointers.clear();
        this.keys.clear();
        this.pointers.add(pointer);
    }

    public int insertEntry(Entry entry) throws IndexOutOfBoundsException {
        if(isOverflowed()){
            throw new IndexOutOfBoundsException();
        }
        int keypos = this.searchPosition(entry.key);
        this.insertKey(keypos, entry.key);
        this.insertPointer(keypos+1, entry.pointer);

        if(isOverflowed()){
            this.state = Node.STATEOVERFLOW;
        }
        return this.state;

    }

    public void moveKeys(int from, int until, NonLeafNode M){
        List<Integer> keylist = new ArrayList(this.keys.subList(from,until+1));
        M.keys.addAll(keylist);
        this.keys.subList(from,until+1).clear();
    }

    public void movePointers(int from, int until, NonLeafNode M){
        List<Integer> pntlist = new ArrayList(this.pointers.subList(from,until+1));
        M.pointers.addAll(pntlist);
        this.pointers.subList(from,until+1).clear();
    }

    private void insertKey(int index, int key){
        this.keys.add(index,key);
    }

    private void insertPointer(int index, int pointer){
        this.pointers.add(index,pointer);
    }

}
