package com.rehman.medicine.Model;

public class ProductsModel {


    private String image;
    private String description;
    private String pname;

    private String Shop_Id;
    private String price;
    private String date;

    private String shopeName;
    private String address;

    public ProductsModel() {
    }

    public ProductsModel(String image, String description, String pname, String shop_Id, String price, String date, String shopeName, String address) {
        this.image = image;
        this.description = description;
        this.pname = pname;
        Shop_Id = shop_Id;
        this.price = price;
        this.date = date;
        this.shopeName = shopeName;
        this.address = address;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public String getShop_Id() {
        return Shop_Id;
    }

    public void setShop_Id(String shop_Id) {
        Shop_Id = shop_Id;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getShopeName() {
        return shopeName;
    }

    public void setShopeName(String shopeName) {
        this.shopeName = shopeName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}