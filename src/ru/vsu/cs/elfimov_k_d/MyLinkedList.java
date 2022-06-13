package ru.vsu.cs.elfimov_k_d;

import java.util.Comparator;
import java.util.Iterator;

public class MyLinkedList<T> implements Iterable<T> {
    public MyLinkedList() {

    }
    public MyLinkedList(T[] array) {
        for (T t : array) {
            this.addLast(t);

        }
        count = array.length;
    }

    public static boolean isDigit(String s){
        try {
            Integer.parseInt(s);
            return true;
        }
        catch (NumberFormatException e){
            return false;
        }
    }

    private int size() {
        return count;
    }

    private class ListNode<T> {
        public T value;
        public ListNode<T> next;

        public ListNode(T value, ListNode<T> next) {
            this.value = value;
            this.next = next;
        }
    }

    private ListNode<T> head = null;
    private ListNode<T> tail = null;

    private int count = 0;

    public void addFirst(T value) {
        head = new ListNode<>(value, head);
        if (count == 0) {
            tail = head;
        }
        count++;
    }

    public void addLast(T value) {
        ListNode<T> newNode = new ListNode<>(value, null);
        if (count > 0) {
            tail.next = newNode;
        } else {
            head = newNode;
        }
        tail = newNode;
        count++;
    }

    private void emptyError() throws Exception {
        if (count == 0) {
            throw new Exception("List is empty");
        }
    }

    public T getFirst() throws Exception {
        emptyError();
        return head.value;
    }

    public T getLast() throws Exception {
        emptyError();
        return tail.value;
    }

    private ListNode<T> getNode(int index) throws Exception {
        if (index < 0 || index >= count) {
            throw new Exception("Wrong index");
        }
        ListNode<T> curr = head;
        for (int i = 0; i < index; i++) {
            curr = curr.next;
        }
        return curr;
    }

    public T get(int index) throws Exception {
        return getNode(index).value;
    }

    public T removeFirst() throws Exception {
        T value = getFirst();
        head = head.next;
        count--;
        if (count == 0) {
            tail = null;
        }
        return value;
    }

    public T removeLast() throws Exception {
        T value = getLast();
        count--;
        if (count == 0) {
            head = tail = null;
        } else {
            tail = getNode(count - 1);
            tail.next = null;
        }
        return value;
    }

    public T remove(int index) throws Exception {
        if (index < 0 || index >= count) {
            throw new Exception("Wrong index");
        }

        T value;
        if (index == 0) {
            value = head.value;
            head = head.next;
            if (head == null) {
                tail = null;
            }
        } else {
            ListNode<T> prev = getNode(index - 1);
            value = prev.next.value;
            prev.next = prev.next.next;
            if (prev.next == null) {
                tail = prev;
            }
        }
        count--;
        return value;
    }

    public void insert(int index, T value) throws Exception {
        if (index < 0 || index > count) {
            throw new Exception("Wrong index");
        }
        if (index == 0) {
            addFirst(value);
        } else {
            ListNode<T> prev = getNode(index - 1);
            prev.next = new ListNode<>(value, prev.next);
            count++;
        }
    }

    public void clear() {
        head = tail = null;
        count = 0;
    }

    public int getCount() {
        return count;
    }

    @Override
    public Iterator<T> iterator() {
        class LinkedListIterator implements Iterator<T> {
            ListNode<T> curr;

            public LinkedListIterator(ListNode<T> head) {
                curr = head;
            }

            @Override
            public boolean hasNext() {
                return curr != null;
            }

            @Override
            public T next() {
                T result = curr.value;
                curr = curr.next;
                return result;
            }
        }
        return new LinkedListIterator(head);
    }

    public String[] toArray() {
        String[] array = new String[count];
        ListNode<T> term = head;
        for (int i = 0; i < count; i++) {
            array[i] = term.value.toString();
            term = term.next;
        }
        return array;
    }

    public void bubbleSorting(Comparator<T> comparator) throws Exception {
        ListNode<T> prev;
        ListNode<T> currentLink;
        ListNode<T> next;

        for (int i = 0; i < count - 1; i++) {
            prev = head;
            currentLink = head;
            next = head.next;
            if (currentLink.next != null && comparator.compare(currentLink.value, currentLink.next.value) > 0) {
                currentLink.next = currentLink.next.next;
                next.next = currentLink;
                head = next;
                next = currentLink.next;
                prev = head;
            } else {
                currentLink = next;
                next = next.next;
            }
            int j = count - 2;
            while (j > i) {
                if (currentLink.next != null && comparator.compare(currentLink.value, currentLink.next.value) > 0) {
                    currentLink.next = currentLink.next.next;
                    next.next = currentLink;
                    prev.next = next;
                    prev = prev.next;
                    next = currentLink.next;
                } else {
                    prev = currentLink;
                    currentLink = next;
                    next = next.next;
                }
                j--;
            }
            if (currentLink.next != null && next == tail && comparator.compare(currentLink.value, currentLink.next.value) > 0) {
                tail = currentLink;
                next.next = currentLink;
                prev.next = next;
                currentLink.next = null;
            }
        }
    }
}
