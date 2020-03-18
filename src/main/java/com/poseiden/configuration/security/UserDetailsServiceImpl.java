package com.poseiden.configuration.security;

import com.google.common.collect.Lists;
import com.poseiden.domain.UserAccount;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String username) {
        UserAccount account = new UserAccount("admin", "admin");

        if (account == null) {
            throw new UsernameNotFoundException(String.format("No user found with username '%s'.", username));
        } else {
            return new UserAuthorization(
                    account.getUsername(),
                    account.getPassword(),
                    Lists.newArrayList());
        }
    }
}
