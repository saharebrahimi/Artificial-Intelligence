import java.util.ArrayList;

public abstract class Problem {
    State initialState;
    State goalState;
    double h = 0;

    public void setInitialState(State initialState) {
        this.initialState = initialState;
    }

    public State getInitialState() {
        return initialState;
    }

    public State getGoalState() {
        return goalState;
    }

    public void setGoalState(State goalState) {
        this.goalState = goalState;
    }

    public boolean goalTest(State s) {
        if (s.equality(goalState)) {
            return true;
        }

        return false;
    }

    abstract ArrayList<Action> actions(State s);

    abstract State result(State s, Action a);

    abstract Action getActionDone(State a, State b);

    abstract int stepCost(State s, Action a);

    abstract double heuristic(State s);

    abstract int fitness(State s);

    abstract void randomInitialState();


}
