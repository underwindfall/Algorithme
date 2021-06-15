package leetcode.datastructure.backtrack;

import java.util.Arrays;

//https://leetcode-cn.com/problems/letter-tile-possibilities/
public class LetterTilePossibilities1079 {
    //see permutations 47
    //排列问题
    int count = 0;
    boolean[] used;
    public int numTilePossibilities(String tiles) {
        if (tiles.length() == 0) {
            return count;
        }

        char[] tilesChar = tiles.toCharArray();
        Arrays.sort(tilesChar);
        used = new boolean[tilesChar.length];
        dfs(tilesChar, 0);
        return count;
    }


    void dfs(char[] tilesChar, int depth) {
        if (depth == tilesChar.length) {
            return;
        }

        for (int i = 0; i < tilesChar.length; i++) {
            if (!used[i]) {
                if (i - 1 >= 0 && tilesChar[i] == tilesChar[i - 1] && !used[i - 1]) {
                    continue;
                }
                count++;
                used[i] = true;
                dfs(tilesChar, depth + 1);
                used[i] = false;
            }
        }
    }
}
