package pe.nico.jwt.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pe.nico.jwt.entity.Role;

@Repository
public interface RoleRepository extends CrudRepository<Role, String> {
}
