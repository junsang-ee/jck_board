package com.devjck.springboard.config.auth;

import com.devjck.springboard.domain.user.User;
import com.devjck.springboard.domain.user.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

// http://localhost:8080/login
@Service
@RequiredArgsConstructor
@Slf4j
public class PrincipalDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    // @OneToMany(mappedBy = "writeUser", cascade = CascadeType.MERGE, orphanRemoval = true, fetch = FetchType.LAZY)
    // USER에서 BOARD, REPLY 항목의 LAZY FETCH 됨에 따라 Transactional 선언
    @Override
    @Transactional
    public UserDetails loadUserByUsername(String mailAddress) throws UsernameNotFoundException {
        // 이메일 정보를 받아 해당 USER로 principalDetails 설정
        log.info("PrincipalDetailsService : loadUserByUsername()");
        log.info("Mail Address : " + mailAddress);
        User user = userRepository.findByMailAddress(mailAddress);
        System.out.println("User : " + user);
        return new PrincipalDetails(user);
    }
}
