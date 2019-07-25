package sg.al.asap.Fragments;

public class Product {
    private String name;
    private int cost;

    public Product(String names, int costs) {
        this.name = names;
        this.cost = costs;
    }

    public String getName() {
        return name;
    }

    public void setName(String names) {
        this.name = names;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int costs) {
        this.cost = costs;
    }
}
