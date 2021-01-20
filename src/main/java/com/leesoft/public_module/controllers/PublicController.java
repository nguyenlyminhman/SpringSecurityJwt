package com.leesoft.public_module.controllers;

import com.leesoft.admin.shared.ResponseObject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/")
public class PublicController {
    @GetMapping("yub")
    public ResponseObject abc (){
        return new ResponseObject();
    }
}
