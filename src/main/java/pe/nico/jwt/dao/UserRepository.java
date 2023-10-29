package pe.nico.jwt.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pe.nico.jwt.entity.User;

@Repository
public interface UserRepository extends CrudRepository<User, String> {
}
