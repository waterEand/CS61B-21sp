package deque;

import java.util.Iterator;

public class ArrayDeque<T> implements Deque<T>, Iterable<T> {
    private T[] items;
    private int size;
    private int nextFirst;
    private int nextLast;

    public ArrayDeque() {
        items = (T[]) new Object[8];
        size = 0;
        nextFirst = 4;
        nextLast = 5;
    }

    private void resize(int capacity) {
        T[] a = (T[]) new Object[capacity];
        int idx = 0;
        for (int i = 0; i < size; i += 1) {
            if (nextFirst + 1 + i >= items.length) {
                idx = nextFirst + 1 + i - items.length;
            } else {
                idx =  nextFirst + 1 + i;
            }
            a[capacity / 4 + i] = items[idx];
        }
        items = a;
        nextFirst = capacity / 4 - 1;
        nextLast = nextFirst + size + 1;
    }

    private int arrayInd(int ind) {
        if (nextFirst + 1 + ind >= items.length) {
            return nextFirst + 1 + ind - items.length;
        } else {
            return nextFirst + 1 + ind;
        }
    }

    public void addFirst(T item) {
        if (size == items.length - 2) {
            resize(items.length * 2);
        }
        items[nextFirst] = item;
        if (nextFirst == 0) {
            nextFirst = items.length - 1;
        } else {
            nextFirst -= 1;
        }
        size += 1;
    }

    public void addLast(T item) {
        if (size == items.length - 2) {
            resize(items.length * 2);
        }
        items[nextLast] = item;
        if (nextLast == items.length - 1) {
            nextLast = 0;
        } else {
            nextLast += 1;
        }
        size += 1;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void printDeque() {
        int idx = 0;
        for (int i = 0; i < size; i += 1) {
            if (nextFirst + 1 + i >= items.length) {
                idx = nextFirst + 1 + i - items.length;
            } else {
                idx =  nextFirst + 1 + i;
            }
            System.out.print(items[idx] + " ");
        }
    }

    public T removeFirst() {
        if (isEmpty()) {
            return null;
        }
        if ((size < items.length / 4) && (size > 8)) {
            resize(items.length / 2);
        }
        int first = arrayInd(0);
        T res = items[first];
        items[first] = null;
        nextFirst = first;
        size -= 1;
        return res;
    }

    public T removeLast() {
        if (isEmpty()) {
            return null;
        }
        if ((size < items.length / 4) && (size > 8)) {
            resize(items.length / 2);
        }
        int last = arrayInd(size - 1);
        T res = items[last];
        items[last] = null;
        nextLast = last;
        size -= 1;
        return res;
    }

    public T get(int index) {
        if (isEmpty() || index > size - 1 || index < 0) {
            return null;
        }
        int idx = 0;
        if (nextFirst + 1 + index >= items.length) {
            idx = nextFirst + 1 + index - items.length;
        } else {
            idx =  nextFirst + 1 + index;
        }
        return items[idx];
    }

    public Iterator<T> iterator() {
        return new ArrayIterator();
    }

    private class ArrayIterator implements Iterator<T> {
        private int wizpos;

        public ArrayIterator() {
            wizpos = 0;
        }

        public boolean hasNext() {
            return wizpos < size;
        }

        public T next() {
            T returnItem = get(wizpos);
            wizpos += 1;
            return returnItem;
        }
    }

    public boolean equals(Object other) {
        if (this == other)
            return true;
        if (other == null || !(other instanceof Deque))
            return false;

        Deque<T> temp = (Deque<T>) other;
        if (this.size() != temp.size())
            return false;
        for (int i = 0; i < this.size(); i += 1) {
            if (this.get(i) != temp.get(i))
                return false;
        }

        return true;
    }

    /** change to public if test */
    private static void main(String[] args) {
        int n = 99;

        ArrayDeque<Integer> ad1 = new ArrayDeque<>();
        for (int i = 0; i <= n; i++) {
            ad1.addLast(i);
        }

        ArrayDeque<Integer> ad2 = new ArrayDeque<>();
        for (int i = n; i >= 0; i--) {
            ad2.addFirst(i);
        }

        // test Iterator
        for (int i : ad1) {
            System.out.print(i + " ");
        }
        System.out.println();
        ad1.printDeque();
        System.out.println();

        System.out.println(ad1.equals(ad2));

        LinkedListDeque<Integer> lld = new LinkedListDeque<>();
        for (int i = 0; i <= n; i++) {
            lld.addLast(i);
        }

        System.out.println(ad1.equals(lld));
    }
}
