package com.libra.Service.Impl;

import com.libra.Models.User;
import com.libra.Repository.UserRepository;
import com.libra.Service.CRUDService;
import com.libra.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements CRUDService<User>, UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public List<User> getObjects() {
        return userRepository.findAll();
    }

    @Override
    public Optional<User> findObjectById(int id) {
        return userRepository.findById(id);
    }

    @Override
    public void deleteObject(int id) {
        userRepository.deleteById(id);
    }

    @Override
    public void saveObject(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    @Override
    public List<User> findObjectsForActiveUser(String username) {
        return Collections.emptyList();
    }

    @Override
    public User returnCurrentSignInUser(String username) {
        return userRepository.findUserByUsername(username);
    }

    @Override
    public void updateUserProfile(User user) {
        userRepository.save(user);
    }

    @Override
    public boolean changePassword(String username, String oldPassword, String newPassword) {
        User user = userRepository.findUserByUsername(username);

        if(bCryptPasswordEncoder.matches(oldPassword, user.getPassword())){
            user.setPassword(newPassword);
            saveObject(user);
            return true;
        }else{
            return false;
        }
    }

}
