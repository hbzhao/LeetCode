package Windows;

import java.io.IOException;
import java.util.*;

public class Solution {
    public List<String> letterCombinations(String digits) {
        LinkedList<String> ans = new LinkedList<>();
        if(digits.length()==0) return ans;
        String[] dic={"0", "1", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
        ans.add("");
        for(int i=0;i<digits.length();i++){
            int x=Character.getNumericValue(digits.charAt(i));
            while (ans.peek().length()==i){
                String t=ans.poll();
                for(char c:dic[x].toCharArray()){
                    ans.add(t+c);
                }
            }
        }
        return ans;
    }

    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode start=new ListNode(0);
        ListNode pre=start, pointer=start;
        start.next=head;
        int i=0;
        for(;i<=n;i++){
            if(pointer!=null) pointer=pointer.next;
            else break;
        }
        if(i<n) return head;
        while (pointer!=null){
            pointer=pointer.next;
            pre=pre.next;
        }
        pointer=pre.next.next;
        pre.next=pointer;
        return start.next;
    }

    public boolean isValid(String s) {
        LinkedList<Character> stack=new LinkedList<>();
        boolean ans=true;
        for(char c:s.toCharArray()){
            if(c=='('||c=='['||c=='{'){
                stack.addFirst(c);
            }else {
                if(c==')'){
                    if(!stack.isEmpty()&&stack.peek()=='(') stack.poll();
                    else {
                        ans=false;break;
                    }
                }
                if(c==']'){
                    if(!stack.isEmpty()&&stack.peek()=='[') stack.poll();
                    else {
                        ans=false;break;
                    }
                }
                if(c=='}'){
                    if(!stack.isEmpty()&&stack.peek()=='{') stack.poll();
                    else {
                        ans=false;break;
                    }
                }
            }
        }
        return stack.isEmpty()&&ans;
    }

    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode start=new ListNode(0);
        ListNode pointer=start;
        while (l1!=null&&l2!=null){
            if(l1.val<=l2.val){
                pointer.next=l1;
                l1=l1.next;
            }else {
                pointer.next=l2;
                l2=l2.next;
            }
            pointer=pointer.next;
        }
        while (l1!=null||l2!=null){
            if(l1!=null){
                pointer.next=l1;
                l1=l1.next;
            }
            if(l2!=null){
                pointer.next=l2;
                l2=l2.next;
            }
            pointer=pointer.next;
        }
        return start.next;
    }

    private void help(List<String> ans, String str,int left,int right,int n){
        if(str.length()==n*2){
            ans.add(str);
        }
        if(left<n) help(ans,str+'(',left+1,right,n);
        if(right<left) help(ans,str+')',left,right+1,n);
    }

    public List<String> generateParenthesis(int n) {
        int left=0;
        int right=0;
        List<String> ans=new ArrayList<>();
        help(ans,"",0,0,n);
        return ans;
    }

    public ListNode mergeKLists(ListNode[] lists) {
        PriorityQueue<ListNode> heap=new PriorityQueue<>(new Comparator<ListNode>() {
            @Override
            public int compare(ListNode o1, ListNode o2) {
                return Integer.compare(o1.val,o2.val);
            }
        });

        ListNode ans=new ListNode(0);
        ListNode tail=ans;
        for(ListNode l:lists) {
            if(l!=null)
                heap.add(l);
        }
        while (!heap.isEmpty()){
            ListNode t=heap.poll();
            tail.next=t;
            tail=tail.next;
            if(t.next!=null) heap.add(t.next);
        }
        return ans.next;
    }

    public ListNode swapPairs(ListNode head) {
        if(head==null) return head;
        ListNode ans=new ListNode(0);
        ans.next=head;
        ListNode tail=ans;
        ListNode front=head;
        ListNode back=head.next;
        ListNode next;
        while (back!=null){
            next=back.next;
            tail.next=back;
            tail=tail.next;
            tail.next=front;
            tail=tail.next;
            front.next=next;
            if(next!=null){
                front=next;
                back=front.next;
            }else break;
        }
        return ans.next;
    }


    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode curr=head;
        int count=0;
        while (curr!=null&&count<k){
            curr=curr.next;
            count++;
        }

        if(count==k){
            curr=reverseKGroup(curr,k);

            while (count>0){
                count--;
                ListNode temp=head.next;
                head.next=curr;
                curr=head;
                head=temp;
            }
            head=curr;
        }
        return head;
    }

    public int removeDuplicates(int[] nums) {
        int left=0;
        int right=left+1;
        if(nums.length<2) return nums.length;

        while (right<nums.length){
            if(nums[left]!=nums[right]){
                nums[left+1]=nums[right];
                left++;
            }
            right++;
        }
        return left+1;
    }

    public int removeElement(int[] nums, int val) {
        int left=0;
        int right=nums.length;
        if(nums.length==0) return 0;
        if(nums.length<2) return nums[0]==val?0:1;
        while (left<right&&nums[left]!=val) left++;
        while (left<right&&nums[right-1]==val) right--;
        while (left<right){
            nums[left]=nums[right-1];
            nums[right-1]=val;
            left++;
            right--;
            while (left<right&&nums[left]!=val) left++;
            while (left<right&&nums[right-1]==val) right--;
        }
        return right;
    }

    public int searchInsert(int[] nums, int target) {
        int left=0;
        int right=nums.length-1;
        int ans=-1;
        int mid=(left+right)/2;
        while (left<right){
            if(nums[mid]<target){
                left=mid+1;
            }else if(nums[mid]>target){
                right=mid-1;
            }else {
                ans=mid;
                return ans;
            }
            mid=(left+right)/2;
        }
        ans=nums[left]>=target?left:left+2;
        return ans;
    }
