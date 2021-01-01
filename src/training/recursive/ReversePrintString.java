package training.recursive;

//倒着打印字符串
public class ReversePrintString {
    public void printReverse(char[] str) {
        helper(0, str);
    }

    void helper(int index, char[] str) {
        // 基本案例 println str[0]
        // 递推关系 str[0......n-1] str[1......n-1] str[2......n-1]
        if (index >= str.length) {
            return;
        }
        helper(index + 1, str);
        System.out.println(str[index]);
    }

    public static void main(String[] args) {
        ReversePrintString re = new ReversePrintString();
        re.printReverse("abc".toCharArray());
    }
}
