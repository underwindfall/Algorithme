package leetcode.algo.dp.prefix;

import java.util.ArrayList;
import java.util.List;

//https://leetcode-cn.com/problems/product-of-the-last-k-numbers/
public class ProductOfNumbers1352 {
    // prefixSum -> prefixProduct
    List<Integer> product;

    public ProductOfNumbers1352() {
        product = new ArrayList<>();
        product.add(1);
    }

    // 若遇到0 则清空之前的所有的数组数据
    // 非0时候 进行前缀积
    public void add(int num) {
        if (num == 0) {
            product.clear();
            product.add(1);
        } else {
            product.add(product.get(product.size() - 1) * num);
        }
    }

    // 因为是前缀积
    // so -> product(size ) / product(size - k)
    public int getProduct(int k) {
        if (product.size() <= k)
            return 0;
        return product.get(product.size() - 1) / product.get(product.size() - k - 1);
    }

    // s,e -> 之间的前缀集
    class ProdcutNumber {
        List<Integer> product;
        List<Integer> cnt; // 前缀和是0的情况

        public ProdcutNumber() {
            product = new ArrayList<>();
            product.add(1);
            cnt = new ArrayList<>();
            cnt.add(0);
        }

        public void add(int num) {
            if (num == 0) {
                product.add(1);
                cnt.add(cnt.get(cnt.size() - 1) + 1);
            } else {
                product.add(num);
                cnt.add(cnt.get(cnt.size() - 1));
            }
        }

        public int getProduct(int k) {
            return getProduct(product.size() - k, product.size() - 1);
        }

        int getProduct(int s, int e) {
            if (e < 1 || e >= product.size() || s < 1 || s >= product.size())
                throw new IllegalStateException("Wrong index");
            // 判断目前 cnt s-e 中不含0
            if (cnt.get(s - 1) != cnt.get(e)) {
                return 0;
            }

            return product.get(e) / product.get(s - 1);
        }
    }
}
