package com.samhwan.dashboard.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.samhwan.dashboard.entity.User2;


@Repository
public interface AdminRepository extends JpaRepository<User2, Integer> {
    
    boolean existsByUserId(String userId);

    Optional<User2> findByUserId(String userId);

    List<User2> findAllByOrderByUserIdDesc();

    
}