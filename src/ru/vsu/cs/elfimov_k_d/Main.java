package ru.vsu.cs.elfimov_k_d;
import java.util.Comparator;

public class Main {
    public static void main(String[] args) throws Exception {
        MyLinkedList<Integer> list = new MyLinkedList<>();
        for (int i = 0; i < 5; i++) {
            list.addFirst(i);
        }
        for (Object integer : list) {
            System.out.print(integer + " ");
        }
        System.out.println();
        list.bubbleSorting(new Comparator<Integer>() {
            @Override
            public int compare(Integer o, Integer t1) {
                return o.compareTo(t1);
            }
        });

        for (Integer integer : list) {
            System.out.print(integer + " ");
        }
    }
}
