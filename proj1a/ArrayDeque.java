public class ArrayDeque<T>{
    public T[] items;
    private int size;
    private int capacity;
    private double load_factor;
    private int first_index;
    private int last_index;

    public ArrayDeque(){
        items = (T[]) new Object[8];
        size = 0;
        capacity = 8;
        first_index = 3;
        last_index = 4;
        load_factor = 0 ;
    }

    //Helper method to resize Array
    public void resize(int newCapacity){
        T[] a = (T[]) new Object[newCapacity];
        for (int i = 0; i < size; i++){
            a[i] = items[i];
        }
        items = a;
        capacity = newCapacity;
        load_factor = size / capacity;
    }

    //Helper method to find array to addFirst

    public int minusOne(int index){
        if (index == 0)
            return size-1;
        else
            return index-1;
    }

    public int plusOne(int index){
        if (index == size -1)
            return 0;
        else
            return index+1;
    }

    public void addFirst(T item){
//        if (size == capcity){
//            this.resize(capacity *2);
//        }
        if (this.isEmpty())
            items[first_index] = item;
        else
            items[minusOne(first_index)] = item;
        size++;
        load_factor = size / capacity;

    }

    public void addLast (T item){
//        if (size == capcity){
//            this.resize(capacity *2);
//        }
        if (this.isEmpty())
            items[last_index] = item;
        else{
            items[plusOne(last_index)] = item;
        }
        size++;
        load_factor = size/ capacity;
    }

    public boolean isEmpty(){
        return size == 0;
    }

    public int size(){
        return size;
    }

    public void printDeque(){
        for (int i =0; i < size -1; i++){
            System.out.print(items[i] + " ");
        }
        System.out.print(items[size-1]);
    }

    public void removeFirst(){
        items[first_index] = null;
        first_index = minusOne(first_index);
        size--;
        load_factor = (size)/capacity;
//        if (load_factor < 0.25 && size >= 16){
//            resize(size / 2);
//        }
    }

    public void removeLast(){
        items[last_index] = null;
        last_index = plusOne(last_index);
        size++;
        load_factor = (--size)/capacity;
//        if (load_factor < 0.25 && size >= 16){
//            resize(size / 2);
//        }
    }

    public T get (int index){
        return items[index];
    }







}