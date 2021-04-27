package com.libra.Service.Impl;

import com.libra.Models.User;
import com.libra.Repository.UserRepository;
import com.libra.Service.CRUDService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements CRUDService<User> {

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
}
