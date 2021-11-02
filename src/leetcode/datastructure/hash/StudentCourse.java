package leetcode.datastructure.hash;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class StudentCourse {
    public static void main(String[] args) {

        String[][] students = new String[][] { { "58", "Linear Algebra" }, { "94", "Art History" },
                { "94", "Operating Systems" }, { "17", "Software Design" }, { "58", "Mechanics" },
                { "58", "Economics" }, { "17", "Linear Algebra" }, { "17", "Political Science" }, { "94", "Economics" },
                { "25", "Economics" }, { "58", "Software Design" } };
        // # find_pairs(student_course_pairs_1) =>
        // # {
        // # "58,17": ["Software Design", "Linear Algebra"]
        // # "58,94": ["Economics"]
        // # "58,25": ["Economics"]
        // # "94,25": ["Economics"]
        // # "17,94": []
        // # "17,25": []
        // # }

        Map<String, Set<String>> map = new HashMap<>();
        for (String[] s : students) {
            String name = s[0], course = s[1];
            if (!map.containsKey(name)) {
                map.put(name, new HashSet<>());
            }
            map.get(name).add(course);
        }
        Map<String, Set<String>> res = new HashMap<>();

        Set<String> namePairs = new HashSet<>();
        String[] keys = map.keySet().toArray(new String[] {});
        for (int i = 0; i < keys.length; i++) {
            for (int j = i + 1; j < keys.length; j++) {
                namePairs.add(keys[i] + "," + keys[j]);
                res.put(keys[i] + "," + keys[j], new HashSet<>());
            }
        }


        for (String name : namePairs) {
            String[] pair = name.split(",");
            for (String courseA: map.get(pair[0])) {
                for (String courseB: map.get(pair[1])) {
                    if (courseA == courseB) {
                        res.get(name).add(courseA);
                    }
                }
            }
        }

        System.out.print(res);

    }
}
