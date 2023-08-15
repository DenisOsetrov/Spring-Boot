package ua.com.owu.springboot.services.role;

import ua.com.owu.springboot.models.role.Role;
import ua.com.owu.springboot.models.role.RoleType;

public interface RoleService {
    Role findByName(RoleType name);
}