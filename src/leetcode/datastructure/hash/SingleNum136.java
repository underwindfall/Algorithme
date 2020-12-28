package leetcode.datastructure.hash;

// https://leetcode-cn.com/problems/single-number/
//异或运算
// A+0 = A
// A+A = 0
// A+(B+A) = A+A+B = B+(A+A)=  B+ 0= B
public class SingleNum136 {

    public int singleNumber(int[] nums) {
        int single = 0;
        for (int num : nums) {
            single ^= num;
        }
        return single;
    }

}
