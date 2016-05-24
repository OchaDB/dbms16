package btree;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chiemi on 2016/05/18.
 */
public class LeafNode extends Node {
    ArrayList<Record> records = new ArrayList<Record>();

    public LeafNode(){
        this.type = this.TYPELEAF;
    }

    public Record getRecord(int index){
        return records.get(index);
    }

    public int getSize(){
        return this.records.size();
    }

    public int insertRecord(int index, Record record) throws IndexOutOfBoundsException {
        if(this.isOverflowed()){
            throw new IndexOutOfBoundsException();
        }
        records.add(index,record);
        if(this.isOverflowed()){
            this.state = Node.STATEOVERFLOW;
            return Node.STATEOVERFLOW;
        }
        else{
            this.state = Node.STATENORMAL;
            return Node.STATENORMAL;
        }
    }

    public int insertRecord(Record record) throws IndexOutOfBoundsException {
        int rno = 0;
        for(rno = 0; rno<this.getSize(); rno++){
            Record r = this.getRecord(rno);
            if(r.getKeyValue() > record.getKeyValue()){
                break;
            }
        }
        return this.insertRecord(rno, record);
    }

    public void moveRecords(int from, int until, LeafNode M){
        List<Record> rdlist = new ArrayList(this.records.subList(from,until+1));
        M.records.addAll(rdlist);
        this.records.subList(from,until+1).clear();
    }

    public int searchPosition(int query){
        int rno = 0;
        for(rno = 0; rno<this.getSize(); rno++){
            Record r = this.getRecord(rno);
            if(r.getKeyValue() >= query){
                break;
            }
        }
        return rno;
    }

}
