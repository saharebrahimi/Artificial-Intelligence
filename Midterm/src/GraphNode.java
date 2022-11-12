import java.util.ArrayList;

public class GraphNode {
    int nodeNum;
    public ArrayList<GraphNode> neighbors = new ArrayList<>();
    public int color;

    public GraphNode(int nodeNum, int color){
        this.nodeNum = nodeNum;
        this.color = color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public int getColor() {
        return color;
    }
}
