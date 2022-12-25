package com.example.usercrud.service;

import com.example.usercrud.entity.User;
import com.example.usercrud.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepo;

    public User saveUser(User user){
        return userRepo.save(user);
    }

    public List<User> saveUsers(List<User> users){
        return userRepo.saveAll(users);
    }

    public List<User> fetchUsers(){
        return userRepo.findAll();
    }

    public User fetchUserById(long id){
        return userRepo.findById(id).orElse(null);
    }

    public User fetchUserByName(String name){
        return userRepo.findByName(name);
    }

    public String deleteUserById(Long id){
        userRepo.deleteById(id);
        return "User Deleted " + id;
    }

    public User updateUser(User user){
        User thisUser = userRepo.findById(user.getId()).orElse(null);
        thisUser.setName(user.getName());
        thisUser.setAddress(user.getAddress());
        thisUser.setHome_district(user.getHome_district());
        thisUser.setPostcode(user.getPostcode());
        thisUser.setEmail(user.getEmail());
        thisUser.setPhNo(user.getPhNo());
        thisUser.setPassword(user.getPassword());
        thisUser.setWork(user.getWork());
        return userRepo.save(thisUser);
    }
}
