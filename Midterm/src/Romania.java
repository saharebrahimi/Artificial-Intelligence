import java.util.ArrayList;

public abstract class Romania extends Problem {
    int cost;
    double h = 0;


    public Romania() {
        RomaniaStates initState = new RomaniaStates();
        initState.setName("Arad");
        setInitialState(initState);
    }

    @Override
    public boolean goalTest(State s) {
        RomaniaStates rs = (RomaniaStates) (s);
        if (rs.getName().equals("Bucharest"))
            return true;
        else
            return false;

    }

    @Override
    ArrayList<Action> actions(State s) {
        ArrayList<Action> acts = new ArrayList<>();
        RomaniaStates st = (RomaniaStates) (s);
//        if (st.getName().equals("Oradea")){
//            acts.add(new RomaniaAction(151));
//            acts.add(new RomaniaAction(71));
//
//        }
//
//        if (st.getName().equals("Zerind")){
//            acts.add(new RomaniaAction(75));
//            acts.add(new RomaniaAction(71));
//
//
//        }
//
//        if (st.getName().equals("Arad")){
//            acts.add(new RomaniaAction(140));
//            acts.add(new RomaniaAction(118));
//            acts.add(new RomaniaAction(75));
//
//
//        }
//
//        if (st.getName().equals("Timisoara")){
//            acts.add(new RomaniaAction(118));
//            acts.add(new RomaniaAction(111));
//
//        }
//
//        if (st.getName().equals("Lugoj"))
//        {
//            acts.add(new RomaniaAction(70));
//            acts.add(new RomaniaAction(111));
//
//
//        }
//
//        if (st.getName().equals("Mehadia"))
//        {
//            acts.add(new RomaniaAction(75));
//            acts.add(new RomaniaAction(70));
//
//
//        }
//
//        if (st.getName().equals("Doberta")){
//            acts.add(new RomaniaAction(120));
//            acts.add(new RomaniaAction(75));
//
//        }
//
//        if (st.getName().equals("Sibiu")){
//            acts.add(new RomaniaAction(140));
//            acts.add(new RomaniaAction(99));
//            acts.add(new RomaniaAction(80));
//            acts.add(new RomaniaAction(151));
//
//
//        }
//
//
//        if (st.getName().equals("Rimnicuvilcea"))
//        {
//            acts.add(new RomaniaAction(146));
//            acts.add(new RomaniaAction(97));
//            acts.add(new RomaniaAction(80));
//
//        }
//
//        if (st.getName().equals("Craiova"))
//        {
//            acts.add(new RomaniaAction(120));
//            acts.add(new RomaniaAction(138));
//            acts.add(new RomaniaAction(146));
//
//
//        }
//
//
//        if (st.getName().equals("Fagaras"))
//        {
//            acts.add(new RomaniaAction(211));
//            acts.add(new RomaniaAction(99));
//
//
//        }
//
//        if (st.getName().equals("Pitesti"))
//        {
//            acts.add(new RomaniaAction(101));
//            acts.add(new RomaniaAction(138));
//            acts.add(new RomaniaAction(97));
//
//        }
//
//        if (st.getName().equals("Bucharest")) {
//
//            acts.add(new RomaniaAction(211));
//            acts.add(new RomaniaAction(90));
//            acts.add(new RomaniaAction(101));
//            acts.add(new RomaniaAction(85));
//        }
//
//        if (st.getName().equals("Giurgiu"))
//        {acts.add(new RomaniaAction(90));
//
//        }
//
//        if (st.getName().equals("Urziceni"))
//        {
//            acts.add(new RomaniaAction(85));
//            acts.add(new RomaniaAction(98));
//            acts.add(new RomaniaAction(142));
//
//
//
//        }
//
//        if (st.getName().equals("Neamt"))
//        {
//            acts.add(new RomaniaAction(87));
//
//        }
//
//
//        if (st.getName().equals("Lasi"))
//        {
//
//            acts.add(new RomaniaAction(87));
//            acts.add(new RomaniaAction(92));
//
//        }
//
//
//        if (st.getName().equals("Vaslui"))
//        {
//            acts.add(new RomaniaAction(92));
//            acts.add(new RomaniaAction(142));
//        }
//
//
//        if (st.getName().equals("Hirsova"))
//        {
//            acts.add(new RomaniaAction(86));
//            acts.add(new RomaniaAction(98));
//        }
//
//
//        if (st.getName().equals("Eforie"))
//        {
//            acts.add(new RomaniaAction(86));
//        }


        if (st.getName().equals("Oradea")) {
            acts.add(new RomaniaAction(71));
            acts.add(new RomaniaAction(151));


        }

        if (st.getName().equals("Zerind")) {
            acts.add(new RomaniaAction(71));
            acts.add(new RomaniaAction(75));


        }

        if (st.getName().equals("Arad")) {
            acts.add(new RomaniaAction(75));
            acts.add(new RomaniaAction(118));
            acts.add(new RomaniaAction(140));


        }

        if (st.getName().equals("Timisoara")) {
            acts.add(new RomaniaAction(111));
            acts.add(new RomaniaAction(118));


        }

        if (st.getName().equals("Lugoj")) {
            acts.add(new RomaniaAction(111));
            acts.add(new RomaniaAction(70));


        }

        if (st.getName().equals("Mehadia")) {
            acts.add(new RomaniaAction(70));
            acts.add(new RomaniaAction(75));


        }

        if (st.getName().equals("Doberta")) {
            acts.add(new RomaniaAction(75));

            acts.add(new RomaniaAction(120));

        }

        if (st.getName().equals("Sibiu")) {

            acts.add(new RomaniaAction(151));
            acts.add(new RomaniaAction(80));
            acts.add(new RomaniaAction(99));
            acts.add(new RomaniaAction(140));
        }


        if (st.getName().equals("Rimnicuvilcea")) {
            acts.add(new RomaniaAction(80));
            acts.add(new RomaniaAction(97));

            acts.add(new RomaniaAction(146));
        }

        if (st.getName().equals("Craiova")) {
            acts.add(new RomaniaAction(146));
            acts.add(new RomaniaAction(138));

            acts.add(new RomaniaAction(120));

        }


        if (st.getName().equals("Fagaras")) {

            acts.add(new RomaniaAction(99));
            acts.add(new RomaniaAction(211));

        }

        if (st.getName().equals("Pitesti")) {
            acts.add(new RomaniaAction(97));
            acts.add(new RomaniaAction(138));
            acts.add(new RomaniaAction(101));

        }

        if (st.getName().equals("Bucharest")) {

            acts.add(new RomaniaAction(85));
            acts.add(new RomaniaAction(101));
            acts.add(new RomaniaAction(90));
            acts.add(new RomaniaAction(211));
        }

        if (st.getName().equals("Giurgiu")) {
            acts.add(new RomaniaAction(90));

        }

        if (st.getName().equals("Urziceni")) {
            acts.add(new RomaniaAction(98));
            acts.add(new RomaniaAction(142));
            acts.add(new RomaniaAction(85));


        }

        if (st.getName().equals("Neamt")) {
            acts.add(new RomaniaAction(87));

        }


        if (st.getName().equals("Lasi")) {
            acts.add(new RomaniaAction(92));
            acts.add(new RomaniaAction(87));


        }


        if (st.getName().equals("Vaslui")) {
            acts.add(new RomaniaAction(142));
            acts.add(new RomaniaAction(92));

        }


        if (st.getName().equals("Hirsova")) {
            acts.add(new RomaniaAction(98));
            acts.add(new RomaniaAction(86));

        }


        if (st.getName().equals("Eforie")) {
            acts.add(new RomaniaAction(86));
        }


        return acts;
    }

