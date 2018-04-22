package bfs;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Solution {

    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> ans=new ArrayList<>();
        int level=1;
        LinkedList<TreeNode> a=new LinkedList<>();
        LinkedList<TreeNode> b=new LinkedList<>();

        a.add(root);
        while (!a.isEmpty()||!b.isEmpty()){
            if(level%2==1){
                LinkedList<Integer> t=new LinkedList<>();
                while (!a.isEmpty()){
                    TreeNode tmp=a.poll();
                    t.add(tmp.val);
                    if(tmp.left!=null) b.add(tmp.left);
                    if(tmp.right!=null) b.add(tmp.right);
                }
                ans.add(t);
            }else {
                LinkedList<Integer> t=new LinkedList<>();
                while (!b.isEmpty()){
                    TreeNode tmp=b.poll();
                    t.addFirst(tmp.val);
                    if(tmp.left!=null) a.add(tmp.left);
                    if(tmp.right!=null) a.add(tmp.right);
                }
                ans.add(t);
            }
            level++;
        }
        return ans;
    }

    public void solve(char[][] board) {

    }


    /*
    测试环境
     */

    public static TreeNode stringToTreeNode(String input) {
        input = input.trim();
        input = input.substring(1, input.length() - 1);
        if (input.length() == 0) {
            return null;
        }

        String[] parts = input.split(",");
        String item = parts[0];
        TreeNode root = new TreeNode(Integer.parseInt(item));
        Queue<TreeNode> nodeQueue = new LinkedList<>();
        nodeQueue.add(root);

        int index = 1;
        while(!nodeQueue.isEmpty()) {
            TreeNode node = nodeQueue.remove();

            if (index == parts.length) {
                break;
            }

            item = parts[index++];
            item = item.trim();
            if (!item.equals("null")) {
                int leftNumber = Integer.parseInt(item);
                node.left = new TreeNode(leftNumber);
                nodeQueue.add(node.left);
            }

            if (index == parts.length) {
                break;
            }

            item = parts[index++];
            item = item.trim();
            if (!item.equals("null")) {
                int rightNumber = Integer.parseInt(item);
                node.right = new TreeNode(rightNumber);
                nodeQueue.add(node.right);
            }
        }
        return root;
    }

    public static String integerArrayListToString(List<Integer> nums, int length) {
        if (length == 0) {
            return "[]";
        }

        String result = "";
        for(int index = 0; index < length; index++) {
            Integer number = nums.get(index);
            result += Integer.toString(number) + ", ";
        }
        return "[" + result.substring(0, result.length() - 2) + "]";
    }

    public static String integerArrayListToString(List<Integer> nums) {
        return integerArrayListToString(nums, nums.size());
    }

    public static String int2dListToString(List<List<Integer>> nums) {
        StringBuilder sb = new StringBuilder("[");
        for (List<Integer> list: nums) {
            sb.append(integerArrayListToString(list));
            sb.append(",");
        }

        sb.setCharAt(sb.length() - 1, ']');
        return sb.toString();
    }
    public static void main(String[] args) throws IOException {
            String line="[3,9,20,null,null,15,7]";

            TreeNode root = stringToTreeNode(line);

            List<List<Integer>> ret = new Solution().zigzagLevelOrder(root);

            String out = int2dListToString(ret);

            System.out.print(out);
        }

}
