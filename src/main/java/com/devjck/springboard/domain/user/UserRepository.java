package com.devjck.springboard.domain.user;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByMailAddress(String mailAddress);
    boolean existsByNickName(String nickName);
    List<User> findByNickNameContains(String nickName);
}
