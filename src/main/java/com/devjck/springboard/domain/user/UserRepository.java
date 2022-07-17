package com.devjck.springboard.domain.user;

import com.devjck.springboard.domain.user.enumType.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByMailAddress(String mailAddress);
    boolean existsByMailAddress(String mailAddress);
    boolean existsByNickName(String nickName);
    List<User> findByNickNameContains(String nickName);

    @Modifying(clearAutomatically = true)
    @Query(value = "UPDATE user SET status = :status WHERE user_id = :userId", nativeQuery = true)
    void updateStatus(@Param("status") String status, @Param("userId") Long userId);

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "UPDATE user SET lasted_access_time = :lastedAccessTime WHERE user_id = :userId", nativeQuery = true)
    void updateLastedAccessTime(@Param("lastedAccessTime") LocalDateTime lastedAccessTime, @Param("userId") Long userId);
}
