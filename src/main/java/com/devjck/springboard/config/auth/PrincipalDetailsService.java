package com.devjck.springboard.config.auth;

import com.devjck.springboard.domain.user.User;
import com.devjck.springboard.domain.user.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

// http://localhost:8080/login
@Service
@RequiredArgsConstructor
@Slf4j
public class PrincipalDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String mailAddress) throws UsernameNotFoundException {
        log.info("PrincipalDetailsService : loadUserByUsername()");
        User user = userRepository.findByMailAddress(mailAddress);
        System.out.println("User : " + user);
        return new PrincipalDetails(user);
    }
}
