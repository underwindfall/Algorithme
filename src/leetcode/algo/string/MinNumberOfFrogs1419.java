package leetcode.algo.string;

//https://leetcode-cn.com/problems/minimum-number-of-frogs-croaking/
class MinNumberOfFrogs1419 {
    //time O(n)
    //space O(1)
    public int minNumberOfFrogs(String croakOfFrogs) {
        int ans=0;
        if(croakOfFrogs.length()%5>0||!numOfLetters(croakOfFrogs)){return -1;}
        int c=0,r=0,o=0,a=0,k=0;
        for(char ch:croakOfFrogs.toCharArray()){
            if(ch=='c'){c++;}
            if(ch=='r'){r++;}
            if(ch=='o'){o++;}
            if(ch=='a'){a++;}
            if(ch=='k'){k++;}
            if(c<r||r<o||o<a||a<k){return -1;}
            if(k>0){
                ans=Math.max(c,ans);
                c--;r--;o--;a--;k--;
            }
        }
        return ans;
    }
    public boolean numOfLetters(String s){
        int c=0;
        int r=0;
        int o=0;
        int a=0;
        int k=0;
        for(char ch:s.toCharArray()){
            if(ch=='c'){c++;}
            if(ch=='r'){r++;}
            if(ch=='o'){o++;}
            if(ch=='a'){a++;}
            if(ch=='k'){k++;}
        }
        return c==r&&r==o&&o==a&&a==k;
    }
}