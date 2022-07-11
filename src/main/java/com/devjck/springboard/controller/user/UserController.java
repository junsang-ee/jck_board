package com.devjck.springboard.controller.user;

import com.devjck.springboard.dto.user.UserSaveRequestDto;
import com.devjck.springboard.dto.user.UserUpdateRequestDto;
import com.devjck.springboard.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class UserController {
    private final UserService userService;

    @PostMapping("/user/insert")
    public Long save(@RequestBody UserSaveRequestDto userSaveRequestDto) {
        return userService.save(userSaveRequestDto);
    }

    @PutMapping("/user/update/{userId}")
    public Long update(@PathVariable Long userId, @RequestBody UserUpdateRequestDto userUpdateRequestDto) {
        return userService.update(userId, userUpdateRequestDto);
    }

    @GetMapping("/user/find/")
    public ResponseEntity<Boolean> ValidateByMailAddress(@RequestParam("mailAddress") String mailAddress) {
        return new ResponseEntity<>(userService.validateDuplipcateEmail(mailAddress), HttpStatus.OK);

    }
}
