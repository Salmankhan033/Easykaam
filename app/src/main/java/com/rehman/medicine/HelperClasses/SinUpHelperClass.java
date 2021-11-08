package com.rehman.medicine.HelperClasses;

public class SinUpHelperClass {

    String fullName, email, phoneNo, address, userId;

    public SinUpHelperClass(Object value) {
    }

    public SinUpHelperClass() {
    }

    public SinUpHelperClass(String fullName, String email, String phoneNo, String address, String userId) {
        this.fullName = fullName;
        this.email = email;
        this.phoneNo = phoneNo;
        this.address = address;
        this.userId = userId;

    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }


}
