package com.example.uzcard.service.interfaces;


import com.example.uzcard.dto.LoginDto;
import com.example.uzcard.payload.ResponseApi;

public interface AuthService {

    public ResponseApi login(LoginDto loginDto);

}
