package ru.gb;

public class Main {

    public static void main(String[] args) {
        // 0 -> 2 -> 4 -> 6
        // 0 -> 1 -> 2 -> 3 -> 5 -> 7 -> 6
        // Количество путей, которыми лягушонок может пропрыгать из начала в конец по маршруту из n камней.
        // Он может прыгать на 1 камень вперед, а может на 2 вперед. n >= 2

        // n = 3 => 2
        // 0 1 2
        // 0 2

        // n = 4 => 3
        // 0 1 2 3
        // 0 2 3
        // 0 1 3
        //


        // n = 5 => 5
        // 0 1 2 3 4
        // 0 2 3 4
        // 0 1 3 4
        // 0 1 2 4
        // 0 2 4

        // 1, 2, 3, ... n - 1
        // n
        int n = 6;
        int[] counts = new int[n + 1];
        counts[2] = 1;
        counts[3] = 2;
        for (int i = 4; i <= n; i++) {
            counts[i] = counts[i - 1] + counts[i - 2];
        }
        System.out.println(counts[n]);
    }

//    static int count(int n) {
//        if (n == 2) {
//            return 1;
//        } else if (n == 3) {
//            return 2;
//        }
//
//        return count(n - 1) + count(n - 2) + 2;
//    }

}