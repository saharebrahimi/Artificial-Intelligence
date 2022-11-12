import java.util.ArrayList;

public abstract class State {
    public State father;
    ArrayList<GraphNode> graph = new ArrayList<>();
    public double g_cost;
    public double f_cost;
    public float fitness;

    public void setFather(State father) {
        this.father = father;
    }

    public abstract boolean equality(State state);

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public abstract ArrayList<GraphNode> getGraph();

    public abstract void setGraph(ArrayList<GraphNode> graph);


}
