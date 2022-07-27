package com.devjck.springboard.config.auth;

import com.devjck.springboard.domain.user.User;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

// UserDetails를 상속받아 사용자 정보 설정하는 PrincipalDetails 클래스
@Data
public class PrincipalDetails implements UserDetails {
    private User user;

    public PrincipalDetails(User user) {
        this.user = user;
    }

    // 인가된 사용자 권한 리스트 생성
    // USER, ADMIN.....
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // 인가된 회원의 USER ROLE.VALUE를 담을 리스트
        Collection<GrantedAuthority> authorities = new ArrayList<>();

        // USER의 ROLE ENUM에서 값을 리스트에 추가
        authorities.add(new GrantedAuthority() {
            @Override
            public String getAuthority() {
                return user.getAuthority().getValue();
            }
        });
        return authorities;
    }

    // 로그인 시 USER의 패스워드
    @Override
    public String getPassword() {
        return user.getPassword();
    }

    // 로그인 시 USER의 사용자 계정 (이메일 로그인)
    @Override
    public String getUsername() {
        return user.getMailAddress();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
