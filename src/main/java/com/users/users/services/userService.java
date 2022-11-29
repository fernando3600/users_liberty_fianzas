package com.users.users.services;

import java.util.ArrayList;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.users.users.models.userModel;
import com.users.users.repositories.userRepository;

@Service
@Qualifier("userService")
public class userService {

    @Autowired
    @Qualifier("CrudRepository")
    userRepository _userRepository;

    public ArrayList<userModel> getUsers() {
        return (ArrayList<userModel>) _userRepository.findAll();
    }

    public Optional<userModel> getUser(Long id) {
        return _userRepository.findById(id);
    }

    public userModel saveUser (userModel user) {
        return _userRepository.save(user);
    }

    public userModel updateUser (userModel user) {
        return _userRepository.save(user);
    }

    public Boolean deleteUser (Long id) {
        try {
            _userRepository.deleteById(id);  
            return true; 
        } catch (Exception e) {
            return false;
        }
    }
    
}
