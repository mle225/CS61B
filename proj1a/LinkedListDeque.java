public class LinkedListDeque<T>{

    private class Node{

        public T item;
        public Node next;
        public Node prev;

        public Node(T stuff, Node prev, Node next){
            item = stuff;
            this.prev = prev;
            this.next = next;
        }
    }

    private Node sen;
    private Node first;
    private Node last;
    private int size = 0;

    //Constructs empty Deque
    public LinkedListDeque(){
        sen = new Node(null, null, null);
        first = sen;
        last = sen;
    }

    public void addFirst(T stuff){
        sen.next = new Node(stuff, sen, first);
        first.prev = sen.next;
        first = sen.next;
        size++;
        if (size == 1) {
            last = first;
        }
    }

    public void addLast(T stuff) {
        sen.prev = new Node(stuff, last, sen);
        last.next = sen.prev;
        last = sen.prev;
        size++;
        if (size == 1) {
            first = last;
        }
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
            p = p.next;
        }
    }

    public T removeFirst() {
        if (size == 0)
            return null;
        sen.next = first.next;
        (first.next).prev = sen;
        size--;
        return first.item;
    }

    public T removeLast() {
        if (size == 0)
            return null;
        sen.prev = last.prev;
        (last.prev).next = sen;
        size--;
        return last.item;
    }

    public T get(int index) {
        if (index == 0)
            return first.item;
        if (index >= size || index < 0)
            return null;
        Node p = first;
        int i = 0;
        while (i < index) {
            p = p.next;
            i++;
        }
        return p.item;
    }

    public T getRecursive(int index){
        if (index == 0)
            return first.item;
        if (index >= size || index < 0)
            return null;
        return getRecursive(index--);
    }
}