package com.patelheggere.rajeevconstituency.model;

public class VillageModel {
    private String name;
    private String sl_no;

    public VillageModel() {
    }

    public VillageModel(String name, String sl_no) {
        this.name = name;
        this.sl_no = sl_no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSl_no() {
        return sl_no;
    }

    public void setSl_no(String sl_no) {
        this.sl_no = sl_no;
    }
}
