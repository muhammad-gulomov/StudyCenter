package uz.pdp.studycenter.repo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.pdp.studycenter.entity.Group;
import uz.pdp.studycenter.model.resp.GroupResponseDto;

import java.util.List;

@Repository
public interface GroupRepo extends JpaRepository<Group, Long> {
    Page<Group> findAllByNameContainingIgnoreCase(String search, Pageable pageable);
    List<GroupResponseDto> findAllByIsArchivedFalse();

}
