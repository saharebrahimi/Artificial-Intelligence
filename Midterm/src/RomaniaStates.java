import java.util.ArrayList;
public class RomaniaStates extends State {
    private String name;

    public RomaniaStates() {
    }

    public RomaniaStates(String name) {
        this.name = name;
    }

    @Override
    public boolean equality(State state) {
        if (this.getName().equals(((RomaniaStates) state).getName()))
            return true;
        else
            return false;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public ArrayList<GraphNode> getGraph() {
        return null;
    }

    @Override
    public void setGraph(ArrayList<GraphNode> graph) {

    }
}
