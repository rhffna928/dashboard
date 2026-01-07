package com.samhwan.dashboard.dto.request.auth;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
    private String memo;
    @NotBlank
    private String auth;
    private String phone;
    private String smsYn;
    private String email;

}
