package com.leesoft.admin.controllers;

import com.leesoft.admin.models.AccountInfo;
import com.leesoft.admin.models.LoginResponse;
import com.leesoft.admin.security.JwtAccount;
import com.leesoft.admin.security.JwtProvider;
import com.leesoft.admin.services.impl.AccountService;
import com.leesoft.admin.shared.ResponseObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/account/")
public class LoginController {

    @Value("30")
    private int clientInActiveMinutes;

    @Autowired
    private AccountService service;

    @Autowired
    private JwtProvider jwtProvider;

    @PostMapping("login/auth")
    public ResponseEntity<?> authenticateUser(@RequestBody JwtAccount requestObject) {
        ResponseObject responseObject = new ResponseObject();
        AccountInfo account = this.getValidAccount(requestObject.getAccountid(), requestObject.getPassword());
        if(account != null){
            JwtAccount jwtUser = JwtAccount.create(account);
            jwtUser.setAccountInfoDetail(service.getAccountInfo(account));

            String jwt = jwtProvider.generateToken(jwtUser);

            LoginResponse resData = new LoginResponse();
            resData.setAccount(jwtUser.getAccountInfoDetail());
            resData.setAccessToken(jwt);
            resData.setExpireHours(this.clientInActiveMinutes);

            responseObject.setData(resData);
        }
        return ResponseEntity.ok(responseObject);
    }

    private AccountInfo getValidAccount(String accountid, String password){
        AccountInfo account = service.loginUser(accountid);
        boolean isCorrectPassword  = jwtProvider.validatePassword(password, account.getPassword());
        if(account != null && isCorrectPassword)
            return account;
        return null;
    }
}
