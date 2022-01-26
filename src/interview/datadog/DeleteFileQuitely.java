package interview.datadog;

import java.io.File;
import java.util.LinkedList;
import java.util.Queue;

public class DeleteFileQuitely {
    static boolean deleteQuietly(File file) {
        if (file == null || !file.exists())
            return true;
        if (!file.isDirectory())
            return file.delete();

        Queue<File> dirs = new LinkedList<>();
        dirs.offer(file);
        boolean succeededDeletion = true;
        while (!dirs.isEmpty()) {
            file = dirs.poll();
            File[] children = file.listFiles();
            if (children == null || children.length == 0) {
                succeededDeletion &= file.delete();
            } else {
                for (File child : children) {
                    if (child.isDirectory()) {
                        dirs.offer(child);
                    } else {
                        succeededDeletion &= child.delete();
                    }
                }

            }
        }
        return succeededDeletion;
    }

}
