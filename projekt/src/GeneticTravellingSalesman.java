import java.util.*;

public class GeneticTravellingSalesman {
    public List<List> list = new ArrayList<>();
    private List<List> finalList = new ArrayList<>();
    public int numberInFirst = 32768;
    TravellingSalesManRandom random = new TravellingSalesManRandom("Waszyngton");
    public void firstPopulation() {
        for (int i = 0; i < numberInFirst ; i++) { //32768
            random = new TravellingSalesManRandom("Waszyngton");
            random.way();
            list.add(random.getFinalLIst());
        }
    }
    public void mutation(List<Object> onePath){
        Random r = new Random();
        float mutationRate = r.nextFloat();
        if(mutationRate <=0.001){
            int firstIndex = r.nextInt(16);
            int secondIndex = r.nextInt(16);
            Collections.swap(onePath, firstIndex, secondIndex);
        }
    }
    public void fittness(List<Object> listToCalculate) {
        int result = 0;
        for (int i = 0; i < listToCalculate.toArray().length - 1; i++) {
            if (listToCalculate.get(i) instanceof City) {
                int neighbourDistance = (int) Math.sqrt(Math.pow(((City) listToCalculate.get(i)).getX() - ((City) listToCalculate.get(i + 1)).getX(), 2)
                        + Math.pow(((City) listToCalculate.get(i)).getY() - ((City) listToCalculate.get(i + 1)).getY(), 2));
                result += neighbourDistance;
            }
        }
        listToCalculate.add(result);
    }

    public List<List> selection(List<List> population) {
        List<List> selected = new ArrayList<>();
        if(population.toArray().length > 2) {
            int length = population.toArray().length / 2;
            for (int i = 0; i < length; i++) {
                List<List> tournament = new ArrayList<>();
                Random r = new Random();
                int index = r.nextInt(population.toArray().length);
                tournament.add(population.get(index));
                population.remove(index);
                index = r.nextInt(population.toArray().length);
                tournament.add(population.get(index));
                population.remove(index);
                List winner;
                if ((int) tournament.get(0).get(16) <= (int) tournament.get(1).get(16))
                    winner = tournament.get(0);
                else
                    winner = tournament.get(1);
                selected.add(winner);
            }
        }
        else{
            if((int) population.get(0).get(16) <= (int) population.get(1).get(16))
                selected.add(population.get(0));
            else
                selected.add(population.get(1));
        }
        return selected;
    }
    public List<List> crossover(List<List> parents){
        Random r = new Random();
        int index = r.nextInt(15)+1;
        for (int j = 0; j <= index; j++) {
            City a = (City) parents.get(0).get(j);
            int first = a.getNumber();
            City b = (City) parents.get(1).get(j);
            int second = b.getNumber();
            for (int x = j; x < 16; x++) {
                if (((City) parents.get(0).get(x)).getNumber() == second) {
                    Collections.swap(parents.get(0), j, x);
                }
            }
        }
        return parents;
    }

    public List<List> cross(List<List> selectedPop) {
        List<List> children = new ArrayList<>();
        List<List> copy = new ArrayList<>(selectedPop);
//        selectedPop.clear();
        for (int i = 0; i < copy.toArray().length - 1; i += 2) {
            List<List> parents = new ArrayList<>();
            parents.add(0,new ArrayList(copy.get(i)));
            parents.add(1,new ArrayList(copy.get(i + 1)));
            parents = this.crossover(parents);
            this.mutation(parents.get(0));
            children.add(parents.get(0));
            children.get(i).remove(16);
            this.fittness(children.get(i));
            parents.clear();
            Random random = new Random();
            int indeks = random.nextInt(16);
            parents.add(0,new ArrayList(copy.get(i + 1)));
            parents.add(1,new ArrayList(copy.get(i)));
            parents = this.crossover(parents);
            this.mutation(parents.get(0));
            children.add(parents.get(0));
            children.get(i+1).remove(16);
            this.fittness(children.get(i+1));
        }

        return children;
    }

    public TravellingSalesManRandom getRandom() {
        return random;
    }

    public void setList(List<List> list) {
        this.list = list;
    }

    public List<List> getList() {
        return list;
    }

    public List<List> getFinalList() {
        return finalList;
    }

    public void setFinalList(List<List> finalList) {
        this.finalList = finalList;
    }

    public void geneticAlgorithm(){
        long start = System.nanoTime();
        this.firstPopulation();
        for (int i = 0; i < this.list.toArray().length; i++) {
            this.fittness(this.list.get(i));
        }
        this.getFinalList().add(this.list);
        while(this.getList().toArray().length > 1){
            this.setList(this.selection(this.getList()));
            this.setList(this.cross(this.getList()));
            for(int i = 0; i < list.toArray().length; i++)
                this.finalList.add(this.list.get(i));
        }
        long end = System.nanoTime();
        long time = end - start;
//        System.out.println(getFinalList());
        System.out.println(time);
    }

    public void zadanie1(){
//    tutaj nalezy wpisac algortytm
    }

    public static void main(String[] args) {
        GeneticTravellingSalesman genetyczny = new GeneticTravellingSalesman();
        genetyczny.geneticAlgorithm();
    }
}
