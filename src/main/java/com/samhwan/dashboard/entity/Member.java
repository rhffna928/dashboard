package com.samhwan.dashboard.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "members")
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long m_id;

    @Column(name = "m_name")
    private String m_name;
    @Column(name = "m_pswd")
    private String m_pswd;
    @Column(name = "m_dept")
    private String m_dept;
    @Column(name = "m_phone")
    private String m_phone;
    @Column(name = "m_role")
    private String m_role;

}