    @Override
    State result(State s, Action a) {
        RomaniaAction act = (RomaniaAction) (a);
        RomaniaStates st = (RomaniaStates) (s);
        RomaniaStates rcs = new RomaniaStates();
        if (st.getName().equals("Oradea") && act.getAction() == 71)
            rcs.setName("Zerind");
        if (st.getName().equals("Oradea") && act.getAction() == 151)
            rcs.setName("Sibiu");

        if (st.getName().equals("Zerind") && act.getAction() == 71)
            rcs.setName("Oradea");
        if (st.getName().equals("Zerind") && act.getAction() == 75)
            rcs.setName("Arad");

        if (st.getName().equals("Arad") && act.getAction() == 118)
            rcs.setName("Timisoara");
        if (st.getName().equals("Arad") && act.getAction() == 75)
            rcs.setName("Zerind");
        if (st.getName().equals("Arad") && act.getAction() == 140)
            rcs.setName("Sibiu");

        if (st.getName().equals("Timisoara") && act.getAction() == 111)
            rcs.setName("Lugoj");
        if (st.getName().equals("Timisoara") && act.getAction() == 118)
            rcs.setName("Arad");

        if (st.getName().equals("Lugoj") && act.getAction() == 70)
            rcs.setName("Mehadia");
        if (st.getName().equals("Lugoj") && act.getAction() == 111)
            rcs.setName("Timisoara");

        if (st.getName().equals("Mehadia") && act.getAction() == 75)
            rcs.setName("Doberta");
        if (st.getName().equals("Mehadia") && act.getAction() == 70)
            rcs.setName("Lugoj");


        if (st.getName().equals("Doberta") && act.getAction() == 120)
            rcs.setName("Craiova");
        if (st.getName().equals("Doberta") && act.getAction() == 75)
            rcs.setName("Mehadia");

        if (st.getName().equals("Sibiu") && act.getAction() == 151)
            rcs.setName("Oradea");
        if (st.getName().equals("Sibiu") && act.getAction() == 99)
            rcs.setName("Fagaras");
        if (st.getName().equals("Sibiu") && act.getAction() == 140)
            rcs.setName("Arad");
        if (st.getName().equals("Sibiu") && act.getAction() == 80)
            rcs.setName("Rimnicuvilcea");


        if (st.getName().equals("Rimnicuvilcea") && act.getAction() == 80)
            rcs.setName("Sibiu");
        if (st.getName().equals("Rimnicuvilcea") && act.getAction() == 97)
            rcs.setName("Pitesti");
        if (st.getName().equals("Rimnicuvilcea") && act.getAction() == 146)
            rcs.setName("Craiova");

        if (st.getName().equals("Craiova") && act.getAction() == 146)
            rcs.setName("Rimnicuvilcea");
        if (st.getName().equals("Craiova") && act.getAction() == 138)
            rcs.setName("Pitesti");
        if (st.getName().equals("Craiova") && act.getAction() == 120)
            rcs.setName("Doberta");

        if (st.getName().equals("Fagaras") && act.getAction() == 99)
            rcs.setName("Sibiu");
        if (st.getName().equals("Fagaras") && act.getAction() == 211)
            rcs.setName("Bucharest");


        if (st.getName().equals("Pitesti") && act.getAction() == 97)
            rcs.setName("Rimnicuvilcea");
        if (st.getName().equals("Pitesti") && act.getAction() == 138)
            rcs.setName("Craiova");
        if (st.getName().equals("Pitesti") && act.getAction() == 101)
            rcs.setName("Bucharest");

        if (st.getName().equals("Bucharest") && act.getAction() == 211)
            rcs.setName("Fagaras");
        if (st.getName().equals("Bucharest") && act.getAction() == 101)
            rcs.setName("Pitesti");
        if (st.getName().equals("Bucharest") && act.getAction() == 90)
            rcs.setName("Giurgiu");
        if (st.getName().equals("Bucharest") && act.getAction() == 85)
            rcs.setName("Urziceni");

        if (st.getName().equals("Giurgiu") && act.getAction() == 90)
            rcs.setName("Bucharest");

        if (st.getName().equals("Urziceni") && act.getAction() == 85)
            rcs.setName("Bucharest");
        if (st.getName().equals("Urziceni") && act.getAction() == 142)
            rcs.setName("Vaslui");
        if (st.getName().equals("Urziceni") && act.getAction() == 98)
            rcs.setName("Hirsova");

        if (st.getName().equals("Neamt") && act.getAction() == 87)
            rcs.setName("Lasi");

        if (st.getName().equals("Lasi") && act.getAction() == 87)
            rcs.setName("Neamt");
        if (st.getName().equals("Lasi") && act.getAction() == 92)
            rcs.setName("Vaslui");

        if (st.getName().equals("Vaslui") && act.getAction() == 92)
            rcs.setName("Lasi");
        if (st.getName().equals("Vaslui") && act.getAction() == 142)
            rcs.setName("Urziceni");

        if (st.getName().equals("Hirsova") && act.getAction() == 98)
            rcs.setName("Urziceni");
        if (st.getName().equals("Hirsova") && act.getAction() == 86)
            rcs.setName("Eforie");

        if (st.getName().equals("Eforie") && act.getAction() == 86)
            rcs.setName("Hirsova");


        cost = act.getAction() + cost;
        return rcs;

    }

