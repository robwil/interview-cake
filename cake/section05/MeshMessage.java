package cake.section05;

import java.nio.file.Path;
import java.util.*;

import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

import static org.junit.Assert.*;

public class MeshMessage {

    private static class PathSoFar {
        private final String node;
        private final List<String> path;

        public PathSoFar(String node, List<String> path) {
            this.node = node;
            this.path = path;
        }

        public String getNode() {
            return node;
        }

        public List<String> getPath() {
            return path;
        }
    }

    public static String[] getPath(Map<String, String[]> graph, String startNode, String endNode) throws Exception {

        // find the shortest route in the network between the two users

        // This can be done using BFS, short-circuiting when we find an answer since it will necessarily be shortest path.

        if (graph.size() <= 0) {
            return null; // no path exists for empty graph
        }

        if (!graph.containsKey(startNode) || !graph.containsKey(endNode)) {
            throw new Exception("startNode and endNode both must exist in the graph");
        }

        Set<String> visitedNodes = new HashSet<>();
        Queue<PathSoFar> nodesToCheck = new LinkedList<>();
        List<String> initialPath = new LinkedList<>();
        initialPath.add(startNode);
        nodesToCheck.add(new PathSoFar(startNode, initialPath)); // add first node to begin BFS
        while (!nodesToCheck.isEmpty()) {
            PathSoFar pathSoFar = nodesToCheck.remove();
            String currentNode = pathSoFar.getNode();
            if (visitedNodes.contains(currentNode)) {
                continue;
            }
            visitedNodes.add(currentNode);
            if (currentNode.equals(endNode)) {
                // found shortest path, so return
                return pathSoFar.getPath().toArray(new String[0]);
            }
            String[] neighbors = graph.get(currentNode);
            for (String neighbor : neighbors) {
                // creating copy here is necessary to avoid issues
                LinkedList<String> path = new LinkedList<>(pathSoFar.getPath());
                path.add(neighbor);
                nodesToCheck.add(new PathSoFar(neighbor, path));
            }
        }

        return null;
    }


















    // tests

    @Test
    public void twoHopPath1Test() throws Exception {
        final String[] expected = {"a", "c", "e"};
        final String[] actual = getPath(getNetwork(), "a", "e");
        assertNotNull(actual);
        assertArrayEquals(expected, actual);
    }

    @Test
    public void twoHopPath2Test() throws Exception {
        final String[] expected = {"d", "a", "c"};
        final String[] actual = getPath(getNetwork(), "d", "c");
        assertNotNull(actual);
        assertArrayEquals(expected, actual);
    }

    @Test
    public void oneHopPath1Test() throws Exception {
        final String[] expected = {"a", "c"};
        final String[] actual = getPath(getNetwork(), "a", "c");
        assertNotNull(actual);
        assertArrayEquals(expected, actual);
    }

    @Test
    public void oneHopPath2Test() throws Exception {
        final String[] expected = {"f", "g"};
        final String[] actual = getPath(getNetwork(), "f", "g");
        assertNotNull(actual);
        assertArrayEquals(expected, actual);
    }

    @Test
    public void oneHopPath3Test() throws Exception {
        final String[] expected = {"g", "f"};
        final String[] actual = getPath(getNetwork(), "g", "f");
        assertNotNull(actual);
        assertArrayEquals(expected, actual);
    }

    @Test
    public void zeroHopPath() throws Exception {
        final String[] expected = {"a"};
        final String[] actual = getPath(getNetwork(), "a", "a");
        assertNotNull(actual);
        assertArrayEquals(expected, actual);
    }

    @Test
    public void noPathTest() throws Exception {
        final String[] actual = getPath(getNetwork(), "a", "f");
        assertNull(actual);
    }

    @Test(expected = Exception.class)
    public void startNodeNotPresentTest() throws Exception {
        getPath(getNetwork(), "h", "a");
    }

    @Test(expected = Exception.class)
    public void endNodeNotPresentTest() throws Exception {
        getPath(getNetwork(), "a", "h");
    }

    private static Map<String, String[]> getNetwork() {
        return new HashMap<String, String[]>() { {
            put("a", new String[] {"b", "c", "d"});
            put("b", new String[] {"a", "d"});
            put("c", new String[] {"a", "e"});
            put("d", new String[] {"a", "b"});
            put("e", new String[] {"c"});
            put("f", new String[] {"g"});
            put("g", new String[] {"f"});
        }};
    }

    public static void main(String[] args) {
        Result result = JUnitCore.runClasses(MeshMessage.class);
        for (Failure failure : result.getFailures()) {
            System.out.println(failure.toString());
        }
        if (result.wasSuccessful()) {
            System.out.println("All tests passed.");
        }
    }
}