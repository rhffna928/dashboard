package com.samhwan.dashboard.dto.request.auth;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class SignUpRequestDto {

    @NotBlank
    private String userId;
    @NotBlank
    private String userName;
    @NotBlank
    private String userPassword;
    @NotBlank
    private String memo;
    @NotBlank
    private String auth;
    @NotBlank
    private String phone;
    @NotBlank
    private String smsYn;
    @NotBlank
    private String email;

}
