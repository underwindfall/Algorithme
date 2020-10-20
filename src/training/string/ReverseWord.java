package training.string;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ReverseWord {
    public static String reverseWords(String s) {
        s = s.trim();
        List<String> wordList = Arrays.asList(s.split("\\s+"));
        Collections.reverse(wordList);
        return String.join(" ", wordList);
    }

    private StringBuilder trimSpaces(String s) {
                
    }

    public static void main(String[] args) {
        System.out.println(reverseWords("The sky is blue."));
    }
}
