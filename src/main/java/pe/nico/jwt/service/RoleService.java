package pe.nico.jwt.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.nico.jwt.dao.RoleRepository;
import pe.nico.jwt.entity.Role;

@Service
public class RoleService {
    @Autowired
    private RoleRepository roleRepository;

    public Role createNewRole(Role role){
        return roleRepository.save(role);
    }
}
