package com.zancheema.pos.service.auth.mapper;

import com.zancheema.pos.service.auth.dto.UserInfo;
import com.zancheema.pos.service.auth.model.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapperImpl implements UserMapper {
    @Override
    public UserInfo getUserInfo(User user) {
        return new UserInfo(
                user.getAuthorities(),
                user.getUsername(),
                user.getEmail(),
                user.isAccountNonExpired(),
                user.isAccountNonLocked(),
                user.isCredentialsNonExpired(),
                user.isEnabled()
        );
    }
}
