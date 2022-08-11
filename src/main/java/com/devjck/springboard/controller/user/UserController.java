package com.devjck.springboard.controller.user;

import com.devjck.springboard.domain.user.enumType.Authority;
import com.devjck.springboard.dto.user.UserSaveRequestDto;
import com.devjck.springboard.dto.user.UserUpdateRequestDto;
import com.devjck.springboard.service.user.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;


@RequiredArgsConstructor
@RestController
@Slf4j
public class UserController {
    private final UserService userService;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @PostMapping("/api/user/join")
    public Long save(@RequestBody UserSaveRequestDto userSaveRequestDto) {
        String rawPassword = userSaveRequestDto.getPassword();

        userSaveRequestDto.setPasswordEncode(bCryptPasswordEncoder.encode(rawPassword));
        userSaveRequestDto.setAuthority(Authority.USER);
        log.info(userSaveRequestDto.toString());
        return userService.save(userSaveRequestDto);
    }

    @GetMapping("/api/mypage")
    public String mypage() {
        return "User";
    }

    @GetMapping("/api/admin")
    public String admin() {
        return "Admin";
    }

/*
    @PostMapping("/api/user/login")
    public ResponseEntity<?> login(@RequestBody User )
*/
    @PutMapping("/api/user/{userId}")
    public Long update(@PathVariable Long userId, @RequestBody UserUpdateRequestDto userUpdateRequestDto) {
        return userService.update(userId, userUpdateRequestDto);
    }

    @GetMapping("/api/user/existsByMailAddress")
    public ResponseEntity<?> existsByMailAddress(@RequestParam("mailAddress") String mailAddress) {
        log.info("request Param : " + mailAddress);
        log.info("exist Mail Address? : " + userService.existsByMailAddress(mailAddress));
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
