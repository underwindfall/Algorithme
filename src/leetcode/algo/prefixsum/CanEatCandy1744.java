package leetcode.algo.prefixsum;

//https://leetcode-cn.com/problems/can-you-eat-your-favorite-candy-on-your-favorite-day/
public class CanEatCandy1744 {
    // prefix sum
    // 每天可以吃糖果数量 在[1, queries[i][2]] 之间，因此可以计算出 最早/最晚 吃到 queries[i][0] 类糖果的时间
    // 判断queries[i][1] 是否在范围内
    // 左边界：以最快速度吃，也就是每天吃queries[i][2]颗，速战速决，看哪天能吃到第一颗指定类型的
    // 右边界：以最慢速度吃，也就是每天吃1颗，尽量延长战线，看哪天才能把最后一颗指定类型的吃完
    // 如果题目指定的那天正好落在我的作战范围内，是不是就符合要求呢！！！
    // time O(N+Q)
    // espace O(N)
    public boolean[] canEat(int[] candiesCount, int[][] queries) {
        int types = candiesCount.length;//糖果的类型数
        long[] sum = new long[types];
        sum[0]=candiesCount[0];
        for(int i=1;i<types;i++){
            sum[i]=sum[i-1]+candiesCount[i];//计算前缀和，也就是计算在吃完指定类型的糖之前一共要吃掉多少颗糖
        }
        int n = queries.length;
        boolean[] res = new boolean[n];
        for(int i=0;i<n;i++){
            int type = queries[i][0];   //指定的糖果类型
            int day = queries[i][1];    //题目要求第几天要吃到指定类型的糖
            int num = queries[i][2];    //每天吃糖的上限
             //以最快的速度吃，看多久能进入战斗，也就是什么时候吃到第一颗指定类型的糖
            long min = (sum[type]-candiesCount[type])/num; 
             //以最慢的速度吃，看战线能拉多长，也就是什么时候把最后一颗指定类型的吃完
            long max = sum[type]-1;   
            
            res[i]=min<=day&& day<=max;
        }
        return res;
    }
}
