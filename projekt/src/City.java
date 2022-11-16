public class City {
    private String name;
    private int x;
    private int y;
    private int number;
    public City(String name, int x, int y, int number) {
        this.name = name;
        this.x = x;
        this.y = y;
        this.number = number;
    }

    public int getNumber() {
        return number;
    }

    public String getName() {
        return name;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
