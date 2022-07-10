package com.devjck.springboard.service.user;

import com.devjck.springboard.domain.user.User;
import com.devjck.springboard.domain.user.UserRepository;
import com.devjck.springboard.dto.user.UserSaveRequestDto;
import com.devjck.springboard.dto.user.UserUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;

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

    public boolean validateDuplipcateEmail(String mailAddress) {
        User user = userRepository.findByMailAddress(mailAddress);
        return (user == null);

    }

}
