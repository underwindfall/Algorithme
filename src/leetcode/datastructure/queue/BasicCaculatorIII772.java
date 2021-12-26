package leetcode.datastructure.queue;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

//time O(n)
//space O(n)
// https://leetcode-cn.com/problems/basic-calculator-iii/
public class BasicCaculatorIII772 {

    public int calculate(String s) {
        Map<Character, Integer> map = new HashMap<>();
        map.put('-', 0);
        map.put('+', 0);
        map.put('/', 1);
        map.put('*', 1);
        map.put('%', 1);
        map.put('^', 2);

        Stack<Integer> nums = new Stack<>();
        nums.add(0);
        Stack<Character> ops = new Stack<>();
        s = s.replaceAll(" ", "");
        char[] arr = s.toCharArray();
        int n = s.length();
        for (int i = 0; i < n; i++) {
            char c = arr[i];
            if (Character.isDigit(c)) {
                int number = 0;
                int j = i;
                while (j < n && Character.isDigit(arr[j])) {
                    number = number * 10 + arr[j] - '0';
                    j++;
                }
                i = j - 1;
                nums.push(number);
            } else {
                if (c == '(') {
                    ops.push(c);
                } else if (c == ')') {
                    // 计算之前括号的值
                    while (!ops.isEmpty()) {
                        if (ops.peek() != '(') {
                            calculate(nums, ops);
                        } else {
                            ops.pop();
                            break;
                        }
                    }
                } else {
                    if (i > 0 && (arr[i - 1] == '(' || arr[i - 1] == '-' || arr[i - 1] == '+')) {
                        nums.push(0);
                    }
                    // +,-,*,/
                    while (!ops.isEmpty() && ops.peek() != '(') {
                        char prev = ops.peek();
                        if (map.get(prev) >= map.get(c)) {
                            calculate(nums, ops);
                        } else {
                            break;
                        }
                    }
                    ops.push(c);
                }
            }
        }

        while (!ops.isEmpty()) {
            calculate(nums, ops);
        }
        return nums.peek();
    }

    void calculate(Stack<Integer> nums, Stack<Character> ops) {
        if (nums.isEmpty() && nums.size() < 2)
            return;

        int b = nums.pop(), a = nums.pop();
        switch (ops.pop()) {
            case '+':
                nums.push(a + b);
                break;
            case '-':
                nums.push(a - b);
                break;
            case '*':
                nums.push(a * b);
                break;
            case '/':
                nums.push(a / b);
                break;
            case '%':
                nums.push(a % b);
                break;
            case '^':
                nums.push((int) (Math.pow(a, b)));
                break;
            default:
                break;
        }
    }

}
