/*
1312. Minimum Insertion Steps to Make a String Palindrome/h

Given a string s. In one step you can insert any character at any index of the string.

Return the minimum number of steps to make s palindrome.

A Palindrome String is one that reads the same backward as well as forward.
*/

class Solution {
    public int minInsertions(String s) {
        int n = s.length();
        int[][] dp = new int[n + 1][n + 1];
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                if(s.charAt(i) == s.charAt(n - 1 - j)) {
                    dp[i + 1][j + 1] = dp[i][j] + 1;
                } else dp[i + 1][j + 1] = Math.max(dp[i][j + 1], dp[i + 1][j]);
            }
        }
        return n - dp[n][n];
    }
}