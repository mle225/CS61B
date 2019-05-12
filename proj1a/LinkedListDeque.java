import sun.awt.image.ImageWatched;

import java.util.LinkedList;

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

    //Constructor for empty LLD
    public LinkedListDeque() {
        Node<Integer> sen = new Node <>(999, null, null);
        sen.next = sen;
        sen.prev = sen;
    }

    public void addFirst (T item) {
        size++;
        Node first = new Node(item, sen, sen.next);
        if (size == 1) {
            sen.next = first;
            sen.prev = first;
        } else {
            sen.next.prev = first;
            sen.next = first;
        }
    }

    public void addLast (T item) {
        size++;
        Node last = new Node(item, sen.prev, sen);
        if (size == 1) {
            sen.next = last;
            sen.prev = last;
        } else {
            sen.prev.next = last;
            sen.prev = last;
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

//    private Node<T> getHelper(LinkedListDeque a, int index) {
//        if (index == 0) {
//            return a.sen.next;
//        }
//        return
//    }

    public T getRecursive (int index) {
        if (index < 0 || index >= size) {
            return null;
        }
        if (index == 0) {
            return (T) sen.next.item;
        }
        if (index == size - 1) {
            return  (T) sen.prev.item;
        }


        return getRecursive (index - 1);
    }
}