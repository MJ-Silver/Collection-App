package com.iqcollections;

public class Collections {
    String colId;
    String colName;
    String colDescription;
    String colImgUrl;
    String colGoal;



    public Collections(String colId, String colName, String colDescription, String colImgUrl,String colGoal) {
        this.colId = colId;
        this.colName = colName;
        this.colDescription = colDescription;
        this.colImgUrl = colImgUrl;
        this.colGoal = colGoal;

    }

    public String getColId() { return colId; }

    public void setColId(String colId) { this.colId = colId; }

    public String getColName() {
        return colName;
    }

    public void setColName(String colName) {
        this.colName = colName;
    }

    public String getColDescription() {
        return colDescription;
    }

    public void setColDescription(String colDescription) {
        this.colDescription = colDescription;
    }

    public String getColImgUrl() {
        return colImgUrl;
    }

    public void setColImgUrl(String colImgUrl) {
        this.colImgUrl = colImgUrl;
    }

    public String getColGoal() {
        return colGoal;
    }

    public void setColGoal(String colGoal) {
        this.colGoal = colGoal;
    }
}
