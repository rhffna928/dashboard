package com.samhwan.dashboard.service.implement;

import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.samhwan.dashboard.dto.request.auth.SignUpRequestDto;
import com.samhwan.dashboard.dto.request.inverter.CreateInverterRequestDto;
import com.samhwan.dashboard.dto.request.inverter.UpdateInverterListRequestDto;
import com.samhwan.dashboard.dto.response.ResponseDto;
import com.samhwan.dashboard.dto.response.admin.DeleteUserResponseDto;
import com.samhwan.dashboard.dto.response.auth.SignUpResponseDto;
import com.samhwan.dashboard.dto.response.inverter_list.CreateInverterResponseDto;
import com.samhwan.dashboard.dto.response.inverter_list.DeleteInverterListResponseDto;
import com.samhwan.dashboard.dto.response.inverter_list.GetInverterList2ResponseDto;
import com.samhwan.dashboard.dto.response.inverter_list.GetUserInverterList2ResponseDto;
import com.samhwan.dashboard.dto.response.inverter_list.UpdateInverterListResponseDto;
import com.samhwan.dashboard.entity.InverterList2;
import com.samhwan.dashboard.entity.User2;
import com.samhwan.dashboard.repository.AdminRepository;
import com.samhwan.dashboard.repository.InverterList2Repository;
import com.samhwan.dashboard.repository.InverterRepository;
import com.samhwan.dashboard.repository.PlantList2Repository;
import com.samhwan.dashboard.repository.PlantListRepository;
import com.samhwan.dashboard.service.InverterList2Service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class InverterList2ServiceImpl implements InverterList2Service{
    
    private final InverterRepository inverterRepository;
    private final InverterList2Repository inverterList2Repository;
    private final AdminRepository adminRepository;
    private final PlantList2Repository plantList2Repository;
    
    private boolean isAdmin(User2 user) {
        // auth == 5 이 관리자라면
        return user != null && "5".equals(user.getAuth());
    }

    @Override
    public ResponseEntity<? super GetInverterList2ResponseDto> getInverterList2() {

        try {
            List<InverterList2> inverterList2s = inverterList2Repository.findAllByOrderByIdAsc();
            return GetInverterList2ResponseDto.success(inverterList2s);

        } catch (Exception e) {
            e.printStackTrace();
            return GetInverterList2ResponseDto.databaseError();
        }
        
    }

    @Transactional
    @Override
    public ResponseEntity<? super CreateInverterResponseDto> createInverter(CreateInverterRequestDto dto) {
           
        try{
            String invName = dto.getInvName();
            
            System.out.println(dto);
            boolean exexistsByName = inverterList2Repository.existsByInvName(invName);
            if(exexistsByName) return CreateInverterResponseDto.duplicateId();
            String invId = dto.getInvId();
            boolean exexistsById = inverterList2Repository.existsByInvName(invId);
            if(exexistsById) return CreateInverterResponseDto.duplicateId();
            InverterList2 inverterList2 = new InverterList2(dto);
            System.out.println(dto);
            System.out.println(inverterList2.getInvName());
            inverterList2Repository.save(inverterList2);
            Integer plantid = inverterList2.getUnitId();

            int updated = plantList2Repository.incInvCountUp(plantid);
            if (updated  == 0 ){
                throw new IllegalAccessException("해당 unit_id의 plant가 존재하지 않습니다: " + plantid);
            }

        }catch(Exception e){
            e.printStackTrace();
            return ResponseDto.databaseError();
        }
        return CreateInverterResponseDto.success();
    }

    @Transactional
    @Override
    public ResponseEntity<? super UpdateInverterListResponseDto> updateInverter(
        String currentUserId,
        Integer id,
        UpdateInverterListRequestDto requestBody) {
        
        User2 currentUser = adminRepository.findByUserId(currentUserId).orElse(null);
        if(!isAdmin(currentUser)) return UpdateInverterListResponseDto.permit();
        InverterList2 targetId = inverterList2Repository.findById(id).orElse(null);
        if(targetId == null) return UpdateInverterListResponseDto.notExistUser();
        targetId.updateInverterList2(requestBody);
        return UpdateInverterListResponseDto.success(targetId);
    }

    @Transactional
    @Override
    public ResponseEntity<? super DeleteInverterListResponseDto> deleteInverter(Integer id) {
        try {
            InverterList2 target = inverterList2Repository.findById(id).orElse(null);
            if (target == null) return DeleteInverterListResponseDto.notExistUser();
            int oldUnitId = target.getUnitId();

            inverterList2Repository.delete(target);

            Integer updated = plantList2Repository.incInvCountDown(oldUnitId);
            if (updated  == 0 ){
                throw new IllegalAccessException("inv_count 감소 실패(plant 없음 또는 이미 0): unit_id=" + oldUnitId);
            }
            

            return DeleteInverterListResponseDto.success(id);
            
        } catch (Exception e) {
            e.printStackTrace();
            return DeleteUserResponseDto.databaseError();
        }
    }

    @Override
    public ResponseEntity<? super GetUserInverterList2ResponseDto> getUserInverterList2(
        String userId
    ) {
        try{
            List<InverterList2> list = inverterList2Repository.findAllByUserId(userId);
            return GetUserInverterList2ResponseDto.success(list);
        }catch(Exception e){
            e.printStackTrace();
            return GetUserInverterList2ResponseDto.databaseError();

        }

    }

}
