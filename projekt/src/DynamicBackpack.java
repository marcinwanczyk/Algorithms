import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DynamicBackpack {
    private List<Items> finalList = new ArrayList<>();
    public List<Items> listOfItems = new ArrayList<Items>(Arrays.asList(
            new Items("Jablko", 1, 2),
            new Items("Diament", 2, 6),
            new Items("Zloto", 2, 8),
            new Items("Laptop", 2, 5),
            new Items("Hantelka", 4, 8),
            new Items("Zegarek", 6, 18),
            new Items("Serwer", 5, 10)));

    private int capacity = 20;
    private int[][] pij = new int[listOfItems.toArray().length][capacity];
    private int[][] qij = new int[listOfItems.toArray().length][capacity];
    private int[][] load = new int[listOfItems.toArray().length][capacity];
    public void dynamic(){
        for(int j = 0; j < capacity; j++) {
            if (listOfItems.get(0).getWeight() <= j + 1) {
                pij[0][j] = listOfItems.get(0).getValue();
                load[0][j] = listOfItems.get(0).getWeight();
                qij[0][j] = 1;
            }
            else{
                pij[0][j] = 0;
                qij[0][j] = 0;
            }
        }
        for (int i = 1; i < listOfItems.toArray().length; i++){
            for(int j = 0; j < capacity; j++){
                pij[i][j] = pij[i-1][j];
                load[i][j] = load[i-1][j];
                qij[i][j] = qij[i-1][j];
                if(listOfItems.get(i).getWeight() <= j+1){
                    if(load[i - 1][j] + listOfItems.get(i).getWeight() <= j + 1){
                        pij[i][j] += listOfItems.get(i).getValue();
                        load[i][j] += listOfItems.get(i).getWeight();
                        qij[i][j] = i+1;
                    }
                    if((pij[i - 1][j] < listOfItems.get(i).getValue())) {
                        pij[i][j] = listOfItems.get(i).getValue();
                        load[i][j] = listOfItems.get(i).getWeight();
                        qij[i][j] = i+1;
                    }
                }
            }
        }
    }
    public void whichItems(){
        int actualCap = this.capacity;
        while(actualCap > 0){
            finalList.add(listOfItems.get(qij[listOfItems.toArray().length-1][actualCap - 1] - 1));
            actualCap -= listOfItems.get(qij[listOfItems.toArray().length-1][actualCap - 1] - 1).getWeight();
        }
    }

    public List<Items> getFinalList() {
        return finalList;
    }

    public List<Items> getListOfItems() {
        return listOfItems;
    }

    public int getCapacity() {
        return capacity;
    }

    public int[][] getPij() {
        return pij;
    }

    public int[][] getQij() {
        return qij;
    }

    public static void main(String[] args) {
        DynamicBackpack back = new DynamicBackpack();
        back.dynamic();
        back.whichItems();
        for(int i = 0; i < back.listOfItems.toArray().length; i++) {
            for (int j = 0; j < back.capacity; j++) {
                System.out.print(back.pij[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
        for(int i = 0; i < back.listOfItems.toArray().length; i++) {
            for (int j = 0; j < back.capacity; j++) {
                System.out.print(back.qij[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
        for(int i = 0; i < back.finalList.toArray().length; i++)
            System.out.print(back.finalList.get(i).getName() + " ");
    }

}
