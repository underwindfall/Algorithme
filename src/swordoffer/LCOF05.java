package swordoffer;

//https://leetcode-cn.com/problems/ti-huan-kong-ge-lcof/
public class LCOF05 {
    // time O(n)
    // space O(1)
    public String replaceSpace(String s) {
        String c = "%20";
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == ' ') {
                sb.append(c);
            } else {
                sb.append(s.charAt(i));
            }
        }
        return sb.toString();
    }
}
