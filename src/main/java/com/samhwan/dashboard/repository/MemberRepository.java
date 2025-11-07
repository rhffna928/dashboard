package com.samhwan.dashboard.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.samhwan.dashboard.entity.Member;

public interface MemberRepository extends JpaRepository<Member, Long> {
    
}