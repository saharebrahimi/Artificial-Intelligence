import java.util.ArrayList;


public class Main {
    public static void main(String[] args) {
///problem 1
//        Romania R = new Romania() {
//            @Override
//            int fitness(State s) {
//                return 0;
//            }
//
//            @Override
//            void randomInitialState() {
//
//            }
//        };
//        Search search = new Search(R);
//         System.out.print("Expanded Node: " );
//
//           search.bfs();
//          //search.dfs();
//        //  search.depthLimitedDFS(4);
//       //   search.iterativeDFS();
//        //search.ucs();
//         //  search.aStar();
//        //search.greedy();
//        search.revers();
//        for (int i=0; i<search.path.size()-1 ; i++)
//            System.out.print(search.path.get(i).getName()+" ");
//        System.out.println();
//        System.out.println("Number of nodes expanded =" + search.numberOfExpandeds);
//        System.out.println("Number of nodes visited =" + search.numberOfNodesVisited);
//        System.out.println("Maximum memory used = " + search.maxMemoryUsed);
//        System.out.println("PathCost=" + search.pathcost);
//        System.out.println("TotalCost=" + search.totalcost);
//        System.out.println("DirectPathCost="+search.pathCost);
//
//

//problem 2

        SearchTwo s = new SearchTwo();
        int[][] k = {{0, 1, 1, 0, 0, 0, 0, 0, 1, 0, 1}, {1, 0, 0, 1, 0, 0, 1, 0, 0, 0, 0},
                {1, 0, 0, 0, 1, 0, 1, 0, 0, 0, 0}, {1, 0, 0, 0, 1, 1, 0, 0, 0, 0, 1},
                {0, 0, 1, 1, 0, 0, 0, 1, 1, 0, 0}, {0, 0, 0, 1, 0, 0, 1, 0, 1, 0, 0},
                {0, 1, 1, 0, 0, 1, 0, 1, 0, 1, 0}, {0, 0, 0, 1, 0, 0, 1, 0, 0, 0, 1},
                {1, 0, 0, 1, 1, 0, 0, 0, 0, 1, 0}, {0, 0, 0, 0, 0, 1, 0, 0, 1, 0, 1},
                {1, 0, 0, 1, 0, 0, 0, 1, 0, 1, 0}};
        Coloring cp = new Coloring(3, k);
        s.problem = cp;
         s.hillClimbing();
        // s.stochasticHillClimbing();
        //s.firstChoiceHillClimbing();
        // s.randomRestartHillClimbing();
        //s.simulatedAnnealing(2);

        System.out.println("number of visited nodes " + s.visited);
        System.out.println("number of expanded nodes " + s.expanded);
        System.out.println("actions : ");
        for (Action actt : s.actions) {
            ColoringAction a = (ColoringAction) actt;
            if (a != null)
                System.out.println("change color of node " + a.changedNode + " to " + a.changedColor);
        }
        System.out.println("final graph is : ");

        for (GraphNode g : ((ColoringStates) s.finalState).graph) {
            System.out.println("node " + g.nodeNum + " color " + g.color);
        }

        System.out.println("final state fitness is : " + s.finalState.fitness);

//        s.genetic(120, 2, (float) 0.1, 16);
//        System.out.println("average fitness  " + s.avgF);
//        System.out.println("best fitness  " + s.bestF);
//        System.out.println("worst fitness " + s.worstF);
//        System.out.println("generation number " + s.generationnum);
    }


}
