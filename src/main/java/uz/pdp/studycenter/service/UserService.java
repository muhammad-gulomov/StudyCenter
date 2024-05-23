package uz.pdp.studycenter.service;

import org.springframework.stereotype.Service;
import uz.pdp.studycenter.entity.User;
import uz.pdp.studycenter.model.resp.StudentInfoResponseDto;
import uz.pdp.studycenter.model.resp.StudentRespDto;

import java.util.List;

@Service
public interface UserService {
    User findByPhoneNumber(String phoneNumber);
    User save(User user);
    List<StudentRespDto> findAllByIsArchivedFalse();
    List<StudentRespDto> findAllBySearch(String search);
    StudentInfoResponseDto findStudentInfosById(Long id);
    User findById(Long id);
}
