package com.rehman.medicine.HelperClasses;

public class Order {
    String Name;
    String Email;
    String PhoneNo;
   String job;
    String Address;
    String Requirements;
    String Quantity;
    String Rupee;
    String Order_Key;

    public Order(String name, String email, String phoneNo, String job, String address, String requirements, String quantity, String rupee, String order_Key) {
        Name = name;
        Email = email;
        PhoneNo = phoneNo;
        this.job = job;
        Address = address;
        Requirements = requirements;
        Quantity = quantity;
        Rupee = rupee;
        Order_Key = order_Key;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getPhoneNo() {
        return PhoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        PhoneNo = phoneNo;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getRequirements() {
        return Requirements;
    }

    public void setRequirements(String requirements) {
        Requirements = requirements;
    }

    public String getQuantity() {
        return Quantity;
    }

    public void setQuantity(String quantity) {
        Quantity = quantity;
    }

    public String getRupee() {
        return Rupee;
    }

    public void setRupee(String rupee) {
        Rupee = rupee;
    }

    public String getOrder_Key() {
        return Order_Key;
    }

    public void setOrder_Key(String order_Key) {
        Order_Key = order_Key;
    }

    public Order() {
    }
}
