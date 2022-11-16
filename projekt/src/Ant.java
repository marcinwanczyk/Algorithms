import java.util.ArrayList;
import java.util.List;

public class Ant {

    private List<Integer> trail = new ArrayList<>();
    private boolean[] visited = new boolean[16];
    public void visitCity(int currentIndex, int city) {
        trail.add(currentIndex + 1, city);
        visited[city] = true;
    }

    public boolean visited(int i) {
        return visited[i];
    }

    public double trailLength(double graph[][]) {
        double length = graph[trail.get(trail.toArray().length - 1)][trail.get(0)];
        for (int i = 0; i < trail.toArray().length - 1; i++) {
            length += graph[trail.get(i)][trail.get(i + 1)];
        }
        return length;
    }
}
