package com.libra.Security;

import com.libra.Models.UserModel;
import com.libra.Models.UserRolesModel;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;

public class UserPrincipal implements UserDetails {

    private UserModel userModel;

    public UserPrincipal(UserModel userModel){
        this.userModel = userModel;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<UserRolesModel> roles = userModel.getRoles();
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();

        for(UserRolesModel userRolesModel : roles){
            authorities.add(new SimpleGrantedAuthority(userRolesModel.getRoles()));
        }
        return authorities;
    }

    @Override
    public String getPassword() {
        return userModel.getPassword();
    }

    @Override
    public String getUsername() {
        return userModel.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
