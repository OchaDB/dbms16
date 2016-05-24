package btreeTest;

import btree.*;
import junit.framework.TestCase;
import org.junit.Test;

import java.util.ArrayList;

/**
 * Created by chiemi on 2016/05/20.
 */
public class DatabaseTest extends TestCase {

    int order = 2;
    Database db = new Database(order);

    @Test
    public void test_InitialDBhasOneLeafNode(){
        assertEquals(db.getNofNodes(),1);
        Node n = db.getNode(0);
        assertEquals(n.getType(),Node.TYPELEAF);
    }

    @Test
    public void testSomeRecordCanbeInserted(){
        db.insertRecord(new Record("A",23));
        db.insertRecord(new Record("B",12));
        db.insertRecord(new Record("C",37));
        db.insertRecord(new Record("D",82));
        assertEquals(db.getNofNodes(),1);
        Node n = db.getNode(0);
        assertEquals(n.getType(),Node.TYPELEAF);
        LeafNode ln = (LeafNode)n;
        assertEquals(ln.getSize(),4);
    }


    @Test
    public void testNodeisDivided() {
        db.insertRecord(new Record("A", 23));
        db.insertRecord(new Record("B", 12));
        db.insertRecord(new Record("C", 37));
        db.insertRecord(new Record("D", 82));
        db.insertRecord(new Record("E", 25));
        assertEquals(db.getNofNodes(), 3);

        Node n0 = db.getNode(0);
        assertEquals(n0.getType(),Node.TYPELEAF);
        Node n1 = db.getNode(1);
        assertEquals(n1.getType(),Node.TYPELEAF);
        Node n2 = db.getNode(2);
        assertEquals(n2.getType(),Node.TYPENONLEAF);

        assertEquals(n0.getSize(),2);
        assertEquals(((LeafNode)n0).getRecord(0).getKeyValue(),12);
        assertEquals(((LeafNode)n0).getRecord(1).getKeyValue(),23);
        assertEquals(n1.getSize(),3);
        assertEquals(((LeafNode)n1).getRecord(0).getKeyValue(),25);
        assertEquals(((LeafNode)n1).getRecord(1).getKeyValue(),37);
        assertEquals(((LeafNode)n1).getRecord(2).getKeyValue(),82);
        assertEquals(n2.getSize(),1);
        assertEquals(((NonLeafNode)n2).getPointer(0),0);
        assertEquals(((NonLeafNode)n2).getPointer(1),1);
        assertEquals(((NonLeafNode)n2).getKey(0),25);
    }

    @Test
    public void testEntryisInsertedInNonLeafNode() {
        db.insertRecord(new Record("A", 23));
        db.insertRecord(new Record("B", 12));
        db.insertRecord(new Record("C", 37));
        db.insertRecord(new Record("D", 82));
        db.insertRecord(new Record("E", 25));
        db.insertRecord(new Record("F", 27));
        db.insertRecord(new Record("G", 67));
        assertEquals(db.getNofNodes(),4);

        Node n = db.getRootNode();
        assertEquals(n.getType(),Node.TYPENONLEAF);
        NonLeafNode nln = (NonLeafNode)n;
        assertEquals(nln.getSize(),2);
        assertEquals(nln.getKey(1),37);
    }

