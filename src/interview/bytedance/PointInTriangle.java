package interview.bytedance;

// https://mp.weixin.qq.com/s/UK7ilkFeEDQeVWvLDHbuKA
public class PointInTriangle {
    // 判断△ABO+△BOC+△COA的面积与△ABC是否相等。若相等则O在内部，反之则在外部。
    private double getDist(Point p1, Point p2) {
        // 两点之间计算距离公式
        return Math.sqrt(Math.pow(p1.x - p2.x, 2) + Math.pow(p1.y - p2.y, 2));
    }

    private double getArea(Point p1, Point p2, Point p3) {
        double a = getDist(p1, p2);
        double b = getDist(p2, p3);
        double c = getDist(p1, p3);
        double p = (a + b + c) / 2;
        return Math.sqrt(p * (p - a) * (p - b) * (p - c));
    }   

    boolean isInTriangle(Point p1, Point p2, Point p3, Point o) {
        double s1 = getArea(p1, p2, o);
        double s2 = getArea(p2, p3, o);
        double s3 = getArea(p3, p1, o);
        double s = getArea(p1, p2, p3);
        return s1 + s2 + s3 == s; // 此处没有用fabs(a-b)<eps比较，是方便大家理解思路
    }



    class Point {
        double x;
        double y;
    }
}
