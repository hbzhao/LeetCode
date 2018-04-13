package FirstTen;

import java.util.*;

public class Solution {
    public int[] twoSum(int[] nums, int target) {
        int[] ans=new int[2];
        HashMap<Integer,Integer> map=new HashMap<>();
        for(int i=0;i<nums.length;i++){
            map.put(nums[i],i);
        }

        for(int i=0;i<nums.length;i++){
            int t=target-nums[i];
            if(map.containsKey(t)){
                if(map.get(t)!=i){
                    ans[0]=i;
                    ans[1]=map.get(t);
                    break;
                }
            }
        }

        return ans;
    }

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        int sum=0;
        ListNode ans = new ListNode(0);
        ListNode pointer=ans;
        while (l1!=null||l2!=null){
            sum/=10;
            if(l1!=null)
            {
                sum+=l1.val;
                l1=l1.next;
            }
            if(l2!=null){
                sum+=l2.val;
                l2=l2.next;
            }
            pointer.next=new ListNode(sum%10);
            pointer=pointer.next;
        }
        if(sum/10>0){
            pointer.next=new ListNode(sum/10);
        }
        return ans.next;
    }

    //i代表左指针，j代表右指针
    public int lengthOfLongestSubstring(String s) {
        HashMap<Character,Integer> dic=new HashMap<>();
        int sum=0;
        int max=0;
        for(int i=0, j=0;i<s.length();i++){
            if(dic.containsKey(s.charAt(i))){
                j=Math.max(j,dic.get(s.charAt(i))+1);
            }
            dic.put(s.charAt(i),i);
            max=Math.max(max,i-j+1);
        }
        return max;
    }

    private double findKth(int[] a,int astart,int[] b,int bstart,int k){
        //当数组一耗尽时从数组二中取值，反之已然
        if(astart>a.length-1) return b[bstart+k-1];
        if(bstart>b.length-1) return a[astart+k-1];

        if(k==1) return Math.min(a[astart],b[bstart]);

        int aMidK =Integer.MAX_VALUE;
        int bMidK =Integer.MAX_VALUE;

        if(astart+k/2-1<a.length) aMidK=a[astart+k/2-1];
        if(bstart+k/2-1<b.length) bMidK=b[bstart+k/2-1];

        if(aMidK<bMidK)
            return findKth(a,astart+k/2,b,bstart,k-k/2);
        else
            return findKth(a,astart,b,bstart+k/2,k-k/2);
    }


    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int la=nums1.length;
        int lb=nums2.length;
        int l=(la+lb+1)/2;
        int r=(la+lb+2)/2;

        return (findKth(nums1,0,nums2,0,l)+findKth(nums1,0,nums2,0,r))/2.0;
    }

    public String longestPalindrome(String s) {
        int lenA=0;
        int lenB=0;
        int start=0;
        int max=0;
        for(int i=0;i<s.length();i++){
            lenA=isPalindrome(s,i,i);
            lenB=isPalindrome(s,i,i+1);
            int temp=Math.max(lenA,lenB);
            if(max<temp){
                start=lenA>lenB?(i-lenA/2):(i-lenB/2+1);
                max=temp;
            }
        }
        return s.substring(start,start+max);
    }
    private int isPalindrome(String s,int i,int j){

        while (i>=0&&j<s.length()){
            if(s.charAt(i)!=s.charAt(j))
                break;
            i--;
            j++;
        }
        return j-i-2+1;
    }

    public String convert(String s, int numRows) {
        StringBuilder ans=new StringBuilder();
        for(int i=0;i<numRows;i++){
            int j=i;
            int length=0;
            if(i==0||i==numRows-1) length=2*numRows-2;
            else length=numRows-1;
            while (j<s.length()){
                ans.append(s.charAt(j));
                j+=length;
            }
        }
        return ans.toString();
    }

    public int reverse(int x) {
        long ans=0;
        boolean flag=false;
        if(x<0){
            flag=true;
            x=Math.abs(x);
        }
        while (x>0){
            ans=ans*10+x%10;
            x=x/10;
            if(ans>Integer.MAX_VALUE)
                return 0;
        }

        return flag?(int)-ans:(int)ans;
    }

    public int myAtoi(String str) {
        long ans=0;
        boolean flag=false;
        int t=0;
        int index=0;
        if(str.length()==0) return 0;
        while (index<str.length()&&str.charAt(index)==' ') index++;
        while (index<str.length()&&(str.charAt(index)=='-'||str.charAt(index)=='+')) {
            if(str.charAt(index)=='-')
                flag = true;
            index++;
            t++;
            if(t>1) return 0;
        }

        while (index<str.length()){
            if(str.charAt(index)>'9'||str.charAt(index)<'0') break;
            ans=ans*10+str.charAt(index)-'0';
            if(ans>Integer.MAX_VALUE) return flag?Integer.MIN_VALUE:Integer.MAX_VALUE;
            index++;
        }
        return flag?(int)-ans:(int)ans;
    }

    public boolean isPalindrome(int x) {
        if(x<0||x/10==0) return false;
        int ans=0;
        int t=x;
        while (t>0){
            ans=ans*10+t%10;
            t=t/10;
        }
        return ans==x;
    }

    public boolean isMatchx(String s, String p) {
        boolean[][] dp=new boolean[s.length()+1][p.length()+1];
        int i=0;
        int j=0;
        for(int d=0;d<s.length();d++) dp[d][0]=true;
        for(int d=0;d<p.length();d++) dp[0][d]=true;
        while (j<p.length()&&i<s.length()) {
            if (p.charAt(j) == '*') {
                if (s.charAt(i) == p.charAt(j - 1)) {
                    dp[i + 1][j + 1] = dp[i][j];
                    dp[i + 1][j] = dp[i][j];
                    i++;
                } else {
                    dp[i + 1][j + 1] = dp[i][j];
                    j++;
                }
            } else {
                if (i >= s.length() || j >= p.length()) break;
                if (p.charAt(j) == s.charAt(i) || p.charAt(j) == '.') {
                    dp[i + 1][j + 1] = dp[i][j];
                    i++;
                    j++;
                } else {
                    dp[i + 1][j + 1] = false;
                    j++;
                }
            }
        }
        return dp[s.length()][p.length()];
    }

    public boolean isMatch(String s, String p) {

        if (s == null || p == null) {
            return false;
        }
        boolean[][] dp = new boolean[s.length()+1][p.length()+1];
        dp[0][0] = true;
        for (int i = 0; i < p.length(); i++) {
            if (p.charAt(i) == '*' && dp[0][i-1]) {
                dp[0][i+1] = true;
            }
        }
        for (int i = 0 ; i < s.length(); i++) {
            for (int j = 0; j < p.length(); j++) {
                if (p.charAt(j) == '.') {
                    dp[i+1][j+1] = dp[i][j];
                }
                if (p.charAt(j) == s.charAt(i)) {
                    dp[i+1][j+1] = dp[i][j];
                }
                if (p.charAt(j) == '*') {
                    if (p.charAt(j-1) != s.charAt(i) && p.charAt(j-1) != '.') {
                        dp[i+1][j+1] = dp[i+1][j-1];
                    } else {
                        dp[i+1][j+1] = (dp[i+1][j] || dp[i][j+1] || dp[i+1][j-1]);
                    }
                }
            }
        }
        return dp[s.length()][p.length()];
    }

    public int maxArea(int[] height) {
        int maxArea=0;
        int left=0;
        int right=height.length-1;
        while (left<right){
            maxArea=Math.max(maxArea,(right-left)*Math.min(height[left],height[right]));
            if(height[left]<height[right]) left++;
            else right--;
        }
        return maxArea;
    }

    public String longestCommonPrefix(String[] strs) {
        String shortString="";
        String ans="";
        int min=Integer.MAX_VALUE;
        for(String s:strs){
            if(s.length()<min){
                min=s.length();
                shortString=s;
            }
        }
        for(int i=0;i<shortString.length();i++){
            for(String s:strs){
                if(s.charAt(i)!=shortString.charAt(i)){
                    ans=shortString.substring(0,i);
                    return ans;
                }
            }

        }
        return shortString;
    }

    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> ans=new ArrayList<>();
        if(nums.length==0) return ans;
        Arrays.sort(nums);
        for(int i=0;i<nums.length-2;i++){
            while (i<nums.length-2&&i>0&&nums[i]==nums[i-1]) i++;
            int left=i+1;
            int right=nums.length-1;
            while (left<right) {
                if (nums[left] + nums[right]+nums[i] < 0) left++;
                else if (nums[left] + nums[right] +nums[i]> 0) right--;
                else {
                    List<Integer> t = Arrays.asList(nums[i], nums[left], nums[right]);
                    ans.add(t);
                    while (left<right&&nums[left]==nums[left+1]) left++;
                    while (left<right&&nums[right]==nums[right-1]) right--;
                    left++;
                    right--;
                }
            }
        }

        return ans;
    }

    public int threeSumClosest(int[] nums, int target) {
        int min=Integer.MAX_VALUE;
        int ans=0;
        Arrays.sort(nums);

        for(int i=0;i<nums.length;i++){
            int sum=target-nums[i];
            //while (i<nums.length-2&&i>0&&nums[i]==nums[i-1]) i++; 重复数字的处理需要更谨慎
            int left=i+1;
            int right=nums.length-1;
            while (left<right){
                int temp=Math.abs(nums[left]+nums[right]-sum);
                if(min>temp){
                    min=temp;
                    ans=nums[i]+nums[left]+nums[right];
                }
                if(nums[left]+nums[right]<sum) left++;
                else if(nums[left]+nums[right]>sum) right--;
                else {
                    left++;
                    right--;
                }
            }
        }
        return ans;
    }



    public int[] searchRange(int[] nums, int target) {
        int[] ans=new int[2];
        Arrays.fill(ans,-1);
        if(nums.length==0) return ans;
        int left=0;
        int right=nums.length-1;
        int mid=(left+right)/2;
        while (left<right){
            if(nums[mid]<target) left=mid+1;
            else right=mid;
            mid=(left+right)/2;
        }
        if(nums[left]==target) ans[0]=left;
        else return ans;
        right=nums.length-1;
        while (left<right){
            mid=(left+right)/2+1;//important make mid biased to right
            if(nums[mid]>target) right=mid-1;
            else left=mid;
        }
        ans[1]=right;
        return ans;
    }

    public boolean isValidSudoku(char[][] board) {
        for(int i=0;i<9;i++) {
            HashSet<Character> row=new HashSet<>();
            HashSet<Character> column=new HashSet<>();
            HashSet<Character> cube=new HashSet<>();
            for(int j=0;j<9;j++){
                if(board[i][j]!='.'&&!row.add(board[i][j])){
                    return false;
                }
                if(board[j][i]!='.'&&!column.add(board[j][i])){
                    return false;
                }
                int indexRow=(i/3)*3;
                int indexCol=(i%3)*3;
                if(board[j/3+indexRow][j%3+indexCol]!='.'&&!cube.add(board[j/3+indexRow][j%3+indexCol])){
                    return false;
                }
            }
        }
        return true;
    }

    public String countAndSay(int n) {
        String begin="11";
        if(n==1) return "1";
        if(n==2) return begin;

        for(int i=2;i<n;i++){
            StringBuilder temp=new StringBuilder();
            int sum=1;
            for(int j=0;j<begin.length()-1;j++){

                if(begin.charAt(j)!=begin.charAt(j+1)){
                    temp.append(sum);
                    temp.append(begin.charAt(j));
                    sum=1;
                }else {
                    sum++;
                }
            }
                temp.append(sum);
                temp.append(begin.charAt(begin.length()-1));

            begin=temp.toString();
        }
        return begin;
    }

    private void helpCombinationSum(int[] candidate,int target,ArrayList<Integer> t,List<List<Integer>> ans,int start){
        for(int i=start;i<candidate.length;i++){
            if(candidate[i]>target) {
                return;
            }
            if(candidate[i]==target) {
                t.add(candidate[i]);
                ans.add(new ArrayList<>(t));
                t.remove(t.size()-1);
                return;
            }
            else{
                t.add(candidate[i]);
                helpCombinationSum(candidate,target-candidate[i],t,ans,i);
                t.remove(t.size()-1);
            }
        }
    }

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        Arrays.sort(candidates);
        List<List<Integer>> ans=new ArrayList<>();
        helpCombinationSum(candidates,target,new ArrayList<>(),ans,0);
        return ans;
    }


    private void helpCombinationSum2(int[] candidates,int target,ArrayList<Integer> t,List<List<Integer>> ans,int start){

        if(target==0){
            ans.add(new ArrayList<>(t));
            return;
        }
        if(target<0){
            return;
        }

        for (int i = start; i < candidates.length; i++) {
            if (i > start && candidates[i] == candidates[i - 1]) continue;
            t.add(candidates[i]);
            helpCombinationSum2(candidates, target - candidates[i], t, ans, i + 1);
            t.remove(t.size() - 1);
        }


    }

    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        Arrays.sort(candidates);
        List<List<Integer>> ans=new ArrayList<>();
        helpCombinationSum2(candidates,target,new ArrayList<>(),ans,0);
        return ans;
    }


    public static void main(String[] args){
        Solution s=new Solution();
        int[] nums={-1,0,1,2,-1,-4};
        s.twoSum(nums,6);
        s.addTwoNumbers(ListNode.stringToListNode("[2,4,3]"),ListNode.stringToListNode("[5,6,4]"));
        s.lengthOfLongestSubstring("aab");
        int[] t=new int[0];
        int[] t1={10,1,2,7,6,1,5};
        s.combinationSum2(t1,8);
    }
}
