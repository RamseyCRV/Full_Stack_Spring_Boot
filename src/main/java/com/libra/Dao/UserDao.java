package com.libra.Dao;

import com.libra.Models.User;

public interface UserDao {

    User findUserByUsername(final String username);
}