/**
    public List<Integer> findSubstring1(String S, String[] L) {
        List<Integer> res = new LinkedList<>();
        if (L.length == 0 || S.length() < L.length * L[0].length())   return res;
        int N = S.length(), M = L.length, K = L[0].length();
        Map<String, Integer> map = new HashMap<>(), curMap = new HashMap<>();
        for (String s : L) {
            if (map.containsKey(s))   map.put(s, map.get(s) + 1);
            else                      map.put(s, 1);
        }
        String str = null, tmp = null;
        for (int i = 0; i < K; i++) {
            int count = 0;  // remark: reset count
            for (int l = i, r = i; r + K <= N; r += K) {
                str = S.substring(r, r + K);
                if (map.containsKey(str)) {
                    if (curMap.containsKey(str))   curMap.put(str, curMap.get(str) + 1);
                    else                           curMap.put(str, 1);

                    if (curMap.get(str) <= map.get(str))    count++;
                    while (curMap.get(str) > map.get(str)) {
                        tmp = S.substring(l, l + K);
                        curMap.put(tmp, curMap.get(tmp) - 1);
                        l += K;
                        if (curMap.get(tmp) < map.get(tmp)) count--;
                    }
                    if (count == M) {
                        res.add(l);
                        tmp = S.substring(l, l + K);
                        curMap.put(tmp, curMap.get(tmp) - 1);
                        l += K;
                        count--;
                    }
                }else {
                    curMap.clear();
                    count = 0;
                    l = r + K;
                }
            }
            curMap.clear();
        }
        return res;
    }

**/

    public List<Integer> findSubstring(String s, String[] words) {
        HashMap<String,Integer> map=new HashMap<>();
        HashMap<String,Integer> curMap=new HashMap<>();
        List<Integer> ans=new ArrayList<>();
        for(String str:words){
            map.put(str,map.getOrDefault(str,0)+1);
        }
        int K=words[0].length();
        int N=s.length();
        int M=words.length;
        for(int i=0;i<K;i++){
            int count=0;
            for(int l=i,r=i;r<=N-K;r+=K){

                String t=s.substring(r,r+K);
                if(map.containsKey(t)){
                    curMap.put(t,curMap.getOrDefault(t,0)+1);
                    if(curMap.get(t)<=map.get(t)) count++;
                    while (curMap.get(t)>map.get(t)){
                        String tmp=s.substring(l,l+K);
                        curMap.put(tmp,curMap.get(tmp)-1);
                        l+=K;
                        if (curMap.get(tmp) < map.get(tmp)) count--;
                    }
                    if(count==M){
                        ans.add(l);
                        String tmp=s.substring(l,l+K);
                        curMap.put(tmp,curMap.get(tmp)-1);
                        l+=K;
                        count--;
                    }
                }else {
                    curMap.clear();
                    count=0;
                    l=r+K;
                }
            }
            curMap.clear();
        }
        return ans;
    }

    public void nextPermutation(int[] nums) {
        int left=-1,right=left+1;
        int tail=nums.length-1;
        for(int i=nums.length-1;i>=1;i--){
            if(nums[i]>nums[i-1]){
                left=i-1;
                right=i;
                break;
            }
        }
        for(;left>=0&&tail>left;tail--){
            if(nums[tail]>nums[left]) {
                int tmp=nums[left];
                nums[left]=nums[tail];
                nums[tail]=tmp;
                break;
            }
        }
        right=nums.length-1;
        left++;
        while (left<right){
            int tmp=nums[left];
            nums[left]=nums[right];
            nums[right]=tmp;
            left++;
            right--;

        }
    }

    public int longestValidParentheses(String s) {
        LinkedList<Integer> lStack=new LinkedList<>();
        int max=0;
        for(int i=0;i<s.length();i++){
            if(s.charAt(i)=='(') lStack.addFirst(i);
            else if(!lStack.isEmpty()&&s.charAt(lStack.peek())=='(') lStack.poll();
            else lStack.addFirst(i);
        }
        if(lStack.isEmpty()) max=s.length();
        else {
            int right = s.length();
            int left = 0;
            while (!lStack.isEmpty()) {
                left = lStack.poll();
                max = Math.max(max, right - left - 1);
                right = left;
            }
            max=Math.max(max,right);
        }
        return max;
    }

    private void helpPermute(List<List<Integer>> ans,ArrayList<Integer> tmp,int[] nums){
        if(tmp.size()==nums.length){
            ans.add(new ArrayList<>(tmp));
        }else {
            for(int i=0;i<nums.length;i++){
                if(tmp.contains(nums[i])) continue;
                tmp.add(nums[i]);
                helpPermute(ans,tmp,nums);
                tmp.remove(tmp.size()-1);
            }
        }
    }

    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> ans=new ArrayList<>();
        helpPermute(ans,new ArrayList<>(),nums);
        return ans;
    }

    public int search(int[] nums, int target) {
        int left=0;
        int right=nums.length-1;
        while (left<=right){
            int mid=(left+right)/2;
            if(nums[mid]==target) return mid;

            //判断mid落在哪一个递增序列内
            if(nums[left]<=nums[mid]){
                if(nums[mid]>target&&target>=nums[left])
                    right=mid-1;
                else
                    left=mid+1;
            }else {
                if(nums[mid]<target&&target<=nums[right])
                    left=mid+1;
                else
                    right=right-1;
            }
        }
        return -1;
    }

