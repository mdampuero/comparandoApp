package com.ar.comparando.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Product {
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("brand")
    @Expose
    private String brand;
    @SerializedName("category")
    @Expose
    private String category;
    @SerializedName("barcode")
    @Expose
    private String barcode;
    @SerializedName("price")
    @Expose
    private Object price;
    @SerializedName("priceMin")
    @Expose
    private Double priceMin;
    @SerializedName("priceMax")
    @Expose
    private Double priceMax;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("display")
    @Expose
    private String display;
    @SerializedName("numberBranchesAvailable")
    @Expose
    private Integer numberBranchesAvailable;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public Object getPrice() {
        return price;
    }

    public void setPrice(Object price) {
        this.price = price;
    }

    public Double getPriceMin() {
        return priceMin;
    }

    public void setPriceMin(Double priceMin) {
        this.priceMin = priceMin;
    }

    public Double getPriceMax() {
        return priceMax;
    }

    public void setPriceMax(Double priceMax) {
        this.priceMax = priceMax;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDisplay() {
        return display;
    }

    public void setDisplay(String display) {
        this.display = display;
    }

    public Integer getNumberBranchesAvailable() {
        return numberBranchesAvailable;
    }

    public void setNumberBranchesAvailable(Integer numberBranchesAvailable) {
        this.numberBranchesAvailable = numberBranchesAvailable;
    }
}
