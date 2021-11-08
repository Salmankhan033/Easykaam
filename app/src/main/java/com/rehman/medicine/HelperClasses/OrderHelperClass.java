package com.rehman.medicine.HelperClasses;

public class OrderHelperClass {
    String Customer_Name, Customer_No, Customer_Address, Product_Name,Customer_feedback,pack,orderId;

    public OrderHelperClass() {
    }

    public OrderHelperClass(String customer_Name, String customer_No, String customer_Address, String product_Name, String customer_feedback, String pack, String orderId) {
        Customer_Name = customer_Name;
        Customer_No = customer_No;
        Customer_Address = customer_Address;
        Product_Name = product_Name;
        Customer_feedback = customer_feedback;
        this.pack = pack;
        this.orderId = orderId;
    }

    public String getCustomer_Name() {
        return Customer_Name;
    }

    public void setCustomer_Name(String customer_Name) {
        Customer_Name = customer_Name;
    }

    public String getCustomer_No() {
        return Customer_No;
    }

    public void setCustomer_No(String customer_No) {
        Customer_No = customer_No;
    }

    public String getCustomer_Address() {
        return Customer_Address;
    }

    public void setCustomer_Address(String customer_Address) {
        Customer_Address = customer_Address;
    }

    public String getProduct_Name() {
        return Product_Name;
    }

    public void setProduct_Name(String product_Name) {
        Product_Name = product_Name;
    }

    public String getCustomer_feedback() {
        return Customer_feedback;
    }

    public void setCustomer_feedback(String customer_feedback) {
        Customer_feedback = customer_feedback;
    }

    public String getPack() {
        return pack;
    }

    public void setPack(String pack) {
        this.pack = pack;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }
}