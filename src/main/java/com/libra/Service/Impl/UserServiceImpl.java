package com.libra.Service.Impl;

import com.libra.Dao.CrudDao;
import com.libra.Dao.UserDao;
import com.libra.Models.User;
import com.libra.Service.CrudService;
import com.libra.Service.NotesService;
import com.libra.Service.TodoService;
import com.libra.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements CrudService<User>, UserService {

    @Autowired
    private CrudDao<User> userCrudDao;
    @Autowired
    private UserDao userDao;
    @Autowired
    private NotesService notesService;
    @Autowired
    private TodoService todoService;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public List<User> getObjects() {
        return userCrudDao.getObjects();
    }

    @Override
    public Optional<User> findObjectById(int id) {
        return userCrudDao.findObjectById(id);
    }

    @Override
    public void deleteObject(int id) {
        userCrudDao.deleteObjectById(id);
    }

    @Override
    public void saveObject(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userCrudDao.saveObject(user);
    }

    @Override
    public User returnCurrentSignInUser(String username) {
        return userDao.findUserByUsername(username);
    }

    @Override
    public void updateUserProfile(User user) {
        userCrudDao.saveObject(user);
    }

    @Override
    public boolean changePassword(String username, String oldPassword, String newPassword) {
        User user = userDao.findUserByUsername(username);

        if (bCryptPasswordEncoder.matches(oldPassword, user.getPassword())) {
            user.setPassword(newPassword);
            saveObject(user);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void deleteAccount(String username) {
        User user = userDao.findUserByUsername(username);
        notesService.deleteAllNotesByActiveUser(username);
        todoService.deleteAllTodosByActiveUser(username);
        deleteObject(user.getId());
    }

    @Override
    public boolean passwordIsCorrect(String username, String password) {
        User user = userDao.findUserByUsername(username);

        if (bCryptPasswordEncoder.matches(password, user.getPassword())) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean checkIfUsernameExist(String username) {
        if(userDao.findUserByUsername(username) != null){
            return true;
        }else{
            return false;
        }
    }

}
