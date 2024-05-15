package com.zancheema.pos.service.auth.controller;

import com.zancheema.pos.service.auth.dto.SignUpPayload;
import com.zancheema.pos.service.auth.dto.UserInfo;
import com.zancheema.pos.service.auth.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("api/v1/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("signup")
    public ResponseEntity<UserInfo> signUp(@RequestBody @Valid SignUpPayload payload) {
        Optional<UserInfo> result = userService.signUp(payload);
        if (result.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(result.get());
    }

    @GetMapping("/info")
    public ResponseEntity<UserInfo> getInfo(Authentication authentication) {
        return ResponseEntity.of(
                userService.getInfo(authentication)
        );
    }
}
