package com.leesoft.admin.services.impl;

import com.leesoft.admin.mappers.AccountMapper;
import com.leesoft.admin.models.AccountInfo;
import com.leesoft.admin.models.AccountInfoDetail;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountService {
    private static final Logger LOGGER = LoggerFactory.getLogger(AccountService.class);

    @Autowired
    private AccountMapper mapper;

    public AccountInfo loginUser(String accountId){
        AccountInfo account = new AccountInfo();
        try {
            account = mapper.loginAccount(accountId);
        }catch (Exception e){
            LOGGER.error("Fail when call loginAccount.", e);
        }
        return account;
    }

    public AccountInfoDetail getAccountInfo(AccountInfo accountInfo){
        AccountInfoDetail accountInfoDetail = null;
        try {
            String accountid = accountInfo.getAccountid();
            accountInfoDetail = mapper.getAccountInfo(accountid);
        }catch (Exception e){
            LOGGER.error("Fail when call getAccountInfo.", e);
        }
        if(accountInfoDetail == null ){
            LOGGER.warn(String.format("Fail when call getAccountInfo of User [%s}", accountInfo.getAccountid()));
            accountInfoDetail = new AccountInfoDetail(accountInfo);
        }
        return  accountInfoDetail;
    }
}
