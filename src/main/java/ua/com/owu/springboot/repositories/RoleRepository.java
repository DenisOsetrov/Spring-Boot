package ua.com.owu.springboot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.com.owu.springboot.models.role.Role;
import ua.com.owu.springboot.models.role.RoleType;

public interface RoleRepository extends JpaRepository<Role, Integer> {
    Role findByName(RoleType name);
}
