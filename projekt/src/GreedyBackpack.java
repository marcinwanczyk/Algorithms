import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GreedyBackpack {

    public List<Items> listOfItems = new ArrayList<Items>(Arrays.asList(

            new Items("Serwer", 5, 10),
            new Items("Diament", 2, 6),
            new Items("Zloto", 2, 8),
            new Items("Laptop", 2, 5),
            new Items("Hantelka", 4, 8),
            new Items("Zegarek", 6, 18),
            new Items("Jablko", 1, 2)));

    public List<Items> backpack = new ArrayList<>();
    public List<Items> items = new ArrayList<>(listOfItems);
    private float wage;
    private int bkpkMaxCapacity = 20;
    private int bkpkCapacity = 0;
    private int temp = 0;

    public List<Items> getBackpack() {
        return backpack;
    }

    public List<Items> getItems() {
        return items;
    }

    public void greedyBackpack() {
        for (int k = 0; k < this.getItems().toArray().length; k++) {
            float maxi = 0;
            int index = 0;
            for (int i = 0; i < this.getItems().toArray().length; i++) {
                wage = (float)this.getItems().get(i).getValue() / this.getItems().get(i).getWeight();
                if (wage > maxi) {
                    maxi = wage;
                    index = i;
                }
                else if (wage == maxi) {
                    if (this.getItems().get(index).getWeight() + bkpkCapacity >= bkpkMaxCapacity){
                        index = i;
                    }
                }
                if (i == this.getItems().toArray().length - 1) {
                    if (bkpkCapacity + this.getItems().get(index).getWeight() <= bkpkMaxCapacity) {
                        this.backpack.add(this.getItems().get(index));
                        bkpkCapacity += this.backpack.get(temp).getWeight();
                        temp++;
                        this.getItems().remove(index);
                        k--;
                    }
                }
            }
        }
        for (int m = 0; m < this.backpack.toArray().length; m++) {
            System.out.println(this.backpack.get(m).getName());
        }
    }

    public static void main(String[] args) {
        GreedyBackpack backpack = new GreedyBackpack();
        backpack.greedyBackpack();
    }
}
