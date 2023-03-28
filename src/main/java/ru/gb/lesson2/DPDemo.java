package ru.gb.lesson2;

import java.util.concurrent.ThreadLocalRandom;

public class DPDemo {

    public static void main(String[] args) {
        /**
         * n = 10, m = 10
         * [ 6] [-5] [ 3] [-2] [-4] [ 4] [-1] [-5] [ 6] [-7]
         * [ 2] [-6] [ 7] [-8] [ 7] [ 1] [-6] [-6] [ 0] [-7]
         * [ 4] [ 3] [ 4] [-1] [-2] [ 2] [ 5] [ 5] [-1] [ 1]
         * [ 1] [ 1] [-4] [ 2] [-4] [-5] [ 3] [-2] [-8] [-5]
         * [-5] [-7] [ 1] [-1] [-6] [ 2] [-4] [ 9] [ 1] [ 3]
         * [-1] [-1] [ 5] [ 7] [ 0] [ 8] [-5] [-1] [ 5] [ 4]
         * [-9] [-2] [-3] [-2] [ 0] [ 7] [ 0] [ 2] [-3] [ 2]
         * [ 6] [ 4] [ 1] [ 7] [-1] [-5] [ 2] [ 8] [-9] [-3]
         * [ 4] [ 2] [-2] [ 2] [ 6] [ 1] [ 5] [ 7] [ 5] [-2]
         * [-3] [-7] [-4] [-5] [ 9] [-7] [ 7] [-3] [-4] [ 5]
         *
         * [6] [1] [4]  [2]
         * [8] [2] [11] [3]
         * [12]
         * [13]
         *
         */
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                int random = ThreadLocalRandom.current().nextInt(-9, 10);
                System.out.print(random >= 0 ? "[ " + random + "] " : "[" + random + "] ");
            }
            System.out.println();
        }
        // (i, j) = (i - 1, j) + (i, j - 1)
        // n = 2,  m = 2 => 2
        // n = 3,  m = 2 => 3
        // n = 2,  m = 3 => 3
        // n = 3,  m = 3 => 6
        // n = 10, m = 1 => 1
//        System.out.println(findWayCount(2, 2));
//        System.out.println(findWayCount(2, 3));
//        System.out.println(findWayCount(3, 2));
//        System.out.println(findWayCount(3, 3));
//        System.out.println(findWayCount(1, 10));
    }

    static int findBestWay(int[][] map) {
        int n = map.length;
        int m = map[0].length;
        int[][] dp = new int[n] [m];
        dp[0][0] = map[0][0];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (i == 0 && j == 0) {
                    continue;
                } else if (i == 0) { // j != 0
                    dp[i][j] = dp[i][j - 1] + map[i][j];
                    continue;
                } else if (j == 0) { // i != 0
                    dp[i][j] = dp[i - 1][j] + map[i][j];
                    continue;
                }

                dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]) + map[i][j];
            }
        }
        return dp[n - 1][m - 1];
    }

    static int findWayCount(int n, int m) {
        // dynamic programming
        int[][] dp = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (i == 0 || j == 0) {
                    dp[i][j] = 1;
                    continue;
                }

                dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
            }
        }

        return dp[n - 1][m - 1];
    }



}
