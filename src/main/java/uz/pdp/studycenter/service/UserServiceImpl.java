package uz.pdp.studycenter.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.pdp.studycenter.entity.User;
import uz.pdp.studycenter.model.resp.StudentInfoResponseDto;
import uz.pdp.studycenter.model.resp.StudentRespDto;
import uz.pdp.studycenter.repo.UserRepo;

import java.util.List;


@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepo userRepo;

    @Override
    public User findByPhoneNumber(String phoneNumber) {
        return userRepo.findByPhoneNumber(phoneNumber);
    }

    @Override
    public User save(User user) {
        return userRepo.save(user);
    }

    @Override
    public List<StudentRespDto> findAllByIsArchivedFalse() {
        return userRepo.findAllByIsArchivedFalse();
    }

    @Override
    public List<StudentRespDto> findAllBySearch(String search) {
        return userRepo.findAllBySearch(search).stream().map(item->mapToStudentRespDto(item)).toList();
    }

    private StudentRespDto mapToStudentRespDto(Object[] item) {
        Long id = (Long) item[0];
        String firstName = (String) item[1];
        String lastName = (String) item[2];
        String phoneNumber = (String) item[3];
        return new StudentRespDto(id, firstName, lastName, phoneNumber);
    }

    @Override
    public User findById(Long id) {
        return userRepo.findById(id).get();
    }

    @Override
    public StudentInfoResponseDto findStudentInfosById(Long id) {
        List<Object[]> studentInfosById = userRepo.findStudentInfosById(id);
        return studentInfosById.stream().map(this::mapToStudentInfoDto).toList().get(0);
    }

    private StudentInfoResponseDto mapToStudentInfoDto(Object[] item) {
        Long id = (Long) item[0];
        String firstName = (String) item[1];
        String lastName = (String) item[2];
        String phoneNumber = (String) item[3];
        String timeTable = (String) item[4];
        Integer currentLesson = (Integer) item[5];
        String groupName = (String) item[6];
        String courseName = (String) item[7];
        Long balance = (Long) item[8];
        return new StudentInfoResponseDto(id, firstName,lastName,phoneNumber,balance,timeTable,currentLesson, groupName,courseName);
    }
}
