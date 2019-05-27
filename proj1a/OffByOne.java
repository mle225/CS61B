import org.junit.Test;
import static org.junit.Assert.*;

public class OffByOne implements CharacterComparator{

    @Override
    public boolean equalChars(char x, char y) {
        return Math.abs(x - y) == 1;
    }

//    @Test
//    public void testOffByOne() {
//        OffByOne d = new OffByOne();
//        assertTrue(d.equalChars('a','b'));
//        assertTrue(d.equalChars('e','d'));
//        assertFalse(d.equalChars('a','f'));
//
//  }


}
