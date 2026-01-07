package com.samhwan.dashboard.service.implement;

import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
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

    @Override
    public ResponseEntity<? super GetAdminUserListResponseDto> getAdminUserList() {

        try {
            List<User2> users = adminRepository.findAllByOrderByUserIdAsc();
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
            User2 target = adminRepository.findByUserId(userId);
            if (target == null) return DeleteUserResponseDto.notExistUser();

            adminRepository.delete(target);

            return DeleteUserResponseDto.success(userId);
            
        } catch (Exception e) {
            e.printStackTrace();
            return DeleteUserResponseDto.databaseError();
        }
    }

    @Override
    public ResponseEntity<? super UpdateUserResponseDto> updateUser(String userId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateUser'");
    }

}
