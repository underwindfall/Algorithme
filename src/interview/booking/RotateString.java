package interview.booking;

// Rotate the string based on the given integer values for leftshift and rightshift
public class RotateString {
    public void rotate(String word, int left, int right) {
        char[] arr = word.toCharArray();
        while (left < right) {
            char c = arr[left];
            arr[left] = arr[right];
            arr[right] = c;
        }
        word = new String(arr);
    }
}
