package leetcode.datastructure.backtrack;

//https://leetcode-cn.com/problems/matchsticks-to-square/
public class MatchsticksToSquare473 {

    class DFS {
        public boolean makesquare(int[] nums) {
            if (null == nums || nums.length < 4) {
                return false;
            }
            int sum = 0;
            for (int num : nums) {
                sum = sum + num;
            }
            if (sum % 4 != 0) {
                return false;
            }

            return dfs(nums, 0, 0, 0, 0, 0, sum / 4);
        }

        /**
         * 
         * @param nums
         * @param index         表示当前放置到了第几个元素
         * @param a|b|c|d表示四个边长
         * @param side          表示最终的边长
         */
        private boolean dfs(int[] nums, int index, int a, int b, int c, int d, int side) {
            if (index == nums.length) {
                return a == side && b == side && c == side && d == side;
            }

            if (a > side || b > side || c > side || d > side) {
                // 只要有一个边大于边长，则不用进行下一步放置了
                return false;
            }

            // 每根火柴都有四种放置地点；分别将index位置的火柴放到a b c d四个位置，检查是否成功
            return dfs(nums, index + 1, a + nums[index], b, c, d, side)
                    || dfs(nums, index + 1, a, b + nums[index], c, d, side)
                    || dfs(nums, index + 1, a, b, c + nums[index], d, side)
                    || dfs(nums, index + 1, a, b, c, d + nums[index], side);
        }
    }

    class Backtracking {
        public boolean makesquare(int[] nums) {
            int total = 0;
            // 统计所有火柴的长度
            for (int num : nums) {
                total += num;
            }
            // 如果所有火柴的长度不是4的倍数，直接返回false
            if (total == 0 || (total % 4) != 0)
                return false;
            // 回溯
            return backtrack(nums, 0, total / 4, new int[4]);
        }

        // index表示访问到当前火柴的位置，target表示正方形的边长，size是长度为4的数组，
        // 分别保存正方形4个边的长度
        private boolean backtrack(int[] nums, int index, int target, int[] size) {
            if (index == nums.length) {
                // 如果火柴都访问完了，并且size的4个边的长度都相等，说明是正方形，直接返回true，
                // 否则返回false
                if (size[0] == size[1] && size[1] == size[2] && size[2] == size[3])
                    return true;
                return false;
            }
            // 到这一步说明火柴还没访问完
            for (int i = 0; i < size.length; i++) {
                // 如果把当前火柴放到size[i]这个边上，他的长度大于target，我们直接跳过
                if (size[i] + nums[index] > target)
                    continue;
                // 如果当前火柴放到size[i]这个边上，长度不大于target，我们就放上面
                size[i] += nums[index];
                // 然后在放下一个火柴，如果最终能变成正方形，直接返回true
                if (backtrack(nums, index + 1, target, size))
                    return true;
                // 如果当前火柴放到size[i]这个边上，最终不能构成正方形，我们就把他从
                // size[i]这个边上给移除，然后在试其他的边
                size[i] -= nums[index];
            }
            // 如果不能构成正方形，直接返回false
            return false;
        }
    }


}