    @Override
    Action getActionDone(State a, State b) {
        RomaniaStates ar = (RomaniaStates) (a);
        RomaniaStates br = (RomaniaStates) (b);
        if (ar == null && br.getName().equals("Arad"))
            return new RomaniaAction(0);

        if (ar.getName().equals("Oradea") && br.getName().equals("Zerind"))
            return new RomaniaAction(171);

        if (ar.getName().equals("Oradea") && br.getName().equals("Sibiu"))
            return new RomaniaAction(151);

        if (ar.getName().equals("Zerind") && br.getName().equals("Oradea"))
            return new RomaniaAction(71);
        if (ar.getName().equals("Zerind") && br.getName().equals("Arad"))
            return new RomaniaAction(75);


        if (ar.getName().equals("Arad") && br.getName().equals("Timisoara"))
            return new RomaniaAction(118);
        if (ar.getName().equals("Arad") && br.getName().equals("Zerind"))
            return new RomaniaAction(75);
        if (ar.getName().equals("Arad") && br.getName().equals("Sibiu"))
            return new RomaniaAction(140);
        if (ar.getName().equals("Timisoara") && br.getName().equals("Lugoj"))
            return new RomaniaAction(111);
        if (ar.getName().equals("Timisoara") && br.getName().equals("Arad"))
            return new RomaniaAction(118);

        if (ar.getName().equals("Lugoj") && br.getName().equals("Mehadia"))
            return new RomaniaAction(70);
        if (ar.getName().equals("Lugoj") && br.getName().equals("Timisoara"))
            return new RomaniaAction(111);

        if (ar.getName().equals("Mehadia") && br.getName().equals("Doberta"))
            return new RomaniaAction(75);
        if (ar.getName().equals("Mehadia") && br.getName().equals("Lugoj"))
            return new RomaniaAction(70);


        if (ar.getName().equals("Doberta") && br.getName().equals("Craiova"))
            return new RomaniaAction(120);
        if (ar.getName().equals("Doberta") && br.getName().equals("Mehadia"))
            return new RomaniaAction(75);

        if (ar.getName().equals("Sibiu") && br.getName().equals("Oradea"))
            return new RomaniaAction(151);
        if (ar.getName().equals("Sibiu") && br.getName().equals("Fagaras"))
            return new RomaniaAction(99);
        if (ar.getName().equals("Sibiu") && br.getName().equals("Arad"))
            return new RomaniaAction(140);
        if (ar.getName().equals("Sibiu") && br.getName().equals("Rimnicuvilcea"))
            return new RomaniaAction(80);


        if (ar.getName().equals("Rimnicuvilcea") && br.getName().equals("Sibiu"))
            return new RomaniaAction(80);
        if (ar.getName().equals("Rimnicuvilcea") && br.getName().equals("Pitesti"))
            return new RomaniaAction(97);
        if (ar.getName().equals("Rimnicuvilcea") && br.getName().equals("Craiova"))
            return new RomaniaAction(146);

        if (ar.getName().equals("Craiova") && br.getName().equals("Rimnicuvilcea"))
            return new RomaniaAction(146);
        if (ar.getName().equals("Craiova") && br.getName().equals("Pitesti"))
            return new RomaniaAction(138);
        if (ar.getName().equals("Craiova") && br.getName().equals("Doberta"))
            return new RomaniaAction(120);

        if (ar.getName().equals("Fagaras") && br.getName().equals("Sibiu"))
            return new RomaniaAction(99);
        if (ar.getName().equals("Fagaras") && br.getName().equals("Bucharest"))
            return new RomaniaAction(211);


        if (ar.getName().equals("Pitesti") && br.getName().equals("Rimnicuvilcea"))
            return new RomaniaAction(97);
        if (ar.getName().equals("Pitesti") && br.getName().equals("Craiova"))
            return new RomaniaAction(138);
        if (ar.getName().equals("Pitesti") && br.getName().equals("Bucharest"))
            return new RomaniaAction(101);

        if (ar.getName().equals("Bucharest") && br.getName().equals("Fagaras"))
            return new RomaniaAction(211);
        if (ar.getName().equals("Bucharest") && br.getName().equals("Pitesti"))
            return new RomaniaAction(101);
        if (ar.getName().equals("Bucharest") && br.getName().equals("Giurgiu"))
            return new RomaniaAction(90);
        if (ar.getName().equals("Bucharest") && br.getName().equals("Urziceni"))
            return new RomaniaAction(85);

        if (ar.getName().equals("Giurgiu") && br.getName().equals("Bucharest"))
            return new RomaniaAction(90);

        if (ar.getName().equals("Urziceni") && br.getName().equals("Bucharest"))
            return new RomaniaAction(85);
        if (ar.getName().equals("Urziceni") && br.getName().equals("Vaslui"))
            return new RomaniaAction(142);
        if (ar.getName().equals("Urziceni") && br.getName().equals("Hirsova"))
            return new RomaniaAction(98);

        if (ar.getName().equals("Neamt") && br.getName().equals("Lasi"))
            return new RomaniaAction(87);

        if (ar.getName().equals("Lasi") && br.getName().equals("Neamt"))
            return new RomaniaAction(87);
        if (ar.getName().equals("Lasi") && br.getName().equals("Vaslui"))
            return new RomaniaAction(92);

        if (ar.getName().equals("Vaslui") && br.getName().equals("Lasi"))
            return new RomaniaAction(92);
        if (ar.getName().equals("Vaslui") && br.getName().equals("Urziceni"))
            return new RomaniaAction(142);

        if (ar.getName().equals("Hirsova") && br.getName().equals("Urziceni"))
            return new RomaniaAction(98);
        if (ar.getName().equals("Hirsova") && br.getName().equals("Eforie"))
            return new RomaniaAction(86);

        if (ar.getName().equals("Eforie") && br.getName().equals("Hirsova"))
            return new RomaniaAction(86);

        return null;
    }


