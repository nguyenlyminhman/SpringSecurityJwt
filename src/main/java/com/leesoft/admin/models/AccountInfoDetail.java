package com.leesoft.admin.models;

public class AccountInfoDetail {

    private int id;
    private String accountid;
    private String email;
    private String fullname;
    private String address;
    private String phone;

    public AccountInfoDetail() {
    }

    public AccountInfoDetail(AccountInfo accountInfo) {
        this.accountid = accountInfo.getAccountid();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAccountid() {
        return accountid;
    }

    public void setAccountid(String accountid) {
        this.accountid = accountid;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
