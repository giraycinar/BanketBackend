package com.bilgeadam.banket.service;

import com.bilgeadam.banket.dto.request.AuthLoginRequestDto;
import com.bilgeadam.banket.dto.response.AuthLoginResponseDto;
import com.bilgeadam.banket.entity.User;
import com.bilgeadam.banket.entity.enums.ERole;
import com.bilgeadam.banket.exception.BanketApplicationException;
import com.bilgeadam.banket.exception.ErrorType;
import com.bilgeadam.banket.repository.UserRepository;
import com.bilgeadam.banket.utility.JwtTokenManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final JwtTokenManager jwtTokenManager;

    private final UserService userService;

    public AuthLoginResponseDto doLogin(AuthLoginRequestDto dto) {
        User user = userService.getUserByEmail(dto.getEmail());
        if (!dto.getPassword().equals(user.getPassword()))
            throw new BanketApplicationException(ErrorType.BAD_LOGIN_CREDENTIALS);
        String token = jwtTokenManager.createToken(user.getUuid(), user.getRoles())
                .orElseThrow(() -> new BanketApplicationException(ErrorType.TOKEN_CANNOT_CREATED));
        return AuthLoginResponseDto.builder()
                .token(token)
                .build();
    }

}
