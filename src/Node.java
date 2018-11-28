
/**
 * Standard Cons-cells
 *
 * @author Stefan Kahrs
 * @version 1
 * <p>
 * <p>
 * type parameter T
 * Below is an explanation for the fancy stuff
 * attached to that type parameter.
 * For the assessment you do not need to know this,
 * but if you are curious: read on!
 * <p>
 * Because we want to be able to compare the elements
 * of the list with one another, we require that
 * class T implements the Comparable interface.
 * That interface has itself a type parameter, which
 * gives you what these values can be compared to.
 * The reason this is (in the most general case) not just T
 * itself is the following scenario:
 * class X implements the interface,
 * so we can compare Xs with Xs, then we define a subclass Y of X,
 * so it inherits the compareTo method from X,
 * but Ys are now compared with Xs.
 */

/**
 * type parameter T
 * Below is an explanation for the fancy stuff
 * attached to that type parameter.
 * For the assessment you do not need to know this,
 * but if you are curious: read on!
 *
 * Because we want to be able to compare the elements
 * of the list with one another, we require that
 * class T implements the Comparable interface.
 * That interface has itself a type parameter, which
 * gives you what these values can be compared to.
 * The reason this is (in the most general case) not just T
 * itself is the following scenario:
 * class X implements the interface,
 * so we can compare Xs with Xs, then we define a subclass Y of X,
 * so it inherits the compareTo method from X,
 * but Ys are now compared with Xs.
 */

import java.util.*;
import java.util.Queue;
import java.util.Random;

public class Node<T extends Comparable<? super T>> {

    protected T head;
    protected Node<T> tail;

    public Node(T h, Node<T> t) {
        head = h;
        tail = t;
    }

    public static void main(String args[]) {
        Node start = new Node<>(0, null);
        start.tail = randomList(10);

        System.out.println(start.toString());
        System.out.println(start.isSorted());
        System.out.println(start.queueSortedSegments());

    }


    static public Node<Integer> randomList(int n) {
        //for testing purposes we want some random lists to be sorted
        //the list is n elements long
        //the elements of the random list are numbers between 0 and n-1
        Random r = new Random();
        Node<Integer> result = null;
        int k = n;
        while (k > 0) {
            result = new Node<Integer>(r.nextInt(n), result);
            k--;
        }
        return result;
    }

    static public void test(int n) {
        //this method should do the following:
        //1. create a random linked list of length n
        //2. output it
        //3. report whether the 'isSorted' method thinks the list is sorted or not
        //4. sort the list using mergeSort
        //5. output the sorted list
        //6. report whether the 'isSorted' method thinks that list is sorted or not
    }

    public String toString() {
        if (tail == null) return "[" + head + "]";
        return "[" + head + tail.tailString();
    }

    private String tailString() {
        String initialPart = "," + head;
        if (tail == null) return initialPart + "]";
        return initialPart + tail.tailString();
    }

    public int length() {
        int result = 1;
        for (Node<T> n = tail; n != null; n = n.tail) {
            result++;
        }
        return result;
    }

    public Queue<Node<T>> queueSortedSegments() {
        Queue<Node<T>> SemiSorted = new LinkedList<Node<T>>();

        Node start = this;
        Node end = null;

        for (Node<T> n = tail; n != null; n = n.tail) {
            if (n.head.compareTo(n.tail.head) < 0) {
                end = tail;
            } else {
                Node holder = end;
                end.tail = null;
                SemiSorted.add(start);
                start = holder;
            }

        }

        //this method should create a queue (of linked lists),
        //split the original (this) list into its sorted non-empty sublists;
        //place those sublists in the queue and return it
        return SemiSorted; //keep compiler happy
    }

    public boolean isSorted() {
        for (Node<T> n = tail; n != null; n = n.tail) {
            if (head.compareTo(tail.head) < 0) {
                return false;
            }
        }
        return true; //keep compiler happy for now
    }

    public Node<T> merge(Node<T> another) {
        //this method should merge two sorted linked lists
        //and return their merged resulting list
        assert isSorted();
        assert another == null || another.isSorted();
        //the above are our assumptions about those lists
        return this;
    }

    public Node<T> mergeSort() {
        //this method should sort the list in the following way:
        //split the list up into sorted segments and place these into a queue
        //poll pairs of lists from the queue, merge them, and put their merge
        //back into the queue
        //if there is only one list left in the queue that should be returned
        return this; //keep compiler happy
    }

}