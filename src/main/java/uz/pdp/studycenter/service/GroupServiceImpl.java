package uz.pdp.studycenter.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import uz.pdp.studycenter.entity.Course;
import uz.pdp.studycenter.entity.Group;
import uz.pdp.studycenter.model.req.GroupRequestDto;
import uz.pdp.studycenter.model.resp.GroupResponseDto;
import uz.pdp.studycenter.repo.CourseRepo;
import uz.pdp.studycenter.repo.GroupRepo;

import java.util.List;


@Service
@RequiredArgsConstructor
public class GroupServiceImpl implements GroupService  {
    private final GroupRepo groupRepo;
    private final CourseRepo courseRepo;


    @Override
    public Page<Group> findAll(Integer pageNumber, Integer pageSize, String search) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        if (search != null && !search.isEmpty()) {
            return groupRepo.findAllByNameContainingIgnoreCase(search, pageable);
        }
        return groupRepo.findAll(pageable);
    }


    @Override
    public List<Group> findAll() {
        return groupRepo.findAll();
    }

    @Override
    public Group save(Group group) {
        return groupRepo.save(group);
    }

    @Override
    public void save(GroupRequestDto groupRequestDto) {
        Course course = courseRepo.findById(groupRequestDto.courseId()).get();
        Group group = Group.builder()
                .name(groupRequestDto.name())
                .course(course)
                .build();
        groupRepo.save(group);
    }

    @Override
    public void remove(Long id) {
        Group group = groupRepo.findById(id).get();
        group.setArchived(true);
        groupRepo.save(group);
    }

    @Override
    public List<GroupResponseDto> findAllByIsArchivedFalse() {
        return groupRepo.findAllByIsArchivedFalse();
    }
}
