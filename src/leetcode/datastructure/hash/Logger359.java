package leetcode.datastructure.hash;

import java.util.HashMap;

// https://leetcode-cn.com/problems/logger-rate-limiter/
// time O(1)
// espace O(M)
public class Logger359 {

    /** Initialize your data structure here. */
    private HashMap<String, Integer> msgDict;

    /** Initialize your data structure here. */
    public Logger359() {
        msgDict = new HashMap<String, Integer>();
    }

    /**
     * Returns true if the message should be printed in the given timestamp,
     * otherwise returns false.
     */
    public boolean shouldPrintMessage(int timestamp, String message) {

        if (!this.msgDict.containsKey(message)) {
            this.msgDict.put(message, timestamp);
            return true;
        }

        Integer oldTimestamp = this.msgDict.get(message);
        if (timestamp - oldTimestamp >= 10) {
            this.msgDict.put(message, timestamp);
            return true;
        } else
            return false;
    }
}
