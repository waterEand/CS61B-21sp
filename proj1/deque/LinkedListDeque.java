package deque;

public class LinkedListDeque<T> {
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
        for (StuffNode p = sentinel.next; p != sentinel.prev; p = p.next) {
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
        int iter = 0;
        for (StuffNode g = sentinel.next; g.item != null; g = g.next) {
            if (iter == index) {
                return g.item;
            }
            iter += 1;
        }
        return null;
    }


}
