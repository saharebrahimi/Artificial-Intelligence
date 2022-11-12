import java.util.ArrayList;
import java.util.Random;

public class SearchTwo {

    public Problem problem;
    ArrayList<State> path = new ArrayList<>();
    ArrayList<Action> actions = new ArrayList<>();
    int visited;
    int expanded;
    State currState;
    State finalState;
    int generationnum = 0;
    ArrayList<Float> avgF = new ArrayList<Float>();
    ArrayList<Float> bestF = new ArrayList<Float>();
    ArrayList<Float> worstF = new ArrayList<Float>();

    public void hillClimbing() {
        currState = problem.initialState;
        State preState = null;

        while (!currState.equality(preState)) {
            ArrayList<Action> actions = problem.actions(currState);
            preState = currState;
            for (Action a : actions) {
                visited++;
                if (problem.result(currState, a).fitness < currState.fitness) {
                    currState = problem.result(currState, a);
                    expanded++;
                }
            }
        }

        finalState = currState;
        getPath(problem.initialState, finalState);
        getActions();
    }

    public void firstChoiceHillClimbing() {
        currState = problem.initialState;
        State preState = null;

        while (!currState.equality(preState)) {
            ArrayList<Action> actions = problem.actions(currState);
            preState = currState;
            for (Action a : actions) {
                visited++;
                if (problem.result(currState, a).fitness < currState.fitness) {
                    currState = problem.result(currState, a);
                    expanded++;
                    break;
                }
            }
        }

        finalState = currState;
        getPath(problem.initialState, finalState);
        getActions();
    }

    public void stochasticHillClimbing() {
        currState = problem.initialState;
        ArrayList<State> betterStates = new ArrayList<>();
        State preState = null;

        while (!currState.equality(preState)) {
            ArrayList<Action> actions = problem.actions(currState);
            preState = currState;
            for (Action a : actions) {
                visited++;
                if (problem.result(currState, a).fitness < currState.fitness) {
                    betterStates.add(problem.result(currState, a));
                }
            }

            Random rand = new Random();
            if (betterStates.size() > 0) {
                int r = rand.nextInt(betterStates.size());
                currState = betterStates.get(r);
                betterStates.clear();
                expanded++;
            }
        }

        finalState = currState;
        getPath(problem.initialState, finalState);
        getActions();
    }

    public void randomRestartHillClimbing() {
        for (int i = 0; i < 6; i++) {
            hillClimbing();
            problem.randomInitialState();
        }
    }

    public void simulatedAnnealing(int selection) {
        currState = problem.initialState;
        ArrayList<State> successors = new ArrayList<>();
        double T = 100;
        int time = 1;

        while (T > 0.0) {
            T = calculate(time, selection);
            time++;
            ArrayList<Action> actions = problem.actions(currState);
            for (Action a : actions) {
                visited++;
                successors.add(problem.result(currState, a));
            }


            Random random = new Random();
            int r = random.nextInt(successors.size());
            State s_state = successors.get(r);
            if (s_state.fitness <= currState.fitness) {
                currState = s_state;
                expanded++;
            } else {

                float choice = random.nextFloat();
                float deltaE = (float) (currState.fitness - s_state.fitness);
                float probability = (float) Math.pow(2.0, deltaE / (float) T);
                if (choice < probability) {
                    currState = s_state;
                }
                expanded++;
            }

            successors.clear();
        }

        finalState = currState;
        getPath(problem.initialState, finalState);
        getActions();

    }


    private double calculate(double time, int schedule) {
        if (time < 10000)
            return 1 / Math.pow(time, schedule);
        else
            return 0;
    }


    public void genetic(int populationSize, int k, float mutationRate, int generationnum) {
        this.generationnum = generationnum;
        ArrayList<State> population = new ArrayList<>();
        currState = problem.initialState;
        float mutatedGenomes;

        ArrayList<State> parents = new ArrayList<>();
        ArrayList<GraphNode> crossover;
        int numberOfParents;


        numberOfParents = populationSize / k;
        if (numberOfParents % k != 0) {
            numberOfParents = numberOfParents + (numberOfParents % k);
        }

        for (int i = 0; i < populationSize; i++) {
            problem.randomInitialState();
            population.add(problem.initialState);
            problem.initialState.fitness = 1 - ((float) problem.fitness(problem.initialState) / (float) 40);
        }
        while (generationnum > 0) {
            parents.clear();
            Random random = new Random();
            float bestfitness = 0;
            float avefitness = 0;
            float worstfitness = 1000;
            int index = 0;
            for (int i = 0; i < numberOfParents; i++) {
                bestfitness = 0;
                worstfitness = 1000;
                // avefitness=0;
                for (int j = 0; j < k; j++) {
                    int r = random.nextInt(populationSize);
                    avefitness = population.get(r).fitness + avefitness;
                    if (bestfitness < population.get(r).fitness) {
                        bestfitness = population.get(r).fitness;
                        index = r;
                    }
                    if (worstfitness > population.get(r).fitness) {
                        worstfitness = population.get(r).fitness;

                    }

                }

                parents.add(population.get(index));

            }

            avefitness /= population.size();
            avgF.add(avefitness);
            bestF.add(bestfitness);
            worstF.add(worstfitness);


            for (int i = 0; i < populationSize; i++) {
                crossover = new ArrayList<>();
                int x = random.nextInt(parents.size());
                int y = random.nextInt(parents.size());
                while (x == y) {
                    y = random.nextInt(parents.size());
                }
                crossover.add(parents.get(x).getGraph().get(0));
                crossover.add(parents.get(x).getGraph().get(1));
                crossover.add(parents.get(x).getGraph().get(2));
                crossover.add(parents.get(x).getGraph().get(3));
                crossover.add(parents.get(x).getGraph().get(4));
                crossover.add(parents.get(y).getGraph().get(5));
                crossover.add(parents.get(y).getGraph().get(6));
                crossover.add(parents.get(y).getGraph().get(7));
                crossover.add(parents.get(y).getGraph().get(8));
                crossover.add(parents.get(y).getGraph().get(9));
                crossover.add(parents.get(y).getGraph().get(10));
                mutatedGenomes = mutationRate * 11 * populationSize;
                for (int u = 0; u < mutatedGenomes; u++) {
                    int change = random.nextInt(crossover.size());
                    int newcolor = random.nextInt(3);
                    crossover.get(change).setColor(newcolor);
                }

                for (int q = 0; q < crossover.size(); q++) {
                    population.get(i).getGraph().get(q).setColor(crossover.get(q).getColor());
                    //System.out.print("node" + q + " " + crossover.get(q).getColor() + "   ");}
                }


            }


            generationnum--;
        }


//
//            for (int m=0 ; m<population.size() ; m++){
//                for (int j=0; j<population.get(m).getGraph().size() ; j++) {
//                    System.out.print("node" + j + " " + population.get(m).getGraph().get(j).getColor() + "   ");
//                }
//                System.out.println();}


    }

    public void getPath(State initState, State finalState) {

        State state = finalState;
        while (state != initState) {

            path.add(state);
            state = state.father;
        }

        path.add(initState);
    }

    public void getActions() {
        for (int i = path.size() - 1; i > 0; i--) {
            actions.add(problem.getActionDone(path.get(i), path.get(i - 1)));
        }

    }
}
