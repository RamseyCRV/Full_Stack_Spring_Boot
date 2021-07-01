package com.libra.Service;

import com.libra.Models.User;

public interface UserService {

    User returnCurrentSignInUser(final String username);

    void updateUserProfile(final User user);

    boolean changePassword(final String username, final String oldPassword, final String newPassword);

    void deleteAccount(final String username);

    boolean passwordIsCorrect(final String username, final String password);

    boolean checkIfUsernameExist(final String username);
}
