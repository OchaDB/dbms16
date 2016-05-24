package btree;

/**
 * Created by chiemi on 2016/05/18.
 */
public class Record {
    int age;
    String name;

    public Record(String n,int a){
        this.age = a;
        this.name = n;
    }

    public int getKeyValue(){
        return age;
    }
}
