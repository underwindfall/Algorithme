package leetcode.datastructure.stack;

import java.util.Stack;

//https://leetcode.com/problems/remove-duplicate-letters/
public class RemoveDuplicateLetters316 {
    //time O(n)
    //space O(n)
    public String removeDuplicateLetters(String s) {
        int[] arr = new int[26];
        boolean[] visited = new boolean[26];
        char[] ch = s.toCharArray();
        for (char c : ch) {
            arr[c - 'a']++;
        }
        
        Stack<Character> stack = new Stack<>();

        for (char character : ch) {
            int idx =  character - 'a';
            arr[idx]--;

            if (visited[idx] == true) {
                continue;
            }

            while (!stack.isEmpty() && character < stack.peek() && arr[stack.peek() - 'a'] != 0) {
                visited[stack.pop() - 'a'] = false;
            }
            stack.push(character);
            visited[character - 'a'] = true;
        }

        StringBuilder sb = new StringBuilder();
        while(!stack.isEmpty()){
            sb.append(stack.pop());
        }
        return sb.toString();
    }
}
