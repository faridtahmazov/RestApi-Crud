package com.company.controller;

import com.company.model.User;
import com.company.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class APIController {

    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String getPage(){
        return "Welcome";
    }

    @GetMapping("users")
    public List<User> getUsers(){
        return userService.findAll();
    }

    @PostMapping("/save")
    public String saveUser(@RequestBody User user){
        userService.save(user);
        return user+"\nsaved..";
    }

    @PutMapping("/user/{id}")
    public String updateUser(@PathVariable Integer id, @RequestBody User user){
        User updatedUser = userService.findById(id).get();
        System.out.println(updatedUser);
        updatedUser.setName(user.getName());
        updatedUser.setSurname(user.getSurname());
        updatedUser.setUsername(user.getUsername());
        updatedUser.setAge(user.getAge());

        userService.save(updatedUser);

        return "updated..";
    }

    @DeleteMapping("/user/{id}")
    public String deleteUser(@PathVariable Integer id){
        User deletedUser = userService.findById(id).get();
        userService.delete(deletedUser);
        
        return "deleted user with username: " + deletedUser.getUsername();
    }


}
