package com.libra.Service;

import com.libra.Models.User;
import org.springframework.stereotype.Service;

@Service
public interface UserService {

    User returnCurrentSignInUser(final String username);

    void updateUserProfile(final User user);

    boolean changePassword(final String username, final String oldPassword, final String newPassword);
}
