package tester;

import static org.junit.Assert.*;

import org.hamcrest.StringDescription;
import org.junit.Test;
import student.StudentArrayDeque;
import edu.princeton.cs.introcs.StdRandom;

public class TestArrayDequeEC {
    @Test
    public void addFirstTest() {
        StudentArrayDeque<Integer> errarr = new StudentArrayDeque<>();
        ArrayDequeSolution<Integer> corarr = new ArrayDequeSolution<>();
        int i = 0;

        while (i < 1000) {
            int num1 = StdRandom.uniform(100); // a random integer between 0 and 100.
            int num2 = StdRandom.uniform(100);
            errarr.addFirst(num1);
            corarr.addFirst(num1);
            errarr.addFirst(num2);
            corarr.addFirst(num2);
            assertEquals("addLast(" + num1 + ")\naddLast(" + num2 + ")\nget(" + 0 + ")", corarr.get(0), errarr.get(0));
            assertEquals("addLast(" + num1 + ")\naddLast(" + num2 + ")\nget(" + 1 + ")", corarr.get(1), errarr.get(1));
            i += 2;
        }
    }

    @Test
    public void addLastTest() {
        StudentArrayDeque<Integer> errarr = new StudentArrayDeque<>();
        ArrayDequeSolution<Integer> corarr = new ArrayDequeSolution<>();
        int i = 0;

        while (i < 1000) {
            int num1 = StdRandom.uniform(100);
            int num2 = StdRandom.uniform(100);
            errarr.addLast(num1);
            corarr.addLast(num1);
            errarr.addLast(num2);
            corarr.addLast(num2);

            assertEquals("addLast(" + num1 + ")\naddLast(" + num2 + ")\nget(" + i + ")", corarr.get(corarr.size()-1), errarr.get(errarr.size()-1));
            assertEquals("addLast(" + num1 + ")\naddLast(" + num2 + ")\nget(" + (i+1) + ")", corarr.get(corarr.size()-2), errarr.get(errarr.size()-2));
            i += 1;
        }
    }

    @Test
    public void removeFirstTest() {
        StudentArrayDeque<Integer> errarr = new StudentArrayDeque<>();
        ArrayDequeSolution<Integer> corarr = new ArrayDequeSolution<>();

        for (int i = 0; i < 100; i += 1) {
            int num1 = StdRandom.uniform(1000);
            int num2 = StdRandom.uniform(1000);
            errarr.addFirst(num1);
            corarr.addFirst(num1);
            errarr.addFirst(num2);
            corarr.addFirst(num2);

            assertEquals("addFirst(" + num1 + ")\naddFirst(" + num2 + ")\nremoveFirst()", corarr.removeFirst(), errarr.removeFirst());
            assertEquals("addLast(" + num1 + ")\naddLast(" + num2 + ")\nremoveFirst()\nget(" + 0 + ")", corarr.get(0), errarr.get(0));
            assertEquals("addFirst(" + num1 + ")\naddFirst(" + num2 + ")\nremoveFirst()\nget(" + (errarr.size()-1) + ")", corarr.get(corarr.size()-1), errarr.get(errarr.size()-1));
        }
    }

    @Test
    public void removeLastTest() {
        StudentArrayDeque<Integer> errarr = new StudentArrayDeque<>();
        ArrayDequeSolution<Integer> corarr = new ArrayDequeSolution<>();

        for (int i = 0; i < 1000; i += 1) {
            int num1 = StdRandom.uniform(1000);
            int num2 = StdRandom.uniform(1000);
            errarr.addLast(num1);
            corarr.addLast(num1);
            errarr.addLast(num2);
            corarr.addLast(num2);
            //assertEquals(errarr.get(errarr.size()-2), corarr.get(corarr.size()-2));
            assertEquals("addLast(" + num1 + ")\naddLast(" + num2 + ")\nremoveLast()", corarr.removeLast(), errarr.removeLast());
            assertEquals("addLast(" + num1 + ")\naddLast(" + num2 + ")\nremoveLast()\nget(" + (errarr.size()-1) + ")", corarr.get(corarr.size()-1), errarr.get(errarr.size()-1));
        }
    }
}
