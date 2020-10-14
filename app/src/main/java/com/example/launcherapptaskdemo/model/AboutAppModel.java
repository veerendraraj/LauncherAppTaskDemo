package com.example.launcherapptaskdemo.model;

import java.util.List;
//App model for app details
public class AboutAppModel {

    private String title;
    private List<RowItem> rows;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<RowItem> getRows() {
        return rows;
    }

    public void setRows(List<RowItem> rows) {
        this.rows = rows;
    }
}
