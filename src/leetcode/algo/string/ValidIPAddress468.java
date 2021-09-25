package leetcode.algo.string;

//https://leetcode-cn.com/problems/validate-ip-address/
public class ValidIPAddress468 {
    //time O(n)
    //space O(n)
    public String validIPAddress(String IP) {
        if (IP.contains(".")) {
            if (isV4(IP)) {
                return "IPv4";
            }
        } else if (IP.contains(":")) {
            if (isV6(IP)) {
                return "IPv6";
            }
        }

        return "Neither";
    }

    boolean isV4(String ip) {
        if (ip.contains(":"))
            return false;
        if (ip.endsWith(".") || ip.endsWith(":"))
            return false;
        String[] addrs = ip.split("\\.");
        if (addrs.length != 4)
            return false;
        for (int i = 0; i < addrs.length; i++) {
            String addr = addrs[i];
            if (addr.equals(""))
                return false;
            int sum = 0;
            for (char c : addr.toCharArray()) {
                if (!Character.isDigit(c)) {
                    return false;
                }
                sum = sum * 10 + c - '0';
                if (sum >= 256)
                    return false;
            }

            if (addr.length() != Integer.toString(sum).length()) {
                return false;
            }
        }

        return true;
    }

    boolean isV6(String ip) {
        if (ip.contains("."))
            return false;
        if (ip.endsWith(".") || ip.endsWith(":"))
            return false;
        String[] addrs = ip.split(":");

        if (addrs.length != 8)
            return false;

        for (int i = 0; i < addrs.length; i++) {
            String addr = addrs[i];
            if (addr.equals(""))
                return false;
            char[] curr = addr.toCharArray();
            if (curr.length > 4)
                return false;
            for (char c : curr) {
                if (Character.toUpperCase(c) > 'F') {
                    return false;
                }
            }
        }
        return true;
    }
}
