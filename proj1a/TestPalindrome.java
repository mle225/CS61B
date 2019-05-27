import org.junit.Test;
import static org.junit.Assert.*;

public class TestPalindrome {
    // You must use this palindrome, and not instantiate
    // new Palindromes, or the autograder might be upset.
    static Palindrome palindrome = new Palindrome();

    @Test
    public void testWordToDeque() {
        Deque d = palindrome.wordToDeque("Dermatology");
        String actual = "";
        for (int i = 0; i < "Dermatology".length(); i++) {
            actual += d.removeFirst();
        }
        assertEquals("Dermatology", actual);
    }

    @Test
    public void testIsPalindrome() {
        assertTrue(palindrome.isPalindrome("tacocat"));
        assertTrue(palindrome.isPalindrome("racecar"));
        assertFalse(palindrome.isPalindrome("Hannah"));
        assertFalse(palindrome.isPalindrome("Tacocat"));
        assertTrue(palindrome.isPalindrome("XXyyyXX"));
    }


}
