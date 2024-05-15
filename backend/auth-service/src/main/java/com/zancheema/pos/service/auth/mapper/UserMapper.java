package com.zancheema.pos.service.auth.mapper;

import com.zancheema.pos.service.auth.dto.UserInfo;
import com.zancheema.pos.service.auth.model.User;

public interface UserMapper {
    UserInfo getUserInfo(User user);
}
