package com.bilgeadam.banket.controller;

import static com.bilgeadam.banket.constant.Endpoint.*;

import com.bilgeadam.banket.dto.request.AuthLoginRequestDto;
import com.bilgeadam.banket.dto.response.AuthLoginResponseDto;
import com.bilgeadam.banket.service.AuthService;
import com.bilgeadam.banket.utility.JwtTokenManager;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(ROOT + AUTH )
@RequiredArgsConstructor
@CrossOrigin
public class AuthController {
    private final AuthService authService;

    @PostMapping(LOGIN)
    public ResponseEntity<AuthLoginResponseDto> doLogin(@RequestBody AuthLoginRequestDto dto){
        return ResponseEntity.ok(authService.doLogin(dto));
    }
}
