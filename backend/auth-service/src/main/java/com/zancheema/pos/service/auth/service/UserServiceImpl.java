package com.zancheema.pos.service.auth.service;

import com.zancheema.pos.service.auth.dto.SignUpPayload;
import com.zancheema.pos.service.auth.dto.UserInfo;
import com.zancheema.pos.service.auth.mapper.UserMapper;
import com.zancheema.pos.service.auth.model.User;
import com.zancheema.pos.service.auth.repository.UserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.userMapper = userMapper;
    }

    @Override
    public Optional<UserInfo> signUp(SignUpPayload payload) {
        if (userRepository.existsById(payload.getUsername()) || userRepository.existsByEmail(payload.getEmail())) {
            return Optional.empty();
        }

        String encodedPassword = passwordEncoder.encode(payload.getPassword());
        User user = new User();
        user.setUsername(payload.getUsername());
        user.setEmail(payload.getEmail());
        user.setPassword(encodedPassword);
        User savedUser = userRepository.save(user);
        UserInfo userInfo = userMapper.getUserInfo(savedUser);
        return Optional.of(userInfo);
    }

    @Override
    public Optional<UserInfo> getInfo(Authentication authentication) {
        String username = authentication.getName();
        return userRepository.findById(username)
                .map(userMapper::getUserInfo);
    }
}
