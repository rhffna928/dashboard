package com.samhwan.dashboard.service.implement;


import org.springframework.stereotype.Service;

import com.samhwan.dashboard.dto.MemberDto;
import com.samhwan.dashboard.entity.Member;
import com.samhwan.dashboard.mapper.MemberMapper;
import com.samhwan.dashboard.repository.MemberRepository;
import com.samhwan.dashboard.service.MemberService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class MemberServiceImpl implements MemberService {

    private MemberRepository memberRepository;
    

    @Override
    public MemberDto createMember(MemberDto memberDto) {

        // if (memberRepository.existsById(memberDto.getM_id())){
        //     throw new IllegalArgumentException("이미 사용중인 사용자명입니다.");
        // }

        Member member = MemberMapper.mapToMember(memberDto);
        

        Member savedMember = memberRepository.save(member);


        return MemberMapper.mapToMemberDto(savedMember);
    }

    
}
