package uz.pdp.studycenter.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.pdp.studycenter.entity.Role;
import uz.pdp.studycenter.entity.enums.RoleName;


@Repository
public interface RoleRepo extends JpaRepository<Role, Long> {

    boolean existsByName(RoleName name);

    Role findByName(RoleName name);

}
