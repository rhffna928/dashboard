package com.samhwan.dashboard.service.implement;

import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.samhwan.dashboard.dto.request.admin.UpdateUserRequestDto;
import com.samhwan.dashboard.dto.response.ResponseDto;
import com.samhwan.dashboard.dto.response.admin.DeleteUserResponseDto;
import com.samhwan.dashboard.dto.response.admin.GetAdminUserListResponseDto;
import com.samhwan.dashboard.dto.response.admin.UpdateUserResponseDto;
import com.samhwan.dashboard.entity.User2;
import com.samhwan.dashboard.repository.AdminRepository;
import com.samhwan.dashboard.service.AdminService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService{
    
    private final AdminRepository adminRepository;

    private boolean isAdmin(User2 user) {
        // auth == 5 이 관리자라면
        return user != null && "5".equals(user.getAuth());
    }
    @Override
    public ResponseEntity<? super GetAdminUserListResponseDto> getAdminUserList() {

        try {
            List<User2> users = adminRepository.findAllByOrderByUserIdDesc();
            return GetAdminUserListResponseDto.success(users);

        } catch (Exception e) {
            e.printStackTrace();
            return GetAdminUserListResponseDto.databaseError();
        }
        
    }
    @Transactional
    @Override
    public ResponseEntity<? super DeleteUserResponseDto> deleteUser(String userId) {
        try {
            User2 target = adminRepository.findByUserId(userId).orElse(null);
            if (target == null) return DeleteUserResponseDto.notExistUser();

            adminRepository.delete(target);

            return DeleteUserResponseDto.success(userId);
            
        } catch (Exception e) {
            e.printStackTrace();
            return DeleteUserResponseDto.databaseError();
        }
    }

    @Transactional
    @Override
    public ResponseEntity<? super UpdateUserResponseDto> updateUser(
        String currentUserId,
        String targetUserId, UpdateUserRequestDto requestBody) {
        System.out.println(targetUserId);
        User2 currentUser = adminRepository.findByUserId(currentUserId).orElse(null);
        if(!isAdmin(currentUser)) return UpdateUserResponseDto.permit();
        User2 targetUser = adminRepository.findByUserId(targetUserId).orElse(null);
        if(targetUser == null) return UpdateUserResponseDto.notExistUser();

        targetUser.updateAdmin(requestBody);
        System.out.println(requestBody.getUserName());
        return UpdateUserResponseDto.success(targetUser);
    }

}
