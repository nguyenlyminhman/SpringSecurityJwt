package com.leesoft.your_module.controllers;

import com.leesoft.admin.shared.ResponseObject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/")
public class PublicController {
    @GetMapping("my-module-path")
    public ResponseObject abc (){
        return new ResponseObject();
    }
}
