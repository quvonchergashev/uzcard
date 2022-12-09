package com.example.uzcard.repositories;

import com.example.uzcard.entity.EOPS;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EOPSRepository extends JpaRepository<EOPS, Long> {
}
