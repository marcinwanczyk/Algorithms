import java.util.*;

public class TravellingSalesManRandom {
    public  List<City> cities = new ArrayList<City>(Arrays.asList(new City("Warsaw", 417, 224, 1), new City("Moskwa", 450, 209, 2),
            new City("Berlin", 402, 225, 3), new City("Kair", 441, 278, 4), new City("Waszyngton", 223, 257, 5),
            new City("Tokio", 667, 256, 6), new City("Canberra", 687, 422, 7),
            new City("Pretoria", 437, 397, 8), new City("Antananarywa", 477, 380, 9),
            new City("Pekin", 614, 252, 10), new City("Nur-Sułtan", 517, 224, 11),
            new City("Nowe Delhi", 468, 288, 12), new City("Buenos Aires", 259, 424, 13),
            new City("Brasilia", 278, 377, 14), new City("Bogota", 223, 334, 15), new City("Ottawa", 232, 234, 16)));

    private String firstCity;
    private List<City> list = cities;
    private List<City> finalLIst = new ArrayList<City>();
    public TravellingSalesManRandom(String firstCity) {
        this.firstCity = firstCity;
    }
    private int wayLenght = 0;
    public void  way(){
        int index;
        for(int i = 0 ; i < list.toArray().length; i++){
            if(list.get(i).getName().equals(firstCity)) {
                index = i;
                finalLIst.add(list.get(index));
                list.remove(list.get(index));
                break;
            }
        }
        while(!list.isEmpty()){
            int random = new Random().nextInt(list.toArray().length);
            finalLIst.add(list.get(random));
            list.remove(list.get(random));
        }

        for(int i = 0 ; i < finalLIst.toArray().length - 1; i++) {
//            System.out.println(finalLIst.get(i).getName());
            int neighbourDistance = (int) Math.sqrt(Math.pow(finalLIst.get(i).getX()- finalLIst.get(i+1).getX(), 2)
                    + Math.pow(finalLIst.get(i).getY()- finalLIst.get(i+1).getY(), 2));
            wayLenght += neighbourDistance;
        }
    }

    public int getWayLenght() {
        return wayLenght;
    }

    public List<City> getFinalLIst() {
        return finalLIst;
    }

    public List<City> getList() {
        return list;
    }

    public void setFirstCity(String firstCity) {
        this.firstCity = firstCity;
    }

    public void setList(List<City> list) {
        this.list = list;
    }

    public static void main(String[] args) {
        TravellingSalesManRandom random = new TravellingSalesManRandom("Waszyngton");
        random.way();
        System.out.println(random.getWayLenght());
    }
}
