package com.example.uzcard.service;
import com.example.uzcard.dto.LoginDto;
import com.example.uzcard.entity.User;
import com.example.uzcard.payload.ResponseApi;
import com.example.uzcard.repositories.UserRepository;
import com.example.uzcard.security.JwtProvider;
import com.example.uzcard.service.interfaces.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements UserDetailsService, AuthService {


    private final UserRepository userRepository;

    private final AuthenticationManager authenticationManager;

    private final JwtProvider jwtProvider;

    @Override
    public UserDetails loadUserByUsername(String phoneNumber) throws UsernameNotFoundException {
        Optional<User> byUsername = userRepository.findByPhoneNumber(phoneNumber);
        if (byUsername.isPresent())
            return byUsername.get();
        throw new UsernameNotFoundException("User topilmadi");
    }
@Override
    public ResponseApi login(LoginDto loginDto) {
        try {
            Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    loginDto.getPhoneNumber(), loginDto.getPassword()));
            User user = (User) authenticate.getPrincipal();
            String token = jwtProvider.generateToken(user.getPhoneNumber());
            return new ResponseApi(token, true);
        } catch (BadCredentialsException e) {
            return new ResponseApi("Parol yoki telefon nomer xato", false);
        }
    }



}
