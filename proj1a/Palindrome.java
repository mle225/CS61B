public class Palindrome {

    public Deque<Character> wordToDeque(String word){

        String temp = word;
        Deque<Character> d = new LinkedListDeque<>();
        while (temp.length() != 0) {
            char front = temp.charAt(0);
            d.addLast(front);
            temp = temp.substring(1);
        }
        return d;
    }

    private Deque<Character> pHelper(String word) {
        Palindrome p = new Palindrome();
        Deque d = p.wordToDeque(word);
        return d;
    }

    public boolean isPalindrome(String word) {
        if (word.length() <= 1) {
            return true;
        }
        Deque d = pHelper(word);
        if (d.removeFirst() != d.removeLast()) {
            return false;
        }
        String sub = word.substring(1,word.length() - 1);
        return isPalindrome(sub);
    }
}
