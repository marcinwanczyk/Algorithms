import javax.swing.*;
import java.awt.*;

public class Items {

    private int weight;
    private int value;
    private String name;

    public Items(String name, int weight, int value) {
        this.name = name;
        this.weight = weight;
        this.value = value;
    }

    public int getWeight() {
        return weight;
    }

    public int getValue() {
        return value;
    }

    public String getName() {
        return name;
    }
}