/********************************************************************/
//测试环境
    public static int[] stringToIntegerArray(String input) {
        input = input.trim();
        input = input.substring(1, input.length() - 1);
        if (input.length() == 0) {
            return new int[0];
        }

        String[] parts = input.split(",");
        int[] output = new int[parts.length];
        for(int index = 0; index < parts.length; index++) {
            String part = parts[index].trim();
            output[index] = Integer.parseInt(part);
        }
        return output;
    }

    public static ListNode stringToListNode(String input) {
        // Generate array from the input
        int[] nodeValues = stringToIntegerArray(input);

        // Now convert that list into linked list
        ListNode dummyRoot = new ListNode(0);
        ListNode ptr = dummyRoot;
        for(int item : nodeValues) {
            ptr.next = new ListNode(item);
            ptr = ptr.next;
        }
        return dummyRoot.next;
    }

    public static String listNodeToString(ListNode node) {
        if (node == null) {
            return "[]";
        }

        String result = "";
        while (node != null) {
            result += Integer.toString(node.val) + ", ";
            node = node.next;
        }
        return "[" + result.substring(0, result.length() - 2) + "]";
    }

    public static void main(String[] args) throws IOException {
        int[] nums={1,3,5,6};
        Solution s=new Solution();
        s.searchInsert(nums,5);
        String[] array={"aa","aa","aa"};
        s.longestValidParentheses("(()(((()");
    }

}
