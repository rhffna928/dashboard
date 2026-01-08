package com.samhwan.dashboard.dto.request.admin;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UpdateUserRequestDto {

    
    @NotBlank
    private String userId;
    @NotBlank
    private String userName;
    @NotBlank
    private String memo;
    @NotBlank
    private String auth;
    @Pattern(regexp = "^\\d{2,3}-\\d{3,4}-\\d{4}$", message = "전화번호 형식이 올바르지 않습니다.")
    @NotBlank
    private String phone;
    @NotBlank
    private String smsYn;
    @Email
    @NotBlank
    private String email;

}
