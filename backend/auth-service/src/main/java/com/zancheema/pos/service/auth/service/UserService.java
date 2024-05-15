package com.zancheema.pos.service.auth.service;

import com.zancheema.pos.service.auth.dto.SignUpPayload;
import com.zancheema.pos.service.auth.dto.UserInfo;
import org.springframework.security.core.Authentication;

import java.util.Optional;

public interface UserService {
    Optional<UserInfo> signUp(SignUpPayload payload);

    Optional<UserInfo> getInfo(Authentication authentication);
}
