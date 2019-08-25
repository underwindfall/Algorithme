package swordoffer.exercise5;

/**
 * 第4题
 * 将一个字符串中的空格替换成"%20"
 */
public class JReplaceBlank {
    public static void main(String[] arg0) {
        int[] array1 = new int[10];
        int count = 1;
        for (int i = 0; i < 5; i++) {
            array1[i] = count;
            count += 2;
        }
        int[] array2 = {0, 2, 4, 6, 8};
        mergeArray(array1, array2);
        for (int i = 0; i < array1.length; i++) {
            System.out.print(array1[i] + " ");
        }
    }

    /**
     * 解法一：使用StringBuffer
     *
     * @param input
     * @return
     */
    public static String replaceBlank1(String input) {
        if (input == null) {
            return null;
        }
        StringBuffer outputBuffer = new StringBuffer();

        for (int i = 0; i < input.length(); i++) {
            if (input.charAt(i) == ' ') {
                outputBuffer.append("%20");
            } else {
                outputBuffer.append(input.charAt(i));
            }
        }
        return new String(outputBuffer);
    }

    /**
     * 解法二：使用StringBuilder
     *
     * @param input
     * @return
     */
    public static String replaceBlank2(String input) {
        if (input == null) {
            return null;
        }
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < input.length(); i++) {
            if (String.valueOf(input.charAt(i)).equals(" ")) {
                sb.append("%20");
            } else {
                sb.append(input.charAt(i));
            }
        }
        return String.valueOf(sb);
    }

    /**
     * 解法三：从后往前复制
     *
     * @param input
     * @return
     */
    public static String replaceBlank3(String input) {
        if (input == null) {
            return null;
        }
        int blankNum = 0;
        int length = input.length();
        int newLength = 0;
        for (int i = 0; i < length; i++) {
            if (input.charAt(i) == ' ') {
                blankNum++;
            }
        }
        // 替换后的字符串长度
        newLength = length + 2 * blankNum;
        char[] newChars = new char[newLength];
        int index = newLength - 1;
        for (int i = length - 1; i >= 0; i--) {
            if (input.charAt(i) == ' ') {
                newChars[index--] = '0';
                newChars[index--] = '2';
                newChars[index--] = '%';
            } else {
                newChars[index--] = input.charAt(i);
            }
        }
        return new String(newChars);
    }

    /*
     * 相关题目：替换空格（将字符串中的空格替换为其它字符串）
     */

    public static int[] mergeArray(int[] array1, int[] array2) {
        int index1 = array1.length - 1;
        while (array1[index1] == 0)
            index1--;
        int index2 = array2.length - 1;
        while (index2 >= 0) {
            if (array2[index2] < array1[index1]) {
                if (index1 == 0 || array1[index1 - 1] < array2[index2]) {
                    insertArray(array2[index2], index1, array1);
                    index1--;
                    index2--;
                }
            } else index1++;
        }
        return array1;
    }

    private static void insertArray(int insertNumber, int index, int[] array) {
        int temp = insertNumber;
        int cache;
        for (int i = index; i < array.length; i++) {
            cache = array[i];
            array[i] = temp;
            temp = cache;
        }
    }
}


