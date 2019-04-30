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
    public static void testAddFirst(){
        ArrayDeque<Integer> actual2 = new ArrayDeque<>();
        actual2.addFirst(1);
        actual2.addFirst(2);
        actual2.addFirst(3);
        actual2.addFirst(5);
        actual2.addFirst(7);
        int[] expected2 = {5,3,2,1,0,0,0,7};
        assertEquals(expected2, actual2);

    }


//    @Test
//    public void testAddLast(){
//        ArrayDeque<int> expected3 = {1,2,3,4,5};
//        ArrayDeque<int> actual3 = {1,2,3,4,0};
//        actual3.addLast(5);
//        assertEquals(expected3,actual3);
//    }

//    @Test
//    public void testIsempty(){
//        bool expected4 = true;
//        ArrayDeque<int> ad = new ArrayDeque<>();
//        bool actual4 = ad.isEmpty();
//        assertEquals(expected4,actual4);
//    }

//    @Test
//    public void testSize(){
//        int exptected5 = 4;
//        ArrayDeque<String> ad1 = new ArrayDeque<>{"egg", "potato", "beef", "ham"};
//        assertEquasl(exptected5,ad1.size);
//    }

//    @Test
//    public void testRemoveFirst(){
//
//    }

}