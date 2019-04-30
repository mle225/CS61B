public class LinkedListDeque<T>{

    public class Node{

        public T item;
        public Node next;
        public Node prev;

        public Node(){
            item = null;
            next = this;
            prev = next;
        }
        public Node(T stuff, Node prev, Node next){
            item = stuff;
            prev = prev;
            next = next;
        }
    }

    private Node sen;
    private Node first;
    private Node last;
    private int size = 0;

    //Constructs empty Deque
    public LinkedListDeque(){
        sen = new Node();
        first = sen;
        last = sen;
    }

    //Constructs Deque w/ 1 item
    public LinkedListDeque(T stuff){
        sen = new Node();
        Node a = new Node(stuff, sen, sen);
        sen.next = a;
        sen.prev = a;
    }

    public void addFirst(T stuff){
        Node a = new Node(stuff, sen, first);
        sen.next = a;
        size++;
    }

    public void addLast(T stuff) {
        Node a = new Node(stuff, last, sen);
        sen.prev = a;
        size++;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void printDeque() {
        Node p = first;
        while (p != last) {
            System.out.print(p.item + " ");
        }
        p = p.next;
    }

    public T removeFirst() {
        if (size == 0)
            return null;
        sen.next = first.next;
        first.next.prev = sen;
        size--;
        return first.item;
    }

    public T removeLast() {
        if (size == 0)
            return null;
        sen.prev = last.prev;
        last.prev.next = sen;
        size--;
        return last.item;
    }

    public T get(int index) {
        int i = index;
        if (i == 0)
            return first.item;
        if (i >= size || i < 0)
            return null;
        Node p = first;
        while (i < index) {
            p = p.next;
            i++;
        }
        return p.item;
    }

    public static void main(String[] args){

    }
}