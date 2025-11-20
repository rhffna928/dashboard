package com.samhwan.dashboard.mapper;

import com.samhwan.dashboard.dto.MemberDto;

public class MemberMapper {

    public static MemberDto mapToMemberDto(MemberDto member) {
        MemberDto memberDto = new MemberDto(
            member.getM_idx(),
            member.getM_id(),
            member.getM_name(),
            null,
            member.getM_dept(),
            member.getM_phone(),
            member.getM_role()
        );
        return memberDto;
    }
    public static MemberDto mapToMember(MemberDto memberDto) {
        MemberDto member = new MemberDto(
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
