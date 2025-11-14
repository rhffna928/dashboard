package com.samhwan.dashboard.service.implement;


import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.samhwan.dashboard.dto.MemberDto;
import com.samhwan.dashboard.entity.Member;
import com.samhwan.dashboard.mapper.MemberMapper;
import com.samhwan.dashboard.provider.JwtProvider;
import com.samhwan.dashboard.repository.MemberRepository;
import com.samhwan.dashboard.service.MemberService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    

    @Override
    public MemberDto createMember(MemberDto memberDto) {

        if (memberRepository.existsById(memberDto.getM_id())){
            throw new IllegalArgumentException("이미 사용중인 사용자명입니다.");
        }
        String encodedPassword = passwordEncoder.encode(memberDto.getM_pswd());
        memberDto.setM_pswd(encodedPassword);
        Member member = MemberMapper.mapToMember(memberDto);
        Member savedMember = memberRepository.save(member);
        return MemberMapper.mapToMemberDto(savedMember);
    }

    @Override
    public MemberDto login(String id, String pswd) {

        //아이디로 회원 조회
        Member member = memberRepository.findById(id)
                            .orElseThrow(() -> new IllegalArgumentException("아이디 및 비밀번호를 확인해주세요"));
        //비밀번호 확인 (받은 비밀번호, 서버 비밀번호)
        if (!passwordEncoder.matches(pswd, member.getM_pswd())){
            throw new IllegalArgumentException("아이디 및 비밀번호를 확인해주세요");
        }
        // 아이디로 토큰발급
        String token = JwtProvider.create(member.getM_id());

        //
    }
    

    
}
