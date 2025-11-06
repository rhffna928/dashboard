package com.samhwan.dashboard.mapper;

import com.samhwan.dashboard.dto.MemberDto;
import com.samhwan.dashboard.entity.Member;

public class MemberMapper {

    public static MemberDto mapToMemberDto(Member member) {
        MemberDto memberDto = new MemberDto(
            member.getM_idx(),
            member.getM_id(),
            member.getM_name(),
            member.getM_pswd(),
            member.getM_dept(),
            member.getM_phone(),
            member.getM_role()
        );
        return memberDto;
    }
    public static Member mapToMember(MemberDto memberDto) {
        Member member = new Member(
            memberDto.getM_idx(),
            memberDto.getM_id(),
            memberDto.getM_name(),
            memberDto.getM_pswd(),
            memberDto.getM_dept(),
            memberDto.getM_phone(),
            memberDto.getM_role()
        );
        return member;
    }

}