    @Override
    int stepCost(State s, Action a) {
        return cost;
    }

    @Override
    double heuristic(State s) {

        RomaniaStates st = (RomaniaStates) (s);

        if (st.getName().equals("Oradea"))
            h = 380;

        if (st.getName().equals("Zerind"))
            h = 374;

        if (st.getName().equals("Arad"))
            h = 366;

        if (st.getName().equals("Timisoara"))
            h = 329;

        if (st.getName().equals("Lugoj"))
            h = 244;

        if (st.getName().equals("Mehadia"))
            h = 241;

        if (st.getName().equals("Doberta"))
            h = 242;


        if (st.getName().equals("Sibiu"))
            h = 253;

        if (st.getName().equals("Rimnicuvilcea"))
            h = 193;

        if (st.getName().equals("Craiova"))
            h = 160;


        if (st.getName().equals("Fagaras"))
            h = 178;

        if (st.getName().equals("Pitesti"))
            h = 98;


        if (st.getName().equals("Bucharest"))
            h = 0;

        if (st.getName().equals("Giurgiu"))
            h = 77;


        if (st.getName().equals("Urziceni"))
            h = 80;

        if (st.getName().equals("Neamt"))
            h = 234;


        if (st.getName().equals("Lasi"))
            h = 226;

        if (st.getName().equals("Vaslui"))

            h = 199;
        if (st.getName().equals("Hirsova"))
            h = 151;

        if (st.getName().equals("Eforie"))
            h = 161;

        return h;
    }

}
