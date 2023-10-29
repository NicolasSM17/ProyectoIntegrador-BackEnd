package pe.nico.jwt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pe.nico.jwt.entity.Role;
import pe.nico.jwt.service.RoleService;

@RestController
public class RoleController {
    @Autowired
    private RoleService roleService;

    @PostMapping({"/createNewRole"})
    public Role createNewRole(@RequestBody Role role){
        return roleService.createNewRole(role);
    }
}
