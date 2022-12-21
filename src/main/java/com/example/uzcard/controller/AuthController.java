package com.example.uzcard.controller;
import com.example.uzcard.dto.LoginDto;
import com.example.uzcard.payload.ResponseApi;
import com.example.uzcard.service.interfaces.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;
    @PostMapping("/login")
    public ResponseEntity<?> login(
            @RequestBody LoginDto loginDto
    ) {
        ResponseApi login = authService.login(loginDto);
        if (login.isSuccess()) return ResponseEntity.ok(login);
        return ResponseEntity.status(409).body(login);
    }

}
