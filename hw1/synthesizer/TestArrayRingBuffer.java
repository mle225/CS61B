package synthesizer;
import org.junit.Test;
import static org.junit.Assert.*;

/** Tests the ArrayRingBuffer class.
 *  @author Josh Hug
 */

public class TestArrayRingBuffer {
    @Test
    public void someTest() {
        BoundedQueue<Double> arb = new ArrayRingBuffer(5){};
        arb.enqueue(0.0);
        arb.enqueue(1.0);
        arb.enqueue(9.0);
        arb.enqueue(7.6);
        arb.enqueue(3.4);
        assertTrue(0.0 == arb.dequeue());
        assertTrue(1.0 == arb.dequeue());
        arb.enqueue(9.8);
        assertTrue(9.0 == arb.peek());

    }

    /** Calls tests for ArrayRingBuffer. */
    public static void main(String[] args) {
        jh61b.junit.textui.runClasses(TestArrayRingBuffer.class);
    }
} 
