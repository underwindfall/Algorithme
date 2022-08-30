package leetcode.datastructure.array;

import java.util.Stack;

// https://leetcode-cn.com/problems/string-compression/
public class Compress443 {
    // time O(n)
    // space O(1)
    class DoubleIndex {
        public int compress(char[] chars) {
            int n = chars.length;
            int write = 0, left = 0;
            for (int read = 0; read < n; read++) {
                if (read == n - 1 || chars[read] != chars[read + 1]) {
                    chars[write++] = chars[read];
                    int num = read - left + 1;
                    if (num > 1) {
                        int anchor = write;
                        while (num > 0) {
                            chars[write++] = (char) (num % 10 + '0');
                            num /= 10;
                        }
                        reverse(chars, anchor, write - 1);
                    }
                    left = read + 1;
                }
            }
            return write;
        }

        void reverse(char[] input, int left, int right) {
            while (left < right) {
                char tmp = input[left];
                input[left] = input[right];
                input[right] = tmp;
                right--;
                left++;
            }
        }
    }

    class DoubleStack {
        // time O(n)
        // space O(n)
        public int compress(char[] chars) {
            Stack<Node> stack1 = new Stack<>();
            Stack<Node> stack2 = new Stack<>();

            for (int i = 0; i < chars.length; i++) {
                char c = chars[i];
                if (!stack1.isEmpty() && stack1.peek().character == c) {
                    stack1.peek().count++;
                } else {
                    Node node = new Node(c, 1);
                    stack1.add(node);
                }
            }

            while (!stack1.isEmpty()) {
                stack2.add(stack1.pop());
            }

            int index = 0;
            while (!stack2.isEmpty()) {
                Node n = stack2.pop();
                String countStr = String.valueOf(n.count);
                chars[index++] = n.character;
                if (n.count != 1) {
                    for (char c : countStr.toCharArray()) {
                        chars[index++] = c;
                    }
                }
            }
            return index;
        }

        class Node {
            char character;
            int count;

            Node(char character, int count) {
                this.character = character;
                this.count = count;
            }
        }
    }
}
