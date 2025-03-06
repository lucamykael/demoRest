package com.demorest.controllers;

import com.demorest.dtos.UserDTO;
import com.demorest.entities.User;
import com.demorest.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.UUID;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    UserService service;

    @GetMapping("/index")
    public String index(){
        return "index";
    }

    @GetMapping
    public Collection<User> get(@RequestBody(required = false) User user) {

        return service.getUsers(user);
    }

    @PostMapping
    public User post(@RequestBody @Validated UserDTO dto) throws Exception{

        return service.create(dto);
    }

    @PutMapping("/{id}")
    public User put(@RequestBody @Validated User dto){

        return service.update(dto);
    }

    @DeleteMapping("/{id}")
    public User delete(@PathVariable @Validated UUID id){

        return service.delete(id);
    }
}