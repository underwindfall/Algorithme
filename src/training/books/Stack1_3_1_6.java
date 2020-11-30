package training.books;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class Stack1_3_1_6 {
    public static void main(String[] args) {
        Stack<Integer> stack = new Stack<>();
        List<Integer> in = Arrays.asList(1, 2, 3, 4, 5);
        for (Integer integer : in) {
            stack.push(integer);
        }
        while (!stack.isEmpty()) {
            System.out.println(stack.pop());
        }
    }
}
