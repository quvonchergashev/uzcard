package com.example.uzcard.service;

import com.example.uzcard.entity.EOPS;
import com.example.uzcard.repositories.EOPSRepository;
import com.example.uzcard.service.interfaces.EOPSService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EOPSServiceImpl implements EOPSService {
    private final EOPSRepository eopsRepository;
    @Override
    public EOPS save(EOPS eops) {
        return eopsRepository.save(eops);
    }
}
