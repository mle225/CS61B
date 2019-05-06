public class ArrayDeque<T> {
    private T[] items;
    private int size;
    private int capacity;
    private double loadFactor;
    private int firstIndex;
    private int lastIndex;

    public ArrayDeque() {
        items = (T[]) new Object[8];
        size = 0;
        capacity = 8;
        firstIndex = 3;
        lastIndex = 3;
        loadFactor = 0;
    }

    //Helper method to resize Array
    private void resize(int newCapacity) {
        T[] a = (T[]) new Object[newCapacity];
        for (int i = 0; i < size; i++) {
            a[i] = items[i];
        }
        items = a;
        capacity = newCapacity;
        loadFactor = size / capacity;
    }

    //Helper method to find array to addFirst

    private int minusOne(int index) {
        if (index == 0) {
            return capacity - 1;
        }
        else {
            index--;
            return index;
        }
    }

    private int plusOne(int index) {
        if (index == capacity - 1) {
            return 0;
        }
        else {
            return index + 1;
        }
    }

    public void addFirst(T item) {
        if (size == capacity) {
            this.resize(capacity * 2);
        }
        if (this.isEmpty()) {
            items[firstIndex] = item;
        }
        else {
            firstIndex = minusOne(firstIndex);
            items[firstIndex] = item;
        }
        size++;
        loadFactor = (double) size / (double) capacity;
    }

    public void addLast (T item) {
        if (size == capacity){
            this.resize(capacity * 2);
        }
        if (this.isEmpty()) {
            items[lastIndex] = item;
        }
        else {
            lastIndex = plusOne(lastIndex);
            items[lastIndex] = item;
        }
        size++;
        loadFactor = (double) size / (double)capacity;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void printDeque() {
        for (int i = firstIndex; i > 0; i--)
            System.out.print(items[i] + " ");
        for (int j = 0; j < lastIndex; j++)
            System.out.print(items[j]+ " ");
    }

    public T removeFirst() {
        if (size == 0) {
            return null;
        }
        T a = items[firstIndex];
//        items[firstIndex] = null;
        size--;
        firstIndex = plusOne(firstIndex);
        loadFactor = (double) size / (double) capacity;
        if (loadFactor < 0.25 && size >= 16) {
            resize(size / 2);
        }

        return a;
    }

    public T removeLast() {
        if (size == 0) {
            return null;
        }
        T a = items[lastIndex];
//        items[lastIndex] = null;
        size--;
        lastIndex = minusOne(lastIndex);
        loadFactor = (double) size / (double) capacity;
        if (loadFactor < 0.25 && size >= 16) {
            resize(size / 2);
        }
        return a;
    }

    public T get (int index) {
        return items[index];
    }

}
