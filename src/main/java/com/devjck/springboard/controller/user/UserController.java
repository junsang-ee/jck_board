package com.devjck.springboard.controller.user;

import com.devjck.springboard.dto.user.UserSaveRequestDto;
import com.devjck.springboard.dto.user.UserUpdateRequestDto;
import com.devjck.springboard.service.user.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@Slf4j
public class UserController {
    private final UserService userService;

    @PostMapping("/api/user")
    public Long save(@RequestBody UserSaveRequestDto userSaveRequestDto) {
        return userService.save(userSaveRequestDto);
    }

    @PutMapping("/api/user/{userId}")
    public Long update(@PathVariable Long userId, @RequestBody UserUpdateRequestDto userUpdateRequestDto) {
        return userService.update(userId, userUpdateRequestDto);
    }

    @GetMapping("/api/user/existsByMailAddress")
    public ResponseEntity<?> existsByMailAddress(@RequestParam("mailAddress") String mailAddress) {
        log.info("request Param : " + mailAddress);
        return new ResponseEntity<>(userService.existsByMailAddress(mailAddress), HttpStatus.OK);
    }

    @GetMapping("/api/user/existsByNickName")
    public ResponseEntity<?> existsByNickName(@RequestParam("nickName") String nickName) {
        return new ResponseEntity<>(userService.existsByNickName(nickName), HttpStatus.OK);
    }

    @GetMapping("/api/user/searchByNickName")
    public ResponseEntity<?> searchByNickName(@RequestParam("nickName") String nickName) {
        return new ResponseEntity<>(userService.searchByNickName(nickName), HttpStatus.OK);
    }

}
