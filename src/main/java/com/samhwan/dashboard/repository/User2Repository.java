package com.samhwan.dashboard.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.samhwan.dashboard.entity.User2;

@Repository
public interface User2Repository extends JpaRepository<User2, Integer> {
    
    boolean existsByUserId(String userId);

    User2 findByUserId(String userId);
    
}