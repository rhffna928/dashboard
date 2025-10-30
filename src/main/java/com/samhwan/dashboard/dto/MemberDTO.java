package com.samhwan.dashboard.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MemberDto {
    private Long m_id;
    private String m_name;
    private String m_pswd;
    private String m_dept;
    private String m_phone;
    private String m_role;

}
