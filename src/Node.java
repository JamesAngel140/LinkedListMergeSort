/**
 * Standard Cons-cells
 *
 * @author Stefan Kahrs
 * @version 1
 **/

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
        Node start = new Node<>(0, randomList(10));

        System.out.println(start.toString());


        start = start.mergeSort();
        System.out.println(start.toString());


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

        Node<T> Clone = new Node<T>(this.head, this.tail);
        Node start = Clone;
        Node end = Clone;


        for (Node<T> n = Clone; n != null; n = n.tail) {
            if (n.tail != null) {

                T Compare1 = n.head;
                T Compare2 = n.tail.head;

                if (Compare1.compareTo(Compare2) <= 0) {
                    end = n.tail;
                } else {
                    Node<T> holder = new Node(end.head, end.tail);
                    end.tail = null;
                    SemiSorted.add(start);
                    start = holder.tail;
                    end = holder.tail;
                    n = holder;
                }
            } else {
                SemiSorted.add(start);
            }
        }

        //this method should create a queue (of linked lists),
        //split the original (this) list into its sorted non-empty sublists;
        //place those sublists in the queue and return it
        return SemiSorted; //keep compiler happy
    }


    public boolean isSorted() {
        for (Node<T> n = tail; n.tail != null; n = n.tail) {
            if (n.head.compareTo(n.tail.head) < 0) {
                return false;
            }
        }
        return true; //keep compiler happy for now
    }

    public Node<T> merge(Node<T> another) {

        Node item1 = this;
        Node item2 = another;
        Node result = new Node(0, null);

        while (item1.tail != null && item2.tail != null) {
            if(item1.head != null && item2.head != null) {
                if (item1.head.compareTo(item2.head) <= 0) {
                    Node holder = new Node(item1.head, item1.tail);
                    item1.tail = null;
                    result.add(item1);
                    item1 = holder.tail;
                } else if (item1.head.compareTo(item2.head) > 0) {
                    Node holder = new Node(item2.head, item2.tail);
                    item2.tail = null;
                    result.add(item2);
                    item2 = holder.tail;

                }
            }
            else {
                if (item1.head.compareTo(item2.head) <= 0) {
                    result.add(item1);
                    result.add(item2);
                    break;
                }
                else
                {
                    result.add(item2);
                    result.add(item1);
                    break;
                }
            }
        }


        /*
        this method should merge two sorted linked lists
        and return their merged resulting list
        the above are our assumptions about those lists
        */
        return result;
    }

    public boolean add(Node node) {
        for (Node<T> n = this; n != null; n = n.tail) {
            if (n.tail == null) {
                n.tail = node;
                return true;
            }
        }
        return false;
    }

    public Node<T> mergeSort() {
        Queue<Node<T>> items = this.queueSortedSegments();
        while (items.size() > 1) {

            Node item1 = items.remove();
            Node item2 = items.remove();

            item1.merge(item2);
            items.add(item1);

        }
        //this method should sort the list in the following way:
        //split the list up into sorted segments and place these into a queue
        //poll pairs of lists from the queue, merge them, and put their merge
        //back into the queue
        //if there is only one list left in the queue that should be returned
        return items.remove(); //keep compiler happy
    }

}