package com.example.demo.repository;

import com.example.demo.model.Magazine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MagazineRepository extends JpaRepository<Magazine, Long> {
    Optional<Magazine> findById(Long id);
}


