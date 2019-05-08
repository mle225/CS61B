public class ArrayDeque<T> {
    private T[] items;
    private int size;
    public int capacity;
    public double loadFactor;
    public int firstIndex;
    public int lastIndex;

    public ArrayDeque() {
        items = (T[]) new Object[8];
        size = 0;
        capacity = 8;
        firstIndex = 0;
        lastIndex = 0;
        loadFactor = 0;
    }

    //Helper method to resize Array
    private void resize(int newCapacity) {
        T[] a = (T[]) new Object[newCapacity];
        //copy array qua
        int i = firstIndex;
        int j = 0;
        for(int k = 0; k < size; k++) {
            a[j] = items[i];
            j = minusOne(j, newCapacity);
            i = minusOne(i, capacity);
        }
        items = a;
        capacity = newCapacity;
        firstIndex = 0;
        lastIndex = capacity - size + 1;
        loadFactor = (double) size / (double) capacity;
    }

    //Helper method to find array to addFirst

    private int minusOne(int index, int cap) {
        if (index == 0) {
            return cap - 1;
        } else {
            index--;
            return index;
        }
    }

    private int plusOne(int index, int cap) {
        if (index == cap - 1) {
            return 0;
        } else {
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
            firstIndex = plusOne(firstIndex, capacity);
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
            lastIndex = minusOne(lastIndex, capacity);
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
        if (size != 0) {
            firstIndex = minusOne(firstIndex, capacity);
        }
        loadFactor = (double) size / (double) capacity;
        if (loadFactor <= 0.25 && size >= 16) {
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
        if (size != 0) {
            lastIndex = plusOne(lastIndex, capacity);
        }
        loadFactor = (double) size / (double) capacity;
        if (loadFactor <= 0.25 && size >= 16) {
            resize(size / 2);
        }
        return a;
    }

    public int getHelper(int increment, int index){
        int getIndex = index;
        for (int t = 0; t < increment; t++){
            getIndex = minusOne(getIndex, capacity);
        }
        return getIndex;
    }

    public T get (int index) {
        if (size == 0 || index >= size) {
            return null;
        }
        if (index == 0) {
            return items[firstIndex];
        }
        if (index == size -1) {
            return items[lastIndex];
        }
        int getIndex = getHelper(index,firstIndex);
        return items[getIndex];
    }

}
