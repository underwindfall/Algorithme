package interview;

import java.io.File;
import java.util.PriorityQueue;

public class KLargestFile {
    public static void main(String[] args) {
        System.out.println(getLargestFile(".", 2).getAbsolutePath());
    }

    /**
     * Find the kth largest file in the given a path of a directory.
     * 
     * For example, if the given path is /home/Documents , it can contains multiple
     * files and directories inside. Say abc.txt, directory1, file2.txt...
     * You have to traverse each directory and return the kth largest file.
     */
    static File getLargestFile(String path, int k) {
        if (k < 1 || path == null || path.length() == 0)
            return null;
        PriorityQueue<File> pq = new PriorityQueue<>((f1, f2) -> Long.compare(f1.length(), f2.length()));
        File file = new File(path);
        dfs(file, pq, k);
        return pq.isEmpty() ? null : pq.poll();
    }

    static void dfs(File dir, PriorityQueue<File> pq, int k) {
        for (File f : dir.listFiles()) {
            if (f.isDirectory()) {
                dfs(f, pq, k);
            } else {
                pq.offer(f);
                if (pq.size() > k) {
                    pq.poll();
                }
            }
        }
    }
}
