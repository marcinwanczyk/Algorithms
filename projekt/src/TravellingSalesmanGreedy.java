import java.util.*;

public class TravellingSalesmanGreedy {

    public List<City> cities = new ArrayList<City>(Arrays.asList( new City("Waszyngton", 223, 257, 1),
            new City("Warsaw", 417, 224, 2), new City("Moskwa", 450, 209, 3),
            new City("Berlin", 402, 225, 4), new City("Kair", 441, 278, 5),
            new City("Tokio", 667, 256, 6), new City("Canberra", 687, 422, 7),
            new City("Pretoria", 437, 397, 8), new City("Antananarywa", 477, 380, 9),
            new City("Pekin", 614, 252, 10), new City("Nur-Sułtan", 517, 224, 11),
            new City("Nowe Delhi", 468, 288, 12), new City("Buenos Aires", 259, 424, 13),
            new City("Brasilia", 278, 377, 14), new City("Bogota", 223, 334, 15), new City("Ottawa", 232, 234, 16)));

    private List<City> finalList = new ArrayList<>();
    private List<City> list = cities;
    private int totalDist = 0;

    public int getTotalDist() {
        return totalDist;
    }

    public void setList(List<City> list) {
        this.list = list;
    }

    public List<City> getList() {
        return list;
    }

    public List<City> getFinalList() {
        return finalList;
    }

    public void greedyAlgorithm() {
        this.finalList.add(this.getList().get(0));
        this.getList().remove(0);
        for (int k = 0; k < this.getList().toArray().length; k++) {
            int index = 0;
            int miniDist = 9999999;
            for (int i = 0; i < this.getList().toArray().length; i++) {
                if (this.finalList.contains(this.getList().get(i))) {
                    continue;
                }
                int distance = (int) Math.sqrt(Math.pow(this.finalList.get(k).getX() - this.list.get(i).getX(), 2) +
                        Math.pow(this.finalList.get(k).getY() - this.list.get(i).getY(), 2));
                if (distance < miniDist) {
                    miniDist = distance;
                    index = i;
                }
            }
            this.finalList.add(this.getList().get(index));
            this.totalDist += miniDist;
        }
    }


    public static void main(String[] args) {
        TravellingSalesmanGreedy zachlanny = new TravellingSalesmanGreedy();
        zachlanny.greedyAlgorithm();
    }
}
