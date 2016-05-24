package btreeTest;

import btree.*;
import junit.framework.TestCase;
import org.junit.Test;


/**
 * Created by chiemi on 2016/05/19.
 */
public class LeafNodeTest extends TestCase {

    @Test
    public void testNoRecordInInitialNode(){
        LeafNode node = new LeafNode();
        assertEquals(node.getType(), Node.TYPELEAF);
        assertEquals(node.getSize(),0);
    }

    @Test
    public void testRecordsAreSorted(){
        LeafNode node = new LeafNode();
        try {
            node.insertRecord(new Record("A", 7));
            node.insertRecord(new Record("B", 5));
        } catch (IndexOutOfBoundsException e){
            fail("Insertion is failed while the node has some space to insert.");
        }
        assertEquals(node.getSize(),2);
        assertEquals(node.getRecord(0).getKeyValue(),5);
        assertEquals(node.getRecord(1).getKeyValue(),7);
    }

    @Test
    public void testOverflowedNodeShowsTheState(){

        LeafNode node = new LeafNode();
        try {
            assertEquals(node.getState(), Node.STATENORMAL);
            node.insertRecord(new Record("A", 17));
            assertEquals(node.getSize(), 1);
            assertEquals(node.getState(), Node.STATENORMAL);
            node.insertRecord(new Record("B", 75));
            assertEquals(node.getSize(), 2);
            assertEquals(node.getState(), Node.STATENORMAL);
            node.insertRecord(new Record("C", 2));
            assertEquals(node.getSize(), 3);
            assertEquals(node.getState(), Node.STATENORMAL);
            node.insertRecord(new Record("D", 10));
            assertEquals(node.getSize(), 4);
            assertEquals(node.getState(), Node.STATENORMAL);
            node.insertRecord(new Record("E", 33));
            assertEquals(node.getSize(), 5);
            assertEquals(node.getState(), Node.STATEOVERFLOW);
            node.insertRecord(new Record("F", 24));
        }catch(IndexOutOfBoundsException e){
            assertEquals(node.getSize(), 5);
            assertEquals(node.getState(), Node.STATEOVERFLOW);
        }

        assertEquals(node.getRecord(0).getKeyValue(),2);
        assertEquals(node.getRecord(1).getKeyValue(),10);
        assertEquals(node.getRecord(2).getKeyValue(),17);
        assertEquals(node.getRecord(3).getKeyValue(),33);
        assertEquals(node.getRecord(4).getKeyValue(),75);
    }


    @Test
    public void testEntriesCanbeMoved(){

        LeafNode node1 = new LeafNode();
        try {
            node1.insertRecord(new Record("A", 17));
            node1.insertRecord(new Record("B", 75));
            node1.insertRecord(new Record("C", 2));
            node1.insertRecord(new Record("D", 10));
            node1.insertRecord(new Record("E", 33));
        }catch(IndexOutOfBoundsException e){
            fail("The node is overflowed.");
        }

        LeafNode node2 = new LeafNode();
        node1.moveRecords(2,4,node2);

        assertEquals(node1.getSize(),2);
        assertEquals(node2.getSize(),3);

        assertEquals(node1.getRecord(0).getKeyValue(),2);
        assertEquals(node1.getRecord(1).getKeyValue(),10);
        assertEquals(node2.getRecord(0).getKeyValue(),17);
        assertEquals(node2.getRecord(1).getKeyValue(),33);
        assertEquals(node2.getRecord(2).getKeyValue(),75);


    }

}
