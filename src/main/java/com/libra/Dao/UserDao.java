package com.libra.Dao;

import com.libra.Models.UserModel;

public interface UserDao {

    UserModel findUserByUsername(final String username);
}
