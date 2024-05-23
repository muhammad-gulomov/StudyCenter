package uz.pdp.studycenter.service;

import org.springframework.stereotype.Service;
import uz.pdp.studycenter.entity.Role;
import uz.pdp.studycenter.entity.enums.RoleName;

@Service
public interface RoleService {

    boolean exists(RoleName name);
    Role save(Role role);

    Role findByName(RoleName roleName);
}
