public class LinkedListDeque<T> {

    private class Node<T> {

        private T item;
        private Node next;
        private Node prev;

        public Node(T stuff, Node prev, Node next) {
            item = stuff;
            this.prev = prev;
            this.next = next;
        }
    }

    private Node sen;
    private int size = 0;

    //Constructor for empty LLD
    public LinkedListDeque() {
        sen = new Node(999, null, null);
        sen.next = sen;
        sen.prev = sen;
    }

    public void addFirst(T item) {
        if (size == 0) {
            sen.next = new Node<>(item, sen, sen.next);
            sen.prev = sen.next;
        } else {
            Node first = new Node<>(item, sen, sen.next);
            sen.next.prev = first;
            sen.next = first;
        }
        size++;
    }

    public void addLast(T item) {
        if (size == 0) {
            sen.prev = new Node(item, sen.prev, sen);
            sen.next = sen.prev;
        } else {
            Node last = new Node(item, sen.prev, sen);
            sen.prev.next = last;
            sen.prev = last;
        }
        size++;
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
        System.out.print(p.item);
    }

    public T removeFirst() {
        if (size == 0) {
            return null;
        }
        T a = (T) sen.next.item;
        if (size == 1) {
            sen.next = sen;
            sen.prev = sen;
        } else {
            sen.next = sen.next.next;
            sen.next.prev = sen;
        }
        size--;
        return a;
    }

    public T removeLast() {
        if (size == 0) {
            return null;
        }
        T a = (T) sen.prev.item;
        if (size == 1) {
            sen.next = sen;
            sen.prev = sen;
        } else {
            sen.prev = sen.prev.prev;
            sen.prev.next = sen;
        }
        size--;
        return a;
    }

    public T get(int index) {
        int i = index;
        if (i < 0 || i >= size) {
            return null;
        }
        if (i == 0) {
            return (T) sen.next.item;
        }
        if (i == size - 1) {
            return (T) sen.prev.item;
        }
        Node p = sen.next;
        for (int j = 0; j < i; j++) {
            p = p.next;
        }
        return (T) p.item;
    }

    public T getRecursive (int index) {
        if (index < 0 || index >= size) {
            return  null;
        }
        return (T) getRecursiveHelper(sen.next, index);
    }

    private T getRecursiveHelper(Node a, int index) {
        if (index == 0) {
            return (T) a.item;
        }
        return (T) getRecursiveHelper(a.next, index - 1);
    }
}
