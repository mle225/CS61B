public class ArrayDeque<T> implements Deque<T> {

    private T[] items;
    private int size;
    private double loadFactor;
    private int firstIndex;
    private int lastIndex;
    private int capacity;

    //Constructor
    public ArrayDeque() {
        //starting size 8
        items = (T[]) new Object[8];
        size = 0;
        //start both pointers at 0
        firstIndex = lastIndex = 0;
        capacity = 8;
    }

    //helper method to resize array
    private void resize(int newCapacity) {
        T[] newItems = (T[]) new Object[newCapacity];
        int newIndex = 0;
        int oldIndex = firstIndex;
        //assigns old array into new, starting at 0
        //minusOne every time
        for (int rep = 0; rep < size; rep++) {
            newItems[newIndex] = items[oldIndex];
            //walks new array
            newIndex = minusOne(newIndex, newCapacity);
            //walks old array
            oldIndex = minusOne(oldIndex, this.capacity);
        }
        //assigns old array to new one
        items = newItems;
        //new Array starts at 0, end at lastArray
        firstIndex = 0;
        lastIndex = (newCapacity / 2) + 1;
        capacity = newCapacity;
    }

    //helper method to find index
    private int minusOne(int index, int cap) {
        if (index == 0) {
            return cap - 1;
        }
        return index - 1;
    }

    private int plusOne(int index, int cap) {
        if (index == (cap - 1)) {
            return 0;
        }
        return index + 1;
    }

    //adds item into first slot
    @Override
    public void addFirst(T item) {
        if (size == 0) {
            items[firstIndex] = item;
        } else {
            firstIndex = plusOne(firstIndex, capacity);
            items[firstIndex] = item;
        }
        size++;
        //resize if array is full
        if (size == capacity) {
            resize(capacity * 2);
        }
    }

    @Override
    public void addLast(T item) {
        if (size == 0) {
            items[lastIndex] = item;
        } else {
            lastIndex = minusOne(lastIndex, capacity);
            items[lastIndex] = item;
        }
        size++;
        //resize if array is full
        if (size == capacity) {
            resize(capacity * 2);
        }
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void printDeque() {
        int t = firstIndex;
        while (t != lastIndex) {
            System.out.print(items[t] + " ");
            t = minusOne(t, this.capacity);
        }
        System.out.print(items[lastIndex]);
    }

    //removes and returns first item in AD
    @Override
    public T removeFirst() {
        if (size == 0) {
            return null;
        } else {
            T returnVal = items[firstIndex];
            size--;
            if (size != 0) {
                firstIndex = minusOne(firstIndex, this.capacity);
            }
            //resize if < 25% used
            loadFactor = (double) size / (double) capacity;
            if (loadFactor <= 0.25 && capacity >= 16) {
                resize(capacity / 2);
            }
            return returnVal;
        }
    }

    @Override
    public T removeLast() {
        if (size == 0) {
            return null;
        } else {
            T returnVal = items[lastIndex];
            size--;
            if (size != 0) {
                lastIndex = plusOne(lastIndex, this.capacity);
            }
            //resize if < 25% used
            loadFactor = (double) size / (double) capacity;
            if (loadFactor <= 0.25 && capacity >= 16) {
                resize(capacity / 2);
            }
            return returnVal;
        }
    }

    private int getHelper(int increment, int index) {
        int getIndex = index;
        for (int i = 0; i < increment; i++) {
            getIndex = minusOne(getIndex, this.capacity);
        }
        return getIndex;
    }

    @Override
    public T get(int index) {
        if (index < 0 || index >= size) {
            return null;
        }
        if (index == 0) {
            return items[firstIndex];
        }
        if (index == size - 1) {
            return items[lastIndex];
        }
        int getIndex = getHelper(index, firstIndex);
        return items[getIndex];
    }
}
