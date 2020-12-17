package lab4;

import helpers.RandomGenerators;
import lab4.sort.BubbleSort;
import lab4.sort.InsertionSort;

public class Main {
    public static void main(String[] args) {
        int n = 500;
        int [] listBubbleOpt = new int[n];
        int [] listBubble = new int[n];
        int [] listInsert = new int[n];

        for (int i =0; i<n; i++) {
            int r = RandomGenerators.getRandomInt(1, 10_000);
            listBubble[i] = r;
            listBubbleOpt[i] = r;
            listInsert[i] = r;
        }

        long start = System.nanoTime();
        System.out.println("Bubble");
        BubbleSort.bubbleSort(listBubble);
        long end = System.nanoTime();
        System.out.println("Time: " +(end - start));

        System.out.println("Insertion");
        start = System.nanoTime();
        InsertionSort.insertionSort(listInsert);
        end = System.nanoTime();
        System.out.println("Time: " +(end - start));

    }
}
