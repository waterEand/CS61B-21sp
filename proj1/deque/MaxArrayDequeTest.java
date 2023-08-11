package deque;

import org.junit.Test;
import static org.junit.Assert.*;
import java.util.Comparator;

/** Performs some basic max array tests. */
public class MaxArrayDequeTest {

    @Test
    public void main() {
        Comparator<Integer> cmp = new Comparator<>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                if (o1 > o2) {
                    return 1;
                } else if (o1 < o2) {
                    return -1;
                } else {
                    return 0;
                }
            }
        };

        MaxArrayDeque mad1 = new MaxArrayDeque(cmp);

        int n = 99;

        for (int i = n; i >= 0; i--) {
            mad1.addFirst(i);
        }

        assertEquals(99, mad1.max());
        assertEquals(99, mad1.max(cmp));

        System.out.print("Printing out the first deque: ");
        mad1.printDeque();
        System.out.println();


        Comparator<String> cmp2 = new Comparator<>() {
            //重写排序方法
            @Override
            public int compare(String strA, String strB) {
                return strA.compareTo(strB);
            }
        };

        MaxArrayDeque mad2 = new MaxArrayDeque(cmp2);

        mad2.addFirst("front");
        mad2.addLast("middle");
        mad2.addLast("back");

        assertEquals("middle", mad2.max());
        assertEquals("middle", mad2.max(cmp2));

        System.out.print("Printing out the second deque: ");
        mad2.printDeque();
    }
}
