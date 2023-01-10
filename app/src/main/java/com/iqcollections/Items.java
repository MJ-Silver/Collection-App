package com.iqcollections;

public class Items {
    private String itemId;
    private String itemName;
    private String itemDescription;
    private String itemImage;
    private String itemDate;
    private String itemCollection;


    public Items(String itemId, String itemName, String itemDescription, String itemImage, String itemCollection, String itemDate) {
        this.itemId = itemId;
        this.itemName = itemName;
        this.itemDescription = itemDescription;
        this.itemImage = itemImage;
        this.itemCollection = itemCollection;
        this.itemDate = itemDate;

    }

    public String getItemId() { return itemId; }

    public void setItemId(String itemId) { this.itemId = itemId; }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemDescription() {
        return itemDescription;
    }

    public void setItemDescription(String itemDescription) {
        this.itemDescription = itemDescription;
    }

    public String getItemImage() {
        return itemImage;
    }

    public void setItemImage(String itemImage) {
        this.itemImage = itemImage;
    }

    public String getItemCollection() {
        return itemCollection;
    }

    public void setItemCollection(String itemCollection) {
        this.itemCollection = itemCollection;
    }

    public String getItemDate() {
        return itemDate;
    }

    public void setItemDate(String itemDate) {
        this.itemDate = itemDate;
    }
}
