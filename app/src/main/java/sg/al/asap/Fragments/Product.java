package sg.al.asap.Fragments;

import android.media.Image;

public class Product {
    private String name;
    private int cost;
    private int filePath;

    public Product(String names, int costs, int filePath) {
        this.name = names;
        this.cost = costs;
        this.filePath = filePath;

    }
    public int getImageFilePath() { return filePath; }
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
