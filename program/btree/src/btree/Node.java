package btree;

/**
 * Created by chiemi on 2016/05/18.
 */
public class Node {
    int nodeNo;
    int type; //0: NonLeaf, 1:Leaf
    int state = Node.STATENORMAL;
    int nofEntries;
    int order;
    boolean root = false;

    public Integer prevNode = null;
    public Integer nextNode = null;

    public static int ORDER = 2;

    public static final int TYPENONLEAF = 0;
    public static final int TYPELEAF = 1;
    public static final int STATEOVERFLOW = 2;
    public static final int STATENORMAL = 3;

    public int getSize(){
        return this.ORDER;
    }

    public void setNodeNo(int no){
        this.nodeNo = no;
    }

    public int getType(){return this.type;}
    public int getState(){return this.state;}

    public boolean hasSpace(){
        if(this.getSize() < this.ORDER*2){
            return true;
        }
        else
            return false;
    }

    public boolean isOverflowed(){
        if(this.getSize()>2*Node.ORDER){
            return true;
        }
        else
            return false;
    }

    public void linkNodes(Node M){
        this.nextNode = M.nodeNo;
        M.prevNode = this.nodeNo;
    }

}
