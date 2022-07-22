package com.devjck.springboard.service.user;

import com.devjck.springboard.domain.user.User;
import com.devjck.springboard.domain.user.UserRepository;
import com.devjck.springboard.dto.user.UserSaveRequestDto;
import com.devjck.springboard.dto.user.UserUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;

    @Transactional
    public void updateStatus(String status, Long userId) {
        userRepository.updateStatus(status, userId);
    }
    @Transactional
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Transactional
    public Long save(UserSaveRequestDto userSaveRequestDto) {
        return userRepository.save(userSaveRequestDto.toEntity()).getUserId();
    }

    @Transactional
    public Long update(Long userId, UserUpdateRequestDto updateDto) {
        User user = userRepository.findById(userId).orElseThrow(
                () -> new IllegalArgumentException("dont` exists user...")
        );
        user.update(updateDto.getNickName(), updateDto.getPassword(), updateDto.getName(),
                updateDto.getAddress(), updateDto.getNumber(), updateDto.getMailAddress());
        return userId;
    }

    @Transactional
    public boolean existsByNickName(String nickName) {
        return userRepository.existsByNickName(nickName);
    }

    @Transactional
    public boolean existsByMailAddress(String mailAddress) {
        return userRepository.existsByMailAddress(mailAddress);
    }

    @Transactional
    public List<User> searchByNickName(String nickName) {
        return userRepository.findByNickNameContains(nickName);
    }

    @Transactional
    public void updateLastedAccessTime(Long userId) {
        LocalDateTime currentTime = LocalDateTime.now();
        userRepository.updateLastedAccessTime(currentTime, userId);
    }
}
