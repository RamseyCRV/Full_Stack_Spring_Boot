package com.libra.Repository;

import com.libra.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    /**
     * Find User by Username
     * @param username
     * @return User
     */
    User findUserByUsername(String username);
}
