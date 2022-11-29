package com.users.users.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.users.users.models.userModel;
import com.users.users.services.userServicePaginate;


@RestController
@RequestMapping("/users")
public class userControllerPaginate {
    
    @Autowired
    @Qualifier("userServicePaginate")
    userServicePaginate _userServicePaginate;

    //se lista a todos los usuarios de forma paginada
    @GetMapping()
    public Page<userModel> getUsersPaginate(@RequestParam(name = "page") int page) {
        return _userServicePaginate.getUsersPaginate(page);
    }
}
