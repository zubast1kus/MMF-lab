package lab1;

public class Main {

    public static void main(String... args) {
        final int min = 50;
        final int max = 50_000_000;
        final int n = rnd(min, max);
        long startTime = System.currentTimeMillis();

        int arr[];
        int sum = 0;
        int averge = 0;
        arr = new int [n];
        for (int i=0;i<arr.length;i++)
            arr[i] = (int) ( Math.random() * n);
        for (int i: arr) {
            sum = sum + i;
            averge = sum/arr.length;
        }
        long timeSpent = System.currentTimeMillis() - startTime;
        System.out.println("Число элементов массива равна: " + n);
        System.out.println("Сумма элементов массива равна: " + sum);
        System.out.println("Среднее арифметическое элементов массива равна: " + averge);
        System.out.println("программа выполнялась " + timeSpent + " миллисекунд");

    }

    public static int rnd(int min, int max) {
        max -= min;
        return (int) (Math.random() * ++max) + min;
    }

}



