import java.util.ArrayList;
import java.util.Random;

public class Coloring extends Problem{

    int numberOfColors;
    int[][] graphMatrix;

    public Coloring(int numberOfColors, int[][] graphMatrix) {
        this.numberOfColors = numberOfColors;
        this.graphMatrix = graphMatrix;
        Random random = new Random();

        ColoringStates initState = new ColoringStates();
        for (int i = 0; i < graphMatrix.length; i++) {
            int r = random.nextInt(numberOfColors);
            GraphNode graphNode = new GraphNode(i, r);
            initState.graph.add(graphNode);
        }

        for (int i = 0; i < graphMatrix.length; i++) {
            for (int j = 0; j < graphMatrix[i].length; j++) {
                if (graphMatrix[i][j] == 1 && i != j) {
                    initState.graph.get(i).neighbors.add(initState.graph.get(j));
                }
            }
        }

        System.out.println("initial state is : ");
        for(GraphNode g : initState.graph){
            System.out.println("node " + g.nodeNum + " color " + g.color);
        }

        initState.fitness = fitness(initState);
        setInitialState(initState);
    }

    @Override
    public void randomInitialState(){
        Random random = new Random();
        ColoringStates initState = new ColoringStates();
        for (int i = 0; i < graphMatrix.length; i++) {
            int r = random.nextInt(numberOfColors);
            GraphNode graphNode = new GraphNode(i, r);
            initState.graph.add(graphNode);
        }

        for (int i = 0; i < graphMatrix.length; i++) {
            for (int j = 0; j < graphMatrix[i].length; j++) {
                if (graphMatrix[i][j] == 1 && i != j) {
                    initState.graph.get(i).neighbors.add(initState.graph.get(j));
                }
            }
        }
        initState.fitness = fitness(initState);
        setInitialState(initState);

    }

    @Override
    ArrayList<Action> actions(State s) {
        ColoringStates gs = (ColoringStates) s;
        ArrayList<Action> actions = new ArrayList<>();
        for (int i = 0; i < gs.graph.size(); i++) {
            for (int j = 0; j < numberOfColors; j++) {
                actions.add(new ColoringAction(i, j));
            }
        }
        return actions;
    }

    @Override
    State result(State s, Action a) {

        ColoringStates gs = (ColoringStates) s;
        ColoringStates rs = new ColoringStates();
        ColoringAction ga = (ColoringAction) a;

        for (int i = 0; i < gs.graph.size(); i++) {
            rs.graph.add(new GraphNode(i, gs.graph.get(i).color));
        }

        for (int i = 0; i < gs.graph.size(); i++) {
            for (int j = 0; j < gs.graph.get(i).neighbors.size(); j++) {
                rs.graph.get(i).neighbors.add(rs.graph.get(gs.graph.get(i).neighbors.get(j).nodeNum));
            }
        }

        rs.graph.get(ga.changedNode).color = ga.changedColor;
        rs.fitness = fitness(rs);
        rs.father = s;

        return rs;
    }

    @Override
    Action getActionDone(State a, State b) {
        ColoringStates gsa = (ColoringStates) a;
        ColoringStates gsb = (ColoringStates) b;
        ArrayList<ColoringAction> actions = new ArrayList<>();
        for (int i = 0; i < gsa.graph.size(); i++) {
            for (int j = 0; j < numberOfColors; j++) {
                actions.add(new ColoringAction(i, j));
            }
        }

        for (ColoringAction act : actions) {
            ColoringStates rs = (ColoringStates) result(gsa, act);
            if (gsb.equality(rs)) {
                return act;
            }
        }
        return null;
    }

    @Override
    int stepCost(State s, Action a) {
        return 0;
    }

    @Override
    double heuristic(State s) {
        return 0;
    }

    @Override
    int fitness(State s) {
        int f = 0;
        ColoringStates gs = (ColoringStates) s;
        for (GraphNode graphNode : gs.graph) {
            for (GraphNode g : graphNode.neighbors) {
                if (g.color == graphNode.color) {
                    f++;
                }
            }
        }
        return f;
    }


}
