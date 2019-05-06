import org.junit.Test;
import static org.junit.Assert.*;

public class ArrayDequeTest{
//    @Test
//    public void testConstruct(){
//        ArrayDeque<int> expected = new ArrayDeque<>();
//        ArrayDeque<int> actual = new ArrayDeque<>();
//        assertEquals(expected, actual);
//    }

//    @Test
//    public void testResize(){
//        ArrayDeque<int> expected1 = {1,2,3,4,0,0,0,0};
//        ArrayDeque<int> actual1 = {1,2,3,4};
//        actual1.resize();
//        assertEquals(expected1,actual1);
//    }

    @Test
    public void testAddFirst(){
        ArrayDeque<Integer> actual2 = new ArrayDeque<>();
        actual2.addFirst(1);
        actual2.addFirst(2);
        actual2.addFirst(3);
        actual2.addFirst(5);
        actual2.addFirst(7);

        assertEquals(1,(long)actual2.get(3));
        assertEquals(2,(long)actual2.get(2));
        assertEquals(3,(long)actual2.get(1));
        assertEquals(5,(long)actual2.get(0));
        assertEquals(7,(long)actual2.get(7));
    }


//    @Test
//    public void testAddLast(){
//        ArrayDeque<Integer> actual3 = new ArrayDeque <>();
//        actual3.addLast(1);
//        actual3.addLast(2);
//        actual3.addLast(5);
//        actual3.addLast(7);
//        actual3.addLast(4);
//        actual3.addLast(3);
//        actual3.addLast(8);
//        actual3.addLast(6);
//
//
//        ArrayDeque<Integer> expected3 = new ArrayDeque<>();
//        expected3.items = new Integer[]{4, 3, 8, 6, 1, 2, 5, 7};
//
//        assertArrayEquals(expected3.items,actual3.items);
//    }

    @Test
    public void testPrintDeque(){
//        {4, 3, 8, 6, 1, 2, 5, 7}
        ArrayDeque<Integer> actual4 = new ArrayDeque <>();
        actual4.addLast(1);
        actual4.addLast(2);
        actual4.addLast(5);
        actual4.addLast(7);
        actual4.addLast(4);
        actual4.addLast(3);
        actual4.addLast(8);
        actual4.addLast(6);

        actual4.printDeque();
    }

//    @Test
//    public void testIsEmpty(){
//        boolean expected4 = true;
//        ArrayDeque<Integer> ad = new ArrayDeque<>();
//        boolean actual4 = ad.isEmpty();
//        assertEquals(expected4,actual4);
//    }

//    @Test
//    public void testSize(){
//        int exptected5 = 4;
//        ArrayDeque<String> ad1 = new ArrayDeque<>{"egg", "potato", "beef", "ham"};
//        assertEquasl(exptected5,ad1.size);
//    }

    @Test
    public void testRemoveFirst(){
        ArrayDeque<Integer> e5 = new ArrayDeque<>();
        for (int i = 0; i < 6; i++){
            e5.addLast(i);
        }
        e5.removeFirst();

        assertEquals(1,(long)e5.removeFirst());

    }

    @Test
    public void testRemoveLast(){
        ArrayDeque<Integer> e6 = new ArrayDeque<>();
        e6.addFirst(1);
        e6.addFirst(2);
        e6.addFirst(3);
        e6.addFirst(4);
        e6.addFirst(5);
        e6.addFirst(6);
        e6.addFirst(7);
        e6.addFirst(8);

        int n = e6.removeLast();

        assertEquals(1,(long)n);
        assertEquals(2, e6.lastIndex);

    }

}