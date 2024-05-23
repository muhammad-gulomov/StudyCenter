package uz.pdp.studycenter.service;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import uz.pdp.studycenter.entity.Course;
import uz.pdp.studycenter.entity.Group;
import uz.pdp.studycenter.model.req.GroupRequestDto;
import uz.pdp.studycenter.model.resp.GroupResponseDto;

import java.util.List;


@Service
public interface GroupService {
    Page<Group> findAll(Integer pageNumber, Integer pageSize, String search);

    List<Group> findAll();

    Group save(Group group);

    void save(GroupRequestDto groupRequestDto);

    void remove(Long id);

    List<GroupResponseDto> findAllByIsArchivedFalse();
}
