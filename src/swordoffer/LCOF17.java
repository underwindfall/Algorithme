package swordoffer;

// https://leetcode.cn/problems/da-yin-cong-1dao-zui-da-de-nwei-shu-lcof/   
public class LCOF17 {

    // time O(10^n)
    // space O(10^n)
    class DFS {
        int[] res;
        int nine = 0, count = 0, start, n;
        char[] num, loop = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};
        public int[] printNumbers(int n) { 
            this.n = n;
            res = new int[(int)Math.pow(10, n) - 1];
            num = new char[n];
            start = n - 1;
            dfs(0);
            return res;
        }
        void dfs(int x) {
            if(x == n) {
                String s = String.valueOf(num).substring(start);
                if(!s.equals("0")) res[count++] = Integer.parseInt(s);
                if(n - start == nine) start--;
                return;
            }
            for(char i : loop) {
                if(i == '9') nine++;
                num[x] = i;
                dfs(x + 1);
            }
            nine--;
        }

    //     StringBuilder res;
    //     int count = 0, n;
    //     char[] num, loop = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};
    //     public String printNumbers(int n) {
    //         this.n = n;
    //         res = new StringBuilder(); // 数字字符串集
    //         num = new char[n]; // 定义长度为 n 的字符列表
    //         dfs(0); // 开启全排列递归
    //         res.deleteCharAt(res.length() - 1); // 删除最后多余的逗号
    //         return res.toString(); // 转化为字符串并返回
    //     }
    //     void dfs(int x) {
    //         if(x == n) { // 终止条件：已固定完所有位
    //             res.append(String.valueOf(num) + ","); // 拼接 num 并添加至 res 尾部，使用逗号隔开
    //             return;
    //         }
    //         for(char i : loop) { // 遍历 ‘0‘ - ’9‘
    //             num[x] = i; // 固定第 x 位为 i
    //             dfs(x + 1); // 开启固定第 x + 1 位
    //         }
    //     }
    
    // 作者：jyd
    // 链接：https://leetcode.cn/problems/da-yin-cong-1dao-zui-da-de-nwei-shu-lcof/solution/mian-shi-ti-17-da-yin-cong-1-dao-zui-da-de-n-wei-2/
    // 来源：力扣（LeetCode）
    // 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
    }

    // time O(n)
    // space O(1)
    class Bad {
        public int[] printNumbers(int n) {
            int max = (int) (Math.pow(10, n)) - 1;
            int[] ans = new int[max];
            for (int i = 0; i < max; i++) {
                ans[i] = i + 1;
            }
            return ans;
        }
    }
}
