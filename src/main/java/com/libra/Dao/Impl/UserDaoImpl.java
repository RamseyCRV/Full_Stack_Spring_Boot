package com.libra.Dao.Impl;

import com.libra.Dao.CrudDao;
import com.libra.Dao.UserDao;
import com.libra.Models.User;
import com.libra.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserDaoImpl implements CrudDao<User>, UserDao {

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<User> getObjects() {
        return userRepository.findAll();
    }

    @Override
    public Optional<User> findObjectById(int id) {
        return userRepository.findById(id);
    }

    @Override
    public void deleteObjectById(int id) {
        userRepository.deleteById(id);
    }

    @Override
    public void saveObject(User object) {
        userRepository.save(object);
    }

    @Override
    public User findUserByUsername(String username) {
        return userRepository.findUserByUsername(username);
    }

}
