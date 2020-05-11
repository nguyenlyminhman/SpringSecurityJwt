package com.leesoft.admin.security;

import com.leesoft.admin.models.AccountInfo;
import com.leesoft.admin.models.AccountInfoDetail;

public class JwtAccount {

    private int id;
    private String accountid;
    private String password;
    private AccountInfoDetail accountInfoDetail;

    public JwtAccount() {
    }

    public JwtAccount(int id, String accountid) {
        this.id = id;
        this.accountid = accountid;
    }

    public JwtAccount(int id, String accountid, String password) {
        this.id = id;
        this.accountid = accountid;
        this.password = password;
    }

    public static JwtAccount create(AccountInfo acount) {
        return new JwtAccount(
                acount.getId(),
                acount.getAccountid()
        );
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public AccountInfoDetail getAccountInfoDetail() {
        return accountInfoDetail;
    }

    public void setAccountInfoDetail(AccountInfoDetail accountInfoDetail) {
        this.accountInfoDetail = accountInfoDetail;
    }
}
