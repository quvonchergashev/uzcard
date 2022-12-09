package com.example.uzcard.service;
import com.example.uzcard.entity.User;
import com.example.uzcard.repositories.UserRepository;
import com.example.uzcard.service.interfaces.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public Page<User> getAllUser(int page) {

        PageRequest pageRequest = PageRequest.of(page, 10);
        Page<User> userRepositoryAll = userRepository.findAll(pageRequest);
        return userRepositoryAll;
    }

    @Override
    public User getById(Long id) {
        Optional<User> repository = userRepository.findById(id);
        return repository.orElse(null);
    }

    @Override
    public User getByPhoneNumber(String phoneNumber) {
        Optional<User> phoneNumber1 = userRepository.findByPhoneNumber(phoneNumber);
        return phoneNumber1.orElse(null);
    }
}
