package com.samhwan.dashboard.dto.response.admin;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.samhwan.dashboard.common.ResponseCode;
import com.samhwan.dashboard.common.ResponseMessage;
import com.samhwan.dashboard.dto.response.ResponseDto;
import com.samhwan.dashboard.entity.User2;

import lombok.Getter;

@Getter
public class GetAdminUserListResponseDto extends ResponseDto {

    private final List<UserSummary> users;

    private GetAdminUserListResponseDto(List<UserSummary> users) {
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
        this.users = users;
    }
    public static ResponseEntity<GetAdminUserListResponseDto> success(List<User2> users){
        List<UserSummary> list = users.stream()
                                    .map(UserSummary::fromEntity)
                                    .toList();
        GetAdminUserListResponseDto result =  new GetAdminUserListResponseDto(list);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    public static ResponseEntity<ResponseDto> notExistUser(){
        ResponseDto result = new ResponseDto(ResponseCode.NOT_EXISTED_USER, ResponseMessage.NOT_EXISTED_USER);
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(result);
    }
    @Getter
    public static class UserSummary {
        private int id;
        private String userId;
        private String userName;
        private String memo;
        private String phone;
        private String auth;
        private String email;

        public static UserSummary fromEntity(User2 users) {
            UserSummary s = new UserSummary();
            s.id = users.getId();
            s.userId = users.getUserId();
            s.userName = users.getUserName();
            s.memo = users.getMemo();
            s.phone = users.getPhone();
            s.auth = String.valueOf(users.getAuth()); // auth가 숫자면 String 변환
            s.email = users.getEmail();
            return s;
        }
    }
}
