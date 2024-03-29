package com.devjck.springboard.controller.user;

import com.devjck.springboard.config.auth.PrincipalDetails;
import com.devjck.springboard.domain.user.enumType.Authority;
import com.devjck.springboard.dto.response.common.DefaultResponseDto;
import com.devjck.springboard.dto.response.user.UserResponseDto;
import com.devjck.springboard.dto.request.user.UserSaveRequestDto;
import com.devjck.springboard.dto.request.user.UserUpdateRequestDto;
import com.devjck.springboard.service.user.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
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
    public ResponseEntity<?> mypage(Authentication auth) {
        //UserResponseDto userDTO = new UserResponseDto(principal.getUser());
        PrincipalDetails principal = (PrincipalDetails) auth.getPrincipal();
        UserResponseDto userDTO = new UserResponseDto(principal.getUser());

        log.info(userDTO.toString());

        return new ResponseEntity<>(userDTO, HttpStatus.OK);
    }

    @GetMapping("/api/test")
    public ResponseEntity<?> test1(Authentication auth) {
        PrincipalDetails principal = (PrincipalDetails) auth.getPrincipal();
        UserResponseDto userResponseDto = new UserResponseDto(principal.getUser());

        return new ResponseEntity<>(userResponseDto.getMailAddress(), HttpStatus.OK);
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
    public ResponseEntity<DefaultResponseDto> update(@PathVariable Long userId, @RequestBody UserUpdateRequestDto userUpdateRequestDto) {
        log.info("userController update :: " + userId);
        return ResponseEntity.ok(userService.update(userId, userUpdateRequestDto));

    }

    @GetMapping("/api/user/existsByMailAddress")
    public ResponseEntity<?> existsByMailAddress(@RequestParam("mailAddress") String mailAddress) {
        log.info("exist Mail Address? : " + userService.existsByMailAddress(mailAddress));
        return new ResponseEntity<>(userService.existsByMailAddress(mailAddress), HttpStatus.OK);
    }

    @GetMapping("/api/user/existsByNickName")
    public ResponseEntity<?> existsByNickName(@RequestParam("nickName") String nickName) {
        log.info("existsByNickName :::");
        return new ResponseEntity<>(userService.existsByNickName(nickName), HttpStatus.OK);
    }

    @GetMapping("/api/user/searchByNickName")
    public ResponseEntity<?> searchByNickName(@RequestParam("nickName") String nickName) {
        return new ResponseEntity<>(userService.searchByNickName(nickName), HttpStatus.OK);
    }

}
