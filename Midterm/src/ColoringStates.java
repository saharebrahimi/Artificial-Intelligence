import java.util.ArrayList;

public class ColoringStates extends State {
    ArrayList<GraphNode> graph = new ArrayList<>();

    @Override
    public boolean equality(State state) {

        if (state == null)
            return false;

        ColoringStates gs = (ColoringStates) state;
        for (int i = 0; i < gs.graph.size(); i++) {
            if (gs.graph.get(i).color != this.graph.get(i).color) {
                return false;
            }
        }

        return true;
    }

    public ArrayList<GraphNode> getGraph() {
        return graph;
    }

    public void setGraph(ArrayList<GraphNode> graph) {
        this.graph = graph;
    }
}
