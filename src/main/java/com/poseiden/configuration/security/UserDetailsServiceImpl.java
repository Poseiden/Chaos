package com.poseiden.configuration.security;

import com.poseiden.domain.UserAccount;
import com.poseiden.repo.UserAccountRepo;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private UserAccountRepo userAccountRepo;

    public UserDetailsServiceImpl(UserAccountRepo userAccountRepo) {
        this.userAccountRepo = userAccountRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        String errMsg = String.format("No user found with username '%s'.", username);
        UserAccount account = this.userAccountRepo.findById(username)
                .orElseThrow(() ->
                        new UsernameNotFoundException(errMsg));

        return new UserAuthorization(
                    account.getUsername(),
                    account.getPassword(),
                    account.getRole());
        }
}
