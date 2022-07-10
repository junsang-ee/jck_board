package com.devjck.springboard.domain.user;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByMailAddress(String mailAddress);
}
