package leetcode.algo.string;

//https://leetcode.com/problems/minimum-number-of-steps-to-make-two-strings-anagram/
class MinStepAnagram1347 {
    //time O(n)
    //space O(n)
    public int minSteps(String s, String t) {
        int[] countS = new int[26];
        for (char c : s.toCharArray()) {
            countS[c - 'a']++;
        }
        int[] countT = new int[26];
        for (char c : t.toCharArray()) {
            countT[c - 'a']++;
        }
        int ans = 0;
        for (int i = 0; i < 26; i++) {
            ans += Math.max(countS[i] - countT[i], 0);
        }
        return ans;
    }

    class MapSolution {
        public int minSteps(String s, String t) {
            Map<Character,Integer> frequency = new HashMap<>();
            for(char ch : s.toCharArray()){
                if(frequency.containsKey(ch)){
                    int value = frequency.get(ch) + 1;
                    frequency.put(ch, value);
                }else{
                    frequency.put(ch, 1);
                }    
            }
            
            int counter= 0;
            for(char ch : t.toCharArray()){
                if(frequency.containsKey(ch) && frequency.get(ch) > 0){
                    int value = frequency.get(ch) - 1;
                    frequency.put(ch, value);
                }else{
                    counter++;
                }     
            }
            return counter;
        }
    }
}