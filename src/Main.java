import LeetCode.utils.ListNode;

import java.util.HashMap;

public class Main {

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        int digitSum = 0;
        ListNode resHead = new ListNode(0);
        ListNode resIterator = resHead;
        while (l1 != null || l2 != null) {
            if (l1 != null) {
                digitSum += l1.val;
                l1 = l1.next;
            }
            if (l2 != null) {
                digitSum += l2.val;
                l2 = l2.next;
            }
            resIterator.next = new ListNode(digitSum % 10);
            resIterator = resIterator.next;
            digitSum = digitSum / 10;
        }
        if (digitSum != 0) resIterator.next = new ListNode(digitSum);
        return resHead.next;
    }

    public int lengthOfLongestSubstring(String s) {
        int left = 0;
        int max = 0;
        //int right=0;
        if (s.length() < 1) return 0;
        HashMap<Character, Integer> dic = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
//          left要保持不断向前运动，因此要注意判断当前位置和存储位置哪个在后边
            if (dic.containsKey(s.charAt(i))) {
                left = Math.max(dic.get(s.charAt(i)) + 1, left);
            }
            dic.put(s.charAt(i), i);
            max = Math.max(max, i - left + 1);
        }
        return max;
    }

    public static void main(String[] args) {
        Main m = new Main();
        m.lengthOfLongestSubstring("abba");
    }
}
