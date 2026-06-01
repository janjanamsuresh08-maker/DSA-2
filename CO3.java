import java.util.*;

class Frame {

    String node;
    boolean processed;

    Frame(String n, boolean p) {
        node = n;
        processed = p;
    }
}

public class IterativeTopoSort {

    static List<String> topoSortIterative(
            Map<String, List<String>> adj,
            String start) {

        Map<String, Integer> color = new HashMap<>();

        for (String v : adj.keySet())
            color.put(v, 0);

        List<String> order = new ArrayList<>();

        Deque<Frame> stack = new ArrayDeque<>();

        stack.push(new Frame(start, false));

        while (!stack.isEmpty()) {

            Frame f = stack.pop();

            if (f.processed) {

                color.put(f.node, 2); // BLACK
                order.add(f.node);
                continue;
            }

            if (color.get(f.node) != 0)
                continue;

            // GREY
            color.put(f.node, 1);

            // Leave operation
            stack.push(new Frame(f.node, true));

            List<String> neighbors =
                    new ArrayList<>(
                            adj.getOrDefault(
                                    f.node,
                                    Collections.emptyList()));

            // Reverse alphabetical so pop order becomes alphabetical
            neighbors.sort(Collections.reverseOrder());

            for (String nbr : neighbors) {

                if (color.getOrDefault(nbr, 0) == 0) {
                    stack.push(new Frame(nbr, false));
                }
            }
        }

        Collections.reverse(order);

        return order;
    }

    public static void main(String[] args) {

        Map<String, List<String>> graph =
                new HashMap<>();

        graph.put("app",
                Arrays.asList("core"));

        graph.put("core",
                Arrays.asList("logging", "util"));

        graph.put("util",
                Arrays.asList("math", "serial"));

        graph.put("logging",
                Arrays.asList("filehandler"));

        graph.put("math",
                Arrays.asList("bigint"));

        graph.put("serial",
                Collections.emptyList());

        graph.put("filehandler",
                Arrays.asList("log4j"));

        graph.put("bigint",
                Collections.emptyList());

        graph.put("log4j",
                Collections.emptyList());

        List<String> topo =
                topoSortIterative(graph, "app");

        System.out.println("Topological Order:");
        System.out.println(topo);
    }
}
