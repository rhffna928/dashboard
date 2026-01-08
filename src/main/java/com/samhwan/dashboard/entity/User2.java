package com.samhwan.dashboard.entity;

import java.time.LocalDateTime;

import com.samhwan.dashboard.dto.request.admin.UpdateUserRequestDto;
import com.samhwan.dashboard.dto.request.auth.SignUpRequestDto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "user_list2")
@Table(name = "user_list2")
public class User2 {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "user_id")
    private String userId;
    @Column(name = "user_name")
    private String userName;
    @Column(name = "user_password")
    private String userPassword;
    @Column(name = "memo")
    private String memo;
    @Column(name = "auth", nullable = false)
    private String auth;
    @Column(name = "phone")
    private String phone;
    @Column(name = "sms_yn", nullable = false)
    private String smsYn;
    @Column(name = "email")
    private String email;
    @Column(name = "regdate", insertable = false, updatable = false)
    private LocalDateTime regdate;

    @Builder
    public User2(SignUpRequestDto dto){
        this.userId = dto.getUserId();
        this.userName = dto.getUserName();
        this.userPassword = dto.getUserPassword();
        this.memo = dto.getMemo();
        this.auth = dto.getAuth();
        this.phone = dto.getPhone();
        this.smsYn = dto.getSmsYn();
        this.email = dto.getEmail();
    }

    public void updateAdmin(UpdateUserRequestDto dto) {

        this.userId = dto.getUserId();
        this.userName = dto.getUserName();
        this.memo = dto.getMemo();
        this.auth = dto.getAuth();
        this.smsYn = dto.getSmsYn();
        this.phone = dto.getPhone();
        this.email = dto.getEmail();

    }


}
