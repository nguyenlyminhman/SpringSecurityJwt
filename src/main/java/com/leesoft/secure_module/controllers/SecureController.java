package com.leesoft.secure_module.controllers;

import com.leesoft.admin.shared.ResponseObject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/apii/")
public class SecureController {
    @GetMapping("yeb")
    public ResponseObject abc (){
        return new ResponseObject();
    }
}