    @Test
    public void testNonLeafNodeCanbeDivided() {
        db.insertRecord(new Record("A", 23));
        db.insertRecord(new Record("B", 12));
        db.insertRecord(new Record("C", 37));
        db.insertRecord(new Record("D", 82));
        db.insertRecord(new Record("E", 25));
        db.insertRecord(new Record("F", 27));
        db.insertRecord(new Record("G", 67));
        db.insertRecord(new Record("H", 2));
        db.insertRecord(new Record("I", 13));
        db.insertRecord(new Record("J", 22));
        db.insertRecord(new Record("K", 19));
        db.insertRecord(new Record("L", 20));
        db.insertRecord(new Record("M", 50));
        db.insertRecord(new Record("N", 70));
        assertEquals(db.getNofNodes(),9);

        assertEquals(((LeafNode)db.getNode(0)).getRecord(0).getKeyValue(),2);
        assertEquals(((LeafNode)db.getNode(0)).getRecord(1).getKeyValue(),12);
        assertEquals(((LeafNode)db.getNode(4)).getRecord(0).getKeyValue(),13);
        assertEquals(((LeafNode)db.getNode(4)).getRecord(1).getKeyValue(),19);
        assertEquals(((LeafNode)db.getNode(5)).getRecord(0).getKeyValue(),20);
        assertEquals(((LeafNode)db.getNode(5)).getRecord(1).getKeyValue(),22);
        assertEquals(((LeafNode)db.getNode(5)).getRecord(2).getKeyValue(),23);
        assertEquals(((LeafNode)db.getNode(1)).getRecord(0).getKeyValue(),25);
        assertEquals(((LeafNode)db.getNode(1)).getRecord(1).getKeyValue(),27);
        assertEquals(((LeafNode)db.getNode(3)).getRecord(0).getKeyValue(),37);
        assertEquals(((LeafNode)db.getNode(3)).getRecord(1).getKeyValue(),50);
        assertEquals(((LeafNode)db.getNode(6)).getRecord(0).getKeyValue(),67);
        assertEquals(((LeafNode)db.getNode(6)).getRecord(1).getKeyValue(),70);
        assertEquals(((LeafNode)db.getNode(6)).getRecord(2).getKeyValue(),82);

    }

    @Test
    public void testAllLeafNodesAreConnectedEachOther(){
        db.insertRecord(new Record("A", 23));
        db.insertRecord(new Record("B", 12));
        db.insertRecord(new Record("C", 37));
        db.insertRecord(new Record("D", 82));
        db.insertRecord(new Record("E", 25));
        db.insertRecord(new Record("F", 27));
        db.insertRecord(new Record("G", 67));
        db.insertRecord(new Record("H", 2));
        db.insertRecord(new Record("I", 13));
        db.insertRecord(new Record("J", 22));
        db.insertRecord(new Record("K", 19));
        db.insertRecord(new Record("L", 20));
        db.insertRecord(new Record("M", 50));
        db.insertRecord(new Record("N", 70));

        assertEquals(db.getNode(0).prevNode,null);
        assertEquals(db.getNode(0).nextNode,new Integer(4));
        assertEquals(db.getNode(4).prevNode,new Integer(0));
        assertEquals(db.getNode(4).nextNode,new Integer(5));
        assertEquals(db.getNode(5).prevNode,new Integer(4));
        assertEquals(db.getNode(5).nextNode,new Integer(1));
        assertEquals(db.getNode(1).prevNode,new Integer(5));
        assertEquals(db.getNode(1).nextNode,new Integer(3));
        assertEquals(db.getNode(3).prevNode,new Integer(1));
        assertEquals(db.getNode(3).nextNode,new Integer(6));
        assertEquals(db.getNode(6).prevNode,new Integer(3));
        assertEquals(db.getNode(6).nextNode,null);

    }

    @Test
    public void testRangeQueryReturnsAccurateResults(){
        db.insertRecord(new Record("A", 23));
        db.insertRecord(new Record("B", 12));
        db.insertRecord(new Record("C", 37));
        db.insertRecord(new Record("D", 82));
        db.insertRecord(new Record("E", 25));
        db.insertRecord(new Record("F", 27));
        db.insertRecord(new Record("G", 67));
        db.insertRecord(new Record("H", 2));
        db.insertRecord(new Record("I", 13));
        db.insertRecord(new Record("J", 22));
        db.insertRecord(new Record("K", 19));
        db.insertRecord(new Record("L", 20));
        db.insertRecord(new Record("M", 50));
        db.insertRecord(new Record("N", 70));

        ArrayList<Record> results = db.RangeQuery(21,false,null,false);
        assertEquals(results.size(),9);

        results = db.RangeQuery(22,false,null,false);
        assertEquals(results.size(),8);
        results = db.RangeQuery(22,true,null,false);
        assertEquals(results.size(),9);

        results = db.RangeQuery(null,false,40,true);
        assertEquals(results.size(),10);
        results = db.RangeQuery(null,false,36,true);
        assertEquals(results.size(),9);
        results = db.RangeQuery(null,false,50,true);
        assertEquals(results.size(),11);
        results = db.RangeQuery(null,false,50,false);
        assertEquals(results.size(),10);
        results = db.RangeQuery(22,true,50,true);
        assertEquals(results.size(),6);
        results = db.RangeQuery(22,false,50,false);
        assertEquals(results.size(),4);

    }
}
