public class LinkedListDeque<T>{

    private class Node <T>{

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
    private int size = 0;

    //Constructs empty Deque
    public LinkedListDeque(){
        sen = new Node<>(999, null, null);
        sen.next = sen;
        sen.prev = sen;
    }

    public void addFirst(T stuff){
        sen.next = new Node(stuff, sen, sen.next);
        sen.next.next.prev = sen.next;
        size++;
        if (size == 1)
            sen.prev = sen.next;

    }

    public void addLast(T stuff) {
        sen.prev = new Node(stuff, sen.prev, sen);
        size++;
        if (size == 1) {
            sen.next = sen.prev;
        }
        else {
            sen.prev.prev.next = sen.prev;
        }


    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void printDeque() {
        Node p = sen.next;
        while (p != sen.prev) {
            System.out.print(p.item + " ");
            p = p.next;
        }
    }

    public T removeFirst() {
        if (size == 0)
            return null;
        T a = (T) sen.next.item;
        sen.next.next.prev = sen;
        sen.next = sen.next.next;
        size--;
        return a;
    }

    public T removeLast() {
        if (size == 0) {
            return null;
        }
        T a = (T) sen.next.item;
        sen.prev.prev.next = sen;
        sen.prev = sen.prev.prev;
        size--;
        return a;
    }

    public T get(int index) {
        if (index == 0)
            return (T) sen.next.item;
        if (index >= size || index < 0)
            return null;
        Node p = sen.next;
        int i = 0;
        while (i < index) {
            p = p.next;
            i++;
        }
        return (T) p.item;
    }

    public T getRecursive(int index){
        if (index == 0)
            return (T) sen.next.item;
        if (index >= size || index < 0)
            return null;
        return getRecursive(index--);
    }
}