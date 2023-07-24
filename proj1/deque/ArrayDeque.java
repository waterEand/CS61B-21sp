package deque;

public class ArrayDeque<T> {
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
        int first = 0;
        if (nextFirst != items.length) {
            first = nextFirst + 1;
        }
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
        int last = items.length - 1;
        if (nextLast != 0) {
            last = nextLast - 1;
        }
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
}
