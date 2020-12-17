package lab3;

import helpers.RandomGenerators;

public class Main {

    static int recSearch(int arr[], int l, int r, int x) {
        if (r < l)
            return -1;
        if (arr[l] == x)
            return l;
        if (arr[r] == x)
            return r;
        return recSearch(arr, l + 1, r - 1, x);
    }

    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        int n = 1000000;
        int[] list = new int[n];

        for (int i = 0; i < n; i++) {
            int r = RandomGenerators.getRandomInt(1, 100);
            list[i] = r;
        }

        int x = 3;

        int index = recSearch(list, 0, list.length - 1, x);
        if (index != -1)
            System.out.println("Element " + x + " is present at index " +
                    index);
        else
            System.out.println("Element " + x + " is not present");
        long timeSpent = System.currentTimeMillis() - startTime;
        System.out.println("программа выполнялась " + timeSpent + " миллисекунд");

    }

}
