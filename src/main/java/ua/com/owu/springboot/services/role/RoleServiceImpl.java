package ua.com.owu.springboot.services.role;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ua.com.owu.springboot.repositories.RoleRepository;
import ua.com.owu.springboot.models.role.Role;
import ua.com.owu.springboot.models.role.RoleType;

@Service
@AllArgsConstructor
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    @Override
    public Role findByName(RoleType name) {
        return roleRepository.findByName(name);
    }
}
