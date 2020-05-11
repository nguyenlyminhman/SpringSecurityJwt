package com.leesoft.admin.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;

public class LoginResponse {

    @JsonProperty("user_info")
    private AccountInfoDetail account;

    private String accessToken;

    @JsonProperty("expires_in")
    private int expireHours;

    public AccountInfoDetail getAccount() {
        return account;
    }

    public void setAccount(AccountInfoDetail account) {
        this.account = account;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public int getExpireHours() {
        return expireHours;
    }

    public void setExpireHours(int expireHours) {
        this.expireHours = expireHours;
    }
}
