package edu.udayton.string;

public class LongestPalindrome {

    public static void main(String[] args) {
        LongestPalindrome pal = new LongestPalindrome();
        System.out.println(pal.longestPalindrome("abcdcbaef"));
    }

    public String longestPalindrome(String s) {
        if (s == null || s.length() == 0) {
            return "";
        }
        int start = 0, end = 0;
        for (int i = 0; i < s.length(); i++) {
            int len1 = expandCenter(s, i ,i);
            int len2 = expandCenter(s, i ,i + 1);
            int max = len1 > len2 ? len1 : len2;
            if (max > (end - start)) {
                start = i - (max - 1) / 2;
                end = i + max / 2;
            }
        }
        return s.substring(start, end + 1);
    }

    private int expandCenter(String s, int start, int end) {
        while (start >= 0 && end < s.length() && s.charAt(start) == s.charAt(end)) {
            start--;
            end++;
        }
        return end - start - 1;
    }

}
