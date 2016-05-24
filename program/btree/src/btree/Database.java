package btree;

import java.util.ArrayList;

/**
 * Created by chiemi on 2016/05/18.
 */
public class Database {
    ArrayList<Node> nodes = new ArrayList<Node>();
    int rootNodeNo = 0;

    public Database(int order){
        Node.ORDER = order;
        int nodeNo = this.generateNode(Node.TYPELEAF);
        this.rootNodeNo = nodeNo;
    }

    public Entry insertRecord(Record record){
        Entry e = new Entry(record);
        return insert(this.rootNodeNo, e);
    }

    public int getNofNodes(){ return nodes.size();}
    public Node getNode(int nodeNo){
        return nodes.get(nodeNo);
    }
    public Node getRootNode(){return nodes.get(rootNodeNo);}

    public ArrayList<Record> RangeQuery(Integer lo,boolean eq_lo,Integer hi,boolean eq_hi){
       // Exercise 2 : Write the program for range query
    }

    // ----------- Private methods -----------------
    private int getNewNodeNo(){
        return getNofNodes();
    }

    private int generateNode(int type){
        Node node = null;
        int newNodeNo = this.getNewNodeNo();
        if(type==Node.TYPENONLEAF){
            node = new NonLeafNode();
        }else{
            node = new LeafNode();
        }
        node.setNodeNo(newNodeNo);
        this.addNode(node);
        return newNodeNo;
    }

    private void addNode(Node node){
        nodes.add(node);
    }

    private NonLeafNode generateNewRootNode(){
        int new_rootNo = this.generateNode(Node.TYPENONLEAF);
        int ex_rootNo = this.rootNodeNo;
        this.rootNodeNo = new_rootNo;
        NonLeafNode R = (NonLeafNode)this.getNode(new_rootNo);
        R.init(ex_rootNo);
        return R;
    }

    private boolean isRootNode(Node node){
        if(node.nodeNo == this.rootNodeNo) {
            return true;
        }
        return false;
    }

    private Entry insert(int P, Entry entry){
	// Excercise 1 : Write insert program 
    }

    private Entry divideNonLeaf(NonLeafNode N){
       // Exercise 1 : Write insert program 
    }

    private Entry divideLeaf(LeafNode N){
       // Exercise 1 : Write insert program
    }

    private void linkDecendantNodes(NonLeafNode N, NonLeafNode M){
        Node descN = N;
        while(descN.getType()==Node.TYPENONLEAF){
            descN = this.getNode(((NonLeafNode)descN).getPointer(descN.getSize()));
        }
        Node descM = M;
        while(descM.getType()==Node.TYPENONLEAF){
            descM = this.getNode(((NonLeafNode)descM).getPointer(0));
        }
        descN.nextNode = descM.nodeNo;
        descM.prevNode = descN.nodeNo;
    }

}
