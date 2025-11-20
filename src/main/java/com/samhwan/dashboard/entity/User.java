package com.samhwan.dashboard.entity;

import java.time.LocalDateTime;

import com.samhwan.dashboard.dto.request.auth.SignUpRequestDto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "user_list")
@Table(name = "user_list")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "user_id", nullable = false, length = 20)
    private String userId;
    @Column(name = "user_name", nullable = false, length = 20)
    private String userName;
    @Column(name = "user_password", nullable = false, length = 60)
    private String userPassword;
    @Column(name = "belong", nullable = false, length = 20)
    private String belong;
    @Column(name = "plant_id")
    private Integer plantId;
    @Column(name = "phone", nullable = false, length = 20)
    private String phone;
    @Column(name = "sms_yn", nullable = false, length = 20)
    private String smsYn;
    @Column(name = "sms_type", nullable = false, length = 20)
    private String smsType;
    @Column(name = "sms_list", nullable = false, length = 500)
    private String smsList;
    @Column(name = "m_type", nullable = false, length = 20)
    private String mType;
    @Column(name = "regdate", nullable = false)
    private LocalDateTime regdate;

}
