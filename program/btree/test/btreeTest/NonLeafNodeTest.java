package btreeTest;

import btree.*;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by chiemi on 2016/05/20.
 */
public class NonLeafNodeTest extends TestCase {

    NonLeafNode node = new NonLeafNode();

    @Before
    public void setup(){
        node.init(0);
    }


    @Test
    public void testEntryAreSorted(){
        setup();
        node.insertEntry(new Entry(10,1));
        node.insertEntry(new Entry(3,2));
        node.insertEntry(new Entry(6,3));

        assertEquals(node.getSize(),3);
        assertEquals(node.getPointer(0),0);
        assertEquals(node.getPointer(1),2);
        assertEquals(node.getPointer(2),3);
        assertEquals(node.getPointer(3),1);

        assertEquals(node.getKey(0),3);
        assertEquals(node.getKey(1),6);
        assertEquals(node.getKey(2),10);

    }

    @Test
    public void testOverflowedNodeShowstheStatus(){
        setup();
        try {
            node.insertEntry(new Entry(10, 1));
            node.insertEntry(new Entry(3, 2));
            node.insertEntry(new Entry(6, 3));
            node.insertEntry(new Entry(30, 4));
            assertEquals(node.getState(), Node.STATENORMAL);
            node.insertEntry(new Entry(56, 5));
            assertEquals(node.getState(), Node.STATEOVERFLOW);
            node.insertEntry(new Entry(23, 6));
        }catch(IndexOutOfBoundsException e){
            assertEquals(node.getSize(),5);
        }
        assertEquals(node.getSize(),5);
    }

    @Test
    public void testEntriesCanbeMoved(){
        setup();
        node.insertEntry(new Entry(10, 1));
        node.insertEntry(new Entry(3, 2));
        node.insertEntry(new Entry(6, 3));
        node.insertEntry(new Entry(30, 4));
        node.insertEntry(new Entry(56, 5));

        assertEquals(node.getPointer(0),0);
        assertEquals(node.getPointer(1),2);
        assertEquals(node.getPointer(2),3);
        assertEquals(node.getPointer(3),1);
        assertEquals(node.getPointer(4),4);
        assertEquals(node.getPointer(5),5);

        NonLeafNode node2 = new NonLeafNode();
        node.movePointers(3,5,node2);
        node.moveKeys(3,4,node2);

        assertEquals(node.getKey(0),3);
        assertEquals(node.getKey(1),6);
        assertEquals(node.getKey(2),10);
        assertEquals(node2.getKey(0),30);
        assertEquals(node2.getKey(1),56);

        assertEquals(node.getPointer(0),0);
        assertEquals(node.getPointer(1),2);
        assertEquals(node.getPointer(2),3);
        assertEquals(node2.getPointer(0),1);
        assertEquals(node2.getPointer(1),4);
        assertEquals(node2.getPointer(2),5);

    }

}
