package main.app.models;

public class ItemPageModel {
    private String itemID;
    private String mainGroup;
    private String subGroup;
    private int totalBought;
    private int totalReturned;


    public ItemPageModel(SalesModel.ItemRow row) {
        this.itemID = row.getItemID();
        this.mainGroup = row.getMainGroup();
        this.subGroup = row.getSubGroup();
        this.totalBought = row.getTotalBought();
        this.totalReturned = row.getTotalReturned();

    }

    public String getItemID() {
        return itemID;
    }

    public String getMainGroup() {
        return mainGroup;
    }

    public String getSubGroup() {
        return subGroup;
    }

    public int getTotalBought() {
        return totalBought;
    }

    public int getTotalReturned() {
        return totalReturned;
    }
}
