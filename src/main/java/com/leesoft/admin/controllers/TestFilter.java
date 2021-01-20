package com.leesoft.admin.controllers;

import com.leesoft.admin.security.JwtAccount;
import com.leesoft.admin.shared.ResponseObject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("api/")
public class TestFilter {
    @PostMapping("test")
    public ResponseObject authenticateUser(@RequestBody Map requestObject) {
            ResponseObject responseObject = new ResponseObject();
            responseObject.setData("This is a filter test...");
        return responseObject;
    }
}