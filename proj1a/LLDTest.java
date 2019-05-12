import org.junit.Test;
import static org.junit.Assert.*;

public class LLDTest {

    @Test
    public void testLLD() {

        LinkedListDeque<Integer> a = new LinkedListDeque<>();
        a.addLast(4);
        a.addLast(0);
        a.addFirst(1);
        a.addFirst(2);
        assertEquals(0, (long)a.getRecursive(3));
//        assertEquals(2, (long)a.get(0));
//        System.out.println(a.sen.next.item);
//        System.out.println(a.sen.prev.item);


    }

}
