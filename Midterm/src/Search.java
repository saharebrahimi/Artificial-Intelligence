import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class Search {

    public int totalcost;
    public int pathcost;
    public Problem problem;
    public boolean expandedBefore = false;
    public boolean addedBefore = false;
    public Queue<State> f_queue = new LinkedList<>();
    public Stack<State> f_stack = new Stack<>();
    public ArrayList<State> f = new ArrayList<>();
    public ArrayList<State> e = new ArrayList<>();
    public ArrayList<State> path = new ArrayList<>();
    public ArrayList<Action> actions = new ArrayList<>();
    public State currState;
    public int maxMemoryUsed = 0;
    public boolean goalFound = false;
    public double pathCost = 0;
    public int numberOfExpandeds;
    public int numberOfNodesVisited = 1;

    public Search(Problem problem) {
        this.problem = problem;
    }

    public void bfs() {

        f_queue.add(problem.getInitialState());
        while (f_queue.size() > 0) {

            if (f_queue.size() + e.size() > maxMemoryUsed) {
                maxMemoryUsed = f_queue.size() + e.size();
            }
            currState = f_queue.remove();
            //generating child nodes
            ArrayList<Action> actionsList = problem.actions(currState);
            System.out.print(currState.getName() + "  ");
            pathcost = pathcost + problem.getActionDone(currState.father, currState).getAction();

            //if node is not leaf
            if (actionsList.size() != 0) {
                for (Action a : actionsList) {
                    expandedBefore = false;
                    addedBefore = false;
                    State s = problem.result(currState, a);
                    numberOfNodesVisited++;
                    totalcost = totalcost + a.getAction();
                    //check if result state has been expanded before
                    for (State st : e) {
                        if (st.equality(s)) {
                            expandedBefore = true;
                            break;
                        }
                    }

                    for (State st : f_queue) {
                        if (st.equality(s)) {
                            addedBefore = true;
                            break;
                        }
                    }

                    if (!expandedBefore && !addedBefore) {
                        s.setFather(currState);

                        f_queue.add(s);
                        if (problem.goalTest(currState)) {
                            System.out.println();
                            System.out.println("solution found");
                            numberOfExpandeds = e.size() + 1;
                            getPath(problem.initialState, s);
                            getActions();
                            return;
                        }
                    }
                }
            }

            e.add(currState);
        }


        System.out.println("no solution found for this problem");

    }

    public void dfs() {

        f_stack.push(problem.initialState);
        while (f_stack.size() > 0) {

            if (f_stack.size() + e.size() > maxMemoryUsed) {
                maxMemoryUsed = f_stack.size() + e.size();
            }

            currState = f_stack.pop();
            System.out.print(currState.getName() + "  ");
            pathcost = pathcost + problem.getActionDone(currState.father, currState).getAction();
            //generating child nodes
            ArrayList<Action> actionsList = problem.actions(currState);

            //if node is not leaf
            if (actionsList.size() != 0) {
                for (Action a : actionsList) {
                    expandedBefore = false;
                    addedBefore = false;
                    State s = problem.result(currState, a);
                    totalcost = totalcost + a.getAction();
                    numberOfNodesVisited++;
                    //check if result state has been expanded before
                    for (State st : e) {
                        if (st.equality(s)) {
                            expandedBefore = true;
                            break;
                        }
                    }

                    for (State st : f_stack) {
                        if (st.equality(s)) {
                            addedBefore = true;
                            break;
                        }
                    }

                    if (!expandedBefore && !addedBefore) {
                        s.setFather(currState);
                        f_stack.push(s);
                        if (problem.goalTest(currState)) {
                            System.out.println();
                            System.out.println("solution found");
                            getPath(problem.initialState, s);
                            getActions();
                            numberOfExpandeds = e.size();
                            return;
                        }
                    }
                }
            }

            e.add(currState);
        }

        System.out.println("no solution found for this problem");


    }

    public void depthLimitedDFS(int limit) {

        f_stack.push(problem.initialState);
        int depth = 0;
        while (f_stack.size() > 0) {

            if (f_stack.size() + e.size() > maxMemoryUsed) {
                maxMemoryUsed = f_stack.size() + e.size();
            }

            currState = f_stack.pop();
            System.out.print(currState.getName() + "   ");
            pathcost = pathcost + problem.getActionDone(currState.father, currState).getAction();

            //generating child nodes
            ArrayList<Action> actionsList = problem.actions(currState);

            //if node is not leaf
            if (actionsList.size() != 0) {
                for (Action a : actionsList) {
                    expandedBefore = false;
                    addedBefore = false;
                    State s = problem.result(currState, a);
                    totalcost = totalcost + a.getAction();
                    s.setFather(currState);
                    numberOfNodesVisited++;
                    //check if result state has been expanded before
                    for (State st : e) {
                        if (st.equality(s)) {
                            expandedBefore = true;
                            break;
                        }
                    }

                    for (State st : f_stack) {
                        if (st.equality(s)) {
                            addedBefore = true;
                            break;
                        }
                    }

                    depth = computeDepth(s);

                    if (!expandedBefore && !addedBefore && limit >= depth) {
                        f_stack.push(s);
                        if (problem.goalTest(currState)) {
                            System.out.println();
                            System.out.println("solution found");
                            System.out.println();
                            getPath(problem.initialState, s);
                            getActions();
                            numberOfExpandeds = e.size();
                            goalFound = true;
                            return;
                        }
                    }
                }
            }

            e.add(currState);
        }
        System.out.println();
        System.out.println("no solution found for this problem");
        System.out.println();
    }


    public void iterativeDFS() {
        int d = 0;
        while (!goalFound) {
            System.out.print("path:");
            depthLimitedDFS(d);
            e.clear();
            d++;
        }
    }

    public void ucs() {

        f.add(problem.getInitialState());
        problem.initialState.g_cost = 0;
        currState = problem.initialState;

        while (f.size() > 0) {

            if (f.size() + e.size() > maxMemoryUsed) {
                maxMemoryUsed = f.size() + e.size();
            }

            double min = 1000000000;
            int minIndex = 0;
            for (int i = 0; i < f.size(); i++) {
                if (f.get(i).g_cost <= min) {
                    min = f.get(i).g_cost;
                    minIndex = i;
                }
            }

            currState = f.get(minIndex);
            pathcost = pathcost + problem.getActionDone(currState.father, currState).getAction();
            System.out.print(currState.getName() + "   ");
            f.remove(minIndex);

            if (problem.goalTest(currState)) {
                System.out.println();
                System.out.println("solution found");
                getPath(problem.initialState, currState);
                getActions();
                numberOfExpandeds = e.size();
                pathCost = currState.g_cost - 32;
                return;
            } else {
                //generating child nodes
                ArrayList<Action> actionsList = problem.actions(currState);
                //if node is not leaf
                if (actionsList.size() != 0) {
                    for (Action a : actionsList) {
                        expandedBefore = false;
                        addedBefore = false;
                        State s = problem.result(currState, a);
                        totalcost = a.getAction() + totalcost;
                        s.setFather(currState);
                        s.g_cost = s.father.g_cost + problem.getActionDone(s.father, s).getAction();
                        numberOfNodesVisited++;
                        //check if result state has been expanded before
                        for (State st : e) {
                            if (st.equality(s)) {
                                expandedBefore = true;
                                break;
                            }
                        }

                        for (State st : f) {

                            if (st.equality(s)) {
                                addedBefore = true;
                                break;
                            }
                        }

                        if (!expandedBefore && !addedBefore) {
                            f.add(s);
                        }
                    }
                }
            }

            e.add(currState);
        }


        System.out.println("no solution found for this problem");

    }

    public void aStar() {
        f.add(problem.getInitialState());
        problem.initialState.g_cost = 0;
        problem.initialState.f_cost = problem.heuristic(problem.initialState) + problem.initialState.g_cost;
        currState = problem.initialState;

        while (f.size() > 0) {

            if (f.size() + e.size() > maxMemoryUsed) {
                maxMemoryUsed = f.size() + e.size();
            }

            double min = 1000000000;
            int minIndex = 0;
            for (int i = 0; i < f.size(); i++) {
                if (f.get(i).f_cost <= min) {
                    min = f.get(i).f_cost;
                    minIndex = i;
                }
            }

            currState = f.get(minIndex);
            pathcost = pathcost + problem.getActionDone(currState.father, currState).getAction();
            System.out.print(currState.getName() + "   ");
            f.remove(minIndex);
            if (problem.goalTest(currState)) {
                System.out.println();
                System.out.println("solution founded");
                getPath(problem.initialState, currState);
                getActions();
                numberOfExpandeds = e.size();
                pathCost = currState.g_cost;
                return;
            } else {

                //generating child nodes
                ArrayList<Action> actionsList = problem.actions(currState);

                //if node is not leaf
                if (actionsList.size() != 0) {
                    for (Action a : actionsList) {
                        expandedBefore = false;
                        addedBefore = false;
                        State s = problem.result(currState, a);
                        totalcost = a.getAction() + totalcost;
                        s.setFather(currState);
                        s.g_cost = s.father.g_cost + problem.getActionDone(s.father, s).getAction();
                        s.f_cost = problem.heuristic(s) + s.g_cost;
                        numberOfNodesVisited++;
                        //check if result state has been expanded before
                        for (State st : e) {
                            if (st.equality(s)) {
                                expandedBefore = true;
                                break;
                            }
                        }

                        for (State st : f) {
                            if (st.equality(s)) {
                                addedBefore = true;
                                break;
                            }
                        }

                        if (!expandedBefore && !addedBefore) {
                            f.add(s);
                        }
                    }
                }
            }

            e.add(currState);
        }

        System.out.println("no solution found for this problem");


    }

    public void greedy() {
        f.add(problem.getInitialState());
        problem.initialState.f_cost = problem.heuristic(problem.initialState);
        currState = problem.initialState;

        while (f.size() > 0) {

            if (f.size() + e.size() > maxMemoryUsed) {
                maxMemoryUsed = f.size() + e.size();
            }

            double min = 1000000000;
            int minIndex = 0;
            for (int i = 0; i < f.size(); i++) {
                if (f.get(i).f_cost <= min) {
                    min = f.get(i).f_cost;
                    minIndex = i;
                }
            }

            currState = f.get(minIndex);

            System.out.print(currState.getName() + "   ");
            pathcost = pathcost + problem.getActionDone(currState.father, currState).getAction();
            f.remove(minIndex);

            if (problem.goalTest(currState)) {
                System.out.println();
                System.out.println("solution founded");
                getPath(problem.initialState, currState);
                getActions();
                numberOfExpandeds = e.size();
                pathCost = pathcost;
                return;
            } else {

                //generating child nodes
                ArrayList<Action> actionsList = problem.actions(currState);

                //if node is not leaf
                if (actionsList.size() != 0) {
                    for (Action a : actionsList) {
                        expandedBefore = false;
                        addedBefore = false;
                        State s = problem.result(currState, a);
                        totalcost = a.getAction() + totalcost;
                        s.setFather(currState);
                        s.f_cost = problem.heuristic(s);
                        numberOfNodesVisited++;
                        //check if result state has been expanded before
                        for (State st : e) {
                            if (st.equality(s)) {
                                expandedBefore = true;
                                break;
                            }
                        }

                        for (State st : f) {
                            if (st.equality(s)) {
                                addedBefore = true;
                                break;
                            }
                        }

                        if (!expandedBefore && !addedBefore) {
                            f.add(s);
                        }
                    }
                }
            }

            e.add(currState);
        }

        System.out.println("no solution found for this problem");


    }


    private int computeDepth(State s) {
        int depth = 0;
        while (s != problem.initialState) {
            depth++;
            s = s.father;
        }

        return depth;
    }


    public void getPath(State initState, State finalState) {

        State state = finalState;
        while (state != initState) {

            path.add(state);
            state = state.father;
        }

        path.add(initState);
    }

    public void revers() {
        ArrayList<State> temp = new ArrayList<>();
        for (int i = path.size() - 1; i >= 0; i--) {
            temp.add(path.get(i));
        }
        path = temp;
    }


    public void getActions() {
        for (int i = path.size() - 1; i > 0; i--) {
            actions.add(problem.getActionDone(path.get(i), path.get(i - 1)));
        }


    }


}
