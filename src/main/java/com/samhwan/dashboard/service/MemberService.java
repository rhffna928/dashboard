package com.samhwan.dashboard.service;

import com.samhwan.dashboard.dto.MemberDto;

public interface MemberService {
    MemberDto createMember(MemberDto memberDto);
    MemberDto login(String id, String pswd);
    
    
}
