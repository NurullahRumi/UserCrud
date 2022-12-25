package com.example.usercrud.controller;

import com.example.usercrud.entity.User;
import com.example.usercrud.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("")
    public String home(){
        return "index";
    }

    @GetMapping("/goto-adduser")
    public String gotoAddUser(){
        System.out.println("Is it working. Testing the git");
        return "add_user";
    }

    @PostMapping("/adduser")
    public User addUser(@RequestBody User user){
        return userService.saveUser(user);
    }

    @PostMapping("/addUsers")
    public List<User> addUsers(@RequestBody List<User> users){
        return userService.saveUsers(users);
    }

    @GetMapping("/getUsers")
    public List<User> getUsers(){
        return userService.fetchUsers();
    }

    @GetMapping("/getUserById/{id}")
    public User getUserById(@PathVariable Long id){
        return userService.fetchUserById(id);
    }

    @GetMapping("/getUserByName/{name}")
    public User getUserByName(@PathVariable String name){
        return userService.fetchUserByName(name);
    }

    @DeleteMapping("/removeUserById/{id}")
    public String removeUserById(@PathVariable Long id){
        userService.deleteUserById(id);
        return "User deleted " + id;
    }

    @PutMapping("/update")
    public User editUser(@RequestBody User user){
        return userService.updateUser(user);
    }
}
