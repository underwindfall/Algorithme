package leetcode.algo.backtrack;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

// https://leetcode-cn.com/problems/evaluate-division/
public class EvaluateDivision399 {
    // time O(n^3)
    // space O(n^2)
    class FloydWarshall {
        public double[] calcEquation(String[][] equations, double[] values, String[][] queries) {
            Map<String, Integer> map = buildIndex(equations);
            Double[][] mat = initMat(equations, values, map);
            floyd(mat);
            return getResult(queries, map, mat);
        }

        private void floyd(Double[][] mat) {
            final int N = mat.length;
            for (int k = 0; k < N; k++) {
                for (int i = 0; i < N; i++) {
                    for (int j = 0; j < N; j++) {
                        if (mat[i][k] != null && mat[k][j] != null)
                            mat[i][j] = mat[i][k] * mat[k][j];
                    }
                }
            }
        }

        private Map<String, Integer> buildIndex(String[][] equations) {
            Map<String, Integer> map = new HashMap<>();
            int n = 0;
            for (String[] equation : equations) {
                if (!map.containsKey(equation[0]))
                    map.put(equation[0], n++);
                if (!map.containsKey(equation[1]))
                    map.put(equation[1], n++);
            }
            return map;
        }

        private Double[][] initMat(String[][] equations, double[] values, Map<String, Integer> map) {
            final int N = map.size();
            Double[][] mat = new Double[N][N];
            for (int i = 0; i < N; i++)
                mat[i][i] = 1.0;
            for (int i = 0; i < equations.length; i++) {
                int a = map.get(equations[i][0]);
                int b = map.get(equations[i][1]);
                mat[a][b] = values[i];
                mat[b][a] = 1.0 / values[i];
            }
            return mat;
        }

        private double[] getResult(String[][] queries, Map<String, Integer> map, Double[][] mat) {
            double[] res = new double[queries.length];
            for (int i = 0; i < queries.length; i++) {
                Integer a = map.get(queries[i][0]);
                Integer b = map.get(queries[i][1]);
                if (a == null || b == null || mat[a][b] == null) {
                    res[i] = -1.0;
                } else {
                    res[i] = mat[a][b];
                }
            }
            return res;

        }
    }

    class UnionFind {

    }

    class Backtrack {
        public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
            // values
            // equations
            HashMap<String, HashMap<String, Double>> graph = new HashMap<>();

            // build graph
            for (int i = 0; i < equations.size(); i++) {
                List<String> equation = equations.get(i);
                String dividend = equation.get(0), divisor = equation.get(1);
                double quotient = values[i];

                if (!graph.containsKey(dividend)) {
                    graph.put(dividend, new HashMap<>());
                }
                if (!graph.containsKey(divisor)) {
                    graph.put(divisor, new HashMap<>());
                }
                graph.get(dividend).put(divisor, quotient);
                graph.get(divisor).put(dividend, 1 / quotient);
            }

            // backtracking

            double[] result = new double[queries.size()];
            for (int i = 0; i < queries.size(); i++) {
                List<String> query = queries.get(i);
                String dividend = query.get(0), divisor = query.get(1);

                if (!graph.containsKey(dividend) || !graph.containsKey(divisor)) {
                    result[i] = -1.0;
                } else if (dividend == divisor) {
                    result[i] = 1.0;
                } else {
                    HashSet<String> visited = new HashSet<>();
                    result[i] = backtrack(graph, dividend, divisor, 1, visited);
                }
            }

            return result;
        }

        double backtrack(HashMap<String, HashMap<String, Double>> graph, String currNode, String targetNode,
                double accProduct, Set<String> visited) {
            // mark the visit
            visited.add(currNode);
            double ret = -1.0;
            Map<String, Double> neighbors = graph.get(currNode);
            if (neighbors.containsKey(targetNode)) {
                ret = accProduct * neighbors.get(targetNode);
            } else {
                for (Map.Entry<String, Double> pair : neighbors.entrySet()) {
                    String nextNode = pair.getKey();
                    if (visited.contains(nextNode)) {
                        continue;
                    }
                    ret = backtrack(graph, nextNode, targetNode, accProduct * pair.getValue(), visited);
                    if (ret != -1.0) {
                        break;
                    }
                }
            }

            visited.remove(currNode);
            return ret;
        }
    }
}
