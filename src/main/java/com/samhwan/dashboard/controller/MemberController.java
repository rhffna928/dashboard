package com.samhwan.dashboard.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.samhwan.dashboard.dto.MemberDto;
import com.samhwan.dashboard.service.MemberService;

@RestController
@RequestMapping("/api/members")
public class MemberController {

    private MemberService memberService;

    // build Add member REST API
    @PostMapping
    public ResponseEntity<MemberDto> createMember(@RequestBody MemberDto memberDto) {
        MemberDto savedMember = memberService.createMember(memberDto);
        return new ResponseEntity<>(savedMember, HttpStatus.CREATED);
        
    }

}
