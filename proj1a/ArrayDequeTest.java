import org.junit.Test;
import static org.junit.Assert.*;

public class ArrayDequeTest{
////    @Test
////    public void testConstruct(){
////        ArrayDeque<int> expected = new ArrayDeque<>();
////        ArrayDeque<int> actual = new ArrayDeque<>();
////        assertEquals(expected, actual);
////    }
//
////    @Test
////    public void testResize(){
////        ArrayDeque<int> expected1 = {1,2,3,4,0,0,0,0};
////        ArrayDeque<int> actual1 = {1,2,3,4};
////        actual1.resize();
////        assertEquals(expected1,actual1);
////    }
//
////    @Test
////    public void testAddFirst(){
////        ArrayDeque<String> actual2 = new ArrayDeque<>();
////        actual2.addFirst("Egg");
////
////        assertEquals("Egg", actual2.removeFirst());
////
////    }
//
//
////    @Test
////    public void testAddLast(){
////        ArrayDeque<Integer> actual3 = new ArrayDeque <>();
////        actual3.addLast(1);
////        actual3.addLast(2);
////        actual3.addLast(5);
////        actual3.addLast(7);
////        actual3.addLast(4);
////        actual3.addLast(3);
////        actual3.addLast(8);
////        actual3.addLast(6);
////
////
////        ArrayDeque<Integer> expected3 = new ArrayDeque<>();
////        expected3.items = new Integer[]{4, 3, 8, 6, 1, 2, 5, 7};
////
////        assertArrayEquals(expected3.items,actual3.items);
////    }
//
////    @Test
////    public void testPrintDeque(){
//////        {4, 3, 8, 6, 1, 2, 5, 7}
////        ArrayDeque<Integer> actual4 = new ArrayDeque <>();
////        actual4.addLast(1);
////        actual4.addLast(2);
////        actual4.addLast(5);
////        actual4.addLast(7);
////        actual4.addLast(4);
////        actual4.addLast(3);
////        actual4.addLast(8);
////        actual4.addLast(6);
////
////        actual4.printDeque();
////    }
//
////    @Test
////    public void testIsEmpty(){
////        boolean expected4 = true;
////        ArrayDeque<Integer> ad = new ArrayDeque<>();
////        boolean actual4 = ad.isEmpty();
////        assertEquals(expected4,actual4);
////    }
//
////    @Test
////    public void testSize(){
////        int exptected5 = 4;
////        ArrayDeque<String> ad1 = new ArrayDeque<>{"egg", "potato", "beef", "ham"};
////        assertEquasl(exptected5,ad1.size);
////    }
//
//    @Test
//    public void testRemoveFirst(){
//        ArrayDeque<Integer> e5 = new ArrayDeque<>();
//        for (int i = 0; i < 6; i++){
//            e5.addLast(i);
//        }
//        e5.removeFirst();
//
//        assertEquals(1,(long)e5.removeFirst());
//
//    }
//
//    @Test
//    public void testRemoveLast(){
//        ArrayDeque<Integer> e6 = new ArrayDeque<>();
//        e6.addLast(1);
//        e6.addLast(2);
//        e6.addLast(3);
//        e6.removeFirst();
//        e6.removeFirst();
//
//        assertEquals(3, (long)e6.removeFirst());
//        assert(2 == e6.lastIndex);
//        assert(2 == e6.firstIndex);
//
//
//
//
//    }

//    @Test
//    public void testResize(){
//        ArrayDeque<Integer> e7 = new ArrayDeque<>();
//        e7.addFirst(0);
//        e7.addFirst(1);
//        e7.addFirst(2);
//        e7.addFirst(3);
//        e7.addLast(4);
//        e7.addLast(5);
//        e7.addLast(6);
//        e7.addLast(7);
//
//        //resized
//
//        e7.addLast(11);
//        e7.addLast(12);
//        e7.addLast(13);
//        e7.addFirst(20);
//        e7.addFirst(21);
//        e7.addFirst(22);
//
////        assertEquals(14, (long)e7.size());
////        System.out.println(e7.get(5));
////        System.out.println(e7.get(0));
////        System.out.println(e7.get(7));
//        System.out.println(e7.lastIndex);
//        System.out.println(e7.firstIndex);
//
////        System.out.println(e7.get(10));


//        System.out.println(e7.lastIndex);
//        assertEquals(2, (long)e7.get(14));
//        assertEquals(13, (long)e7.get(6));
//        assertEquals(3, (long)e7.firstIndex);
//        assertEquals(6, (long)e7.lastIndex);

    //}

//    @Test
//    public void testExpandContract() {
//        ArrayDeque<Integer> e8 = new ArrayDeque<>();
//
//        for (int i = 0; i < 56; i++){
//            e8.addFirst(i);
//        }
//
//        for (int j = 0; j < 56; j++){
//            e8.removeFirst();
//        }
//
//        for (int t = 0; t < 400; t++){
//            e8.addFirst(t);
//        }
//
//        for (int h = 0; h < 200; h++){
//            e8.removeFirst();
//        }
//
//        assert(199 == e8.removeFirst());
//    }

    @Test
    public void testGet(){
        ArrayDeque<Integer> e8 = new ArrayDeque<>();
        e8.addFirst(0);
        e8.addFirst(1);
        e8.addFirst(2);
        e8.addFirst(3);
        e8.addLast(4);
        e8.addLast(5);
        e8.addLast(6);
        e8.addLast(7);

        assertEquals(7, (long)e8.get(7));
        assertEquals(0, (long)e8.get(3));
        assertEquals(2, (long)e8.get(1));



    }


}