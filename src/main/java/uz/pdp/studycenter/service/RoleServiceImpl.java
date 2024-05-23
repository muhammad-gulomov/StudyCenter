package uz.pdp.studycenter.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.pdp.studycenter.entity.Role;
import uz.pdp.studycenter.entity.enums.RoleName;
import uz.pdp.studycenter.repo.RoleRepo;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService  {


    private final RoleRepo roleRepo;

    @Override
    public boolean exists(RoleName name) {
        return roleRepo.existsByName(name);
    }

    @Override
    public Role save(Role role) {
        return roleRepo.save(role);
    }

    @Override
    public Role findByName(RoleName roleName) {
        return roleRepo.findByName(roleName);
    }
}
