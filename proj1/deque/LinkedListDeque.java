package deque;

import java.awt.*;
import java.util.Iterator;

public class LinkedListDeque<T> implements Deque<T>, Iterable<T> {
    private class StuffNode {
        public StuffNode prev;
        public T item;
        public StuffNode next;

        public StuffNode(T i, StuffNode n) {
            item = i;
            next = n;
        }
    }
    private StuffNode sentinel;
    private int size;
    public LinkedListDeque() {
        sentinel = new StuffNode(null, null);
        size = 0;
        /*There's only one sentinel.*/
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
    }

    public void addFirst(T item) {
        StuffNode first = sentinel.next;
        first.prev = new StuffNode(item, first);
        sentinel.next = first.prev;
        first.prev.prev = sentinel;
        size += 1;
    }

    public void addLast(T item) {
        StuffNode last = sentinel.prev;
        last.next = new StuffNode(item, sentinel);
        sentinel.prev = last.next;
        last.next.prev = last;  // important!!
        size += 1;
    }

    public boolean isEmpty() {
         return size == 0;
    }

    public int size() {
        return size;
    }

    public void printDeque() {
        for (StuffNode p = sentinel.next; p.item != null; p = p.next) {
            System.out.print(p.item + " ");
        }
    }

    public T removeFirst() {
        if (isEmpty()) {
            return null;
        }
        StuffNode first = sentinel.next;
        T fitem = first.item;
        //first.next: second node.
        sentinel.next = first.next;
        first.next.prev = sentinel;
        first.item = null;
        first.next = null;
        first.prev = null;
        size -= 1;
        return fitem;
    }

    public T removeLast() {
        if (isEmpty()) {
            return null;
        }
        StuffNode last = sentinel.prev;
        T litem = last.item;

        sentinel.prev = last.prev;
        last.prev.next = sentinel;
        last.item = null;
        last.next = null;
        last.prev = null;
        size -= 1;
        return litem;
    }

    public T get(int index) {
        if (isEmpty() || index < 0 || size < index + 1) {
            return null;
        }
        int iter = 0;   // iterator
        for (StuffNode g = sentinel.next; g.item != null; g = g.next) {
            if (iter == index) {
                return g.item;
            }
            iter += 1;
        }
        return null;
    }

    public T getRecursivehelper(int index, int currindex, StuffNode q) {
        if (index == currindex)
            return q.item;
        return getRecursivehelper(index, currindex+1, q.next);
    }
    public T getRecursive(int index) {
        if (isEmpty() || index < 0 || size < index + 1) {
            return null;
        }
        StuffNode p = sentinel.next;
        return getRecursivehelper(index, 0, p);
    }

    public Iterator<T> iterator() {
        return new LinkedListIterator();
    }

    private class LinkedListIterator implements Iterator<T> {
        private int wizpos;

        public LinkedListIterator() {
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

        Deque<T> temp = (Deque<T>) other;   // Deque! Not LLDeque!
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

        LinkedListDeque<Integer> lld1 = new LinkedListDeque<>();
        for (int i = 0; i <= n; i++) {
            lld1.addLast(i);
        }

        LinkedListDeque<Integer> lld2 = new LinkedListDeque<>();
        for (int i = n; i >= 0; i--) {
            lld2.addFirst(i);
        }

        // test Iterator
        for (int i : lld1) {
            System.out.print(i + " ");
        }
        //lld1.printDeque();
        System.out.println();
        System.out.println(lld1.equals(lld2));

        ArrayDeque<Integer> ad = new ArrayDeque<>();
        for (int i = 0; i <= n; i++) {
            ad.addLast(i);
        }

        System.out.println(lld1.equals(ad));
    }
}
