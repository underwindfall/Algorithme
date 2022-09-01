
package interview;

import java.util.LinkedList;
import java.util.Queue;

public class CurrentViewGroupLevel {

    public void getViewLevel(View view) {
        if (view == null) {
            return;
        }
        if (view instanceof View) {
            return;
        }
        // int level = 0;
        ViewGroup viewGroup = (ViewGroup) view;

        Queue<View> queued = new LinkedList<>();

        queued.add(viewGroup);
        while (!queued.isEmpty()) {
            int size = queued.size();
            // level++;
            for (int i = 0; i < size; i++) {
                View node = queued.poll();
                if (node instanceof ViewGroup) {
                    ViewGroup nodeGroup = (ViewGroup) node;
                    for (int j = 0; j < nodeGroup.getChildCount(); j++) {
                        queued.offer(nodeGroup.getChildAt(j));
                    }
                }
            }
        }
    }

    // curr为当前View的层级
    private static int getDeep(View view, int curr) {
        if (view instanceof ViewGroup) {
            ViewGroup parent = (ViewGroup) view;
            int childCount = parent.getChildCount();
            if (childCount > 0) {
                // 层级+1
                int max = curr + 1;
                for (int i = 0; i < childCount; i++) {
                    View child = parent.getChildAt(i);
                    int height = getDeep(child, curr + 1);
                    if (max < height) {
                        max = height;
                    }
                }
                return max;
            } else {
                return curr;
            }
        } else {
            return curr;
        }
    }

    abstract class View {

    }

    abstract class ViewGroup extends View {
        abstract int getChildCount();

        abstract View getChildAt(int i);
    }
}
