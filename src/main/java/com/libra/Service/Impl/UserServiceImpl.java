package com.libra.Service.Impl;

import com.libra.Dao.CrudDao;
import com.libra.Dao.UserDao;
import com.libra.Models.UserModel;
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
public class UserServiceImpl implements CrudService<UserModel>, UserService {

    @Autowired
    private CrudDao<UserModel> userCrudDao;
    @Autowired
    private UserDao userDao;
    @Autowired
    private NotesService notesService;
    @Autowired
    private TodoService todoService;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public List<UserModel> getObjects() {
        return userCrudDao.getObjects();
    }

    @Override
    public Optional<UserModel> findObjectById(int id) {
        return userCrudDao.findObjectById(id);
    }

    @Override
    public void deleteObject(int id) {
        userCrudDao.deleteObjectById(id);
    }

    @Override
    public void saveObject(UserModel userModel) {
        userModel.setPassword(bCryptPasswordEncoder.encode(userModel.getPassword()));
        userCrudDao.saveObject(userModel);
    }

    @Override
    public UserModel returnCurrentSignInUser(String username) {
        return userDao.findUserByUsername(username);
    }

    @Override
    public void updateUserProfile(UserModel userModel) {
        userCrudDao.saveObject(userModel);
    }

    @Override
    public boolean changePassword(String username, String oldPassword, String newPassword) {
        UserModel userModel = userDao.findUserByUsername(username);

        if (bCryptPasswordEncoder.matches(oldPassword, userModel.getPassword())) {
            userModel.setPassword(newPassword);
            saveObject(userModel);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void deleteAccount(String username) {
        UserModel userModel = userDao.findUserByUsername(username);
        notesService.deleteAllNotesByActiveUser(username);
        todoService.deleteAllTodosByActiveUser(username);
        deleteObject(userModel.getId());
    }

    @Override
    public boolean passwordIsCorrect(String username, String password) {
        UserModel userModel = userDao.findUserByUsername(username);

        if (bCryptPasswordEncoder.matches(password, userModel.getPassword())) {
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
