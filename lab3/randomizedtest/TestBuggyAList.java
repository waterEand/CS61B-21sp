package randomizedtest;

import edu.princeton.cs.algs4.StdRandom;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Created by hug.
 */
public class TestBuggyAList {
  // YOUR TESTS HERE
    @Test
    public void testThreeAddThreeRemove() {
        AListNoResizing<Integer> AList = new AListNoResizing<>();
        BuggyAList<Integer> BAList = new BuggyAList<>();

        for (int i = 4; i < 7; i += 1) {
            AList.addLast(i);
            BAList.addLast(i);
        }

        for (int j = 0; j < 3; j += 1) {
            assertEquals(AList.removeLast(), BAList.removeLast());
        }
    }

    @Test
    public void randomizedTest() {
        AListNoResizing<Integer> cL = new AListNoResizing<>();
        BuggyAList<Integer> bL = new BuggyAList<>();

        int N = 5000;
        for (int i = 0; i < N; i += 1) {
            int operationNumber = StdRandom.uniform(0, 4);
            if (operationNumber == 0) {
                // addLast
                int randVal = StdRandom.uniform(0, 100);
                cL.addLast(randVal);
                bL.addLast(randVal);
                // System.out.println("addLast(" + randVal + ")");
            } else if (operationNumber == 1) {
                // size
                int size = cL.size();
                int sizeb = bL.size();
                // System.out.println("size: " + size + " sizeb: " + sizeb);
                assertEquals(size, sizeb);
            } else if (operationNumber == 2) {
                // getLast
                if (cL.size() > 0) {
                    int res =  cL.getLast();
                    int resb = bL.getLast();
                    // System.out.println("getLast: " + res + " getLastb: " + resb);
                    assertEquals(res, resb);
                }
            } else if (operationNumber == 3) {
                // removeLast
                if (cL.size() > 0) {
                    int res = cL.removeLast();
                    int resb = bL.removeLast();
                    // System.out.println("removeLast: " + res + " removeLastb: " + resb);
                    assertEquals(res, resb);
                }
            }
        }
    }
}
