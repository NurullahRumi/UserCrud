package com.example.usercrud.controller;

import com.example.usercrud.entity.User;
import com.example.usercrud.service.UserService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLOutput;
import java.util.List;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String home(Model m) {
        List<User> usr = userService.fetchUsers();
        m.addAttribute("usr", usr);
        return "index";
    }

    @GetMapping("/goto-adduser")
    public String gotoAddUser() {
        System.out.println("Is it working. Testing the git");
        return "add_user";
    }

    @PostMapping("/adduser")
    public String addUser(@Valid @ModelAttribute User user, HttpSession session, BindingResult result) {
        if(result.hasErrors()){
            return "add_user";
        }
        userService.saveUser(user);
        session.setAttribute("msg", "User added successfully");
        return "redirect:/";
    }

//    @PostMapping("/addUsers")
//    public List<User> addUsers(@RequestBody List<User> users){
//        return userService.saveUsers(users);
//    }

//    @GetMapping("/getUsers")
//    public List<User> getUsers(){
//        return userService.fetchUsers();
//    }

    @GetMapping("/getUserById/{id}")
    public String getUserById(@PathVariable Long id, Model m) {
        User user = userService.fetchUserById(id);
        m.addAttribute("user", user);
        return "edit_user";
    }

//    @GetMapping("/getUserByName/{name}")
//    public User getUserByName(@PathVariable String name){
//        return userService.fetchUserByName(name);
//    }

    @GetMapping("/removeUserById/{id}")
    public String removeUserById(@PathVariable Long id) {
        userService.deleteUserById(id);
        return "redirect:/";
    }

//    @PutMapping("/update")
//    public String editUser(@ModelAttribute User user){
//        System.out.println(user);
//        userService.updateUser(user);
//        return "redirect:/";
//    }

    @PostMapping("/updater")
    public String updateUser(@Valid @ModelAttribute User user, HttpSession session, BindingResult result) {
        if (result.hasErrors()) {
            return "edit_user";
        }
        userService.saveUser(user);
        session.setAttribute("msg", "User updated successfully.");
        return "redirect:/";
    }
}
