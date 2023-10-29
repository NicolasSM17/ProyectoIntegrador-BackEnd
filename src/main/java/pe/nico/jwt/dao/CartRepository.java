package pe.nico.jwt.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pe.nico.jwt.entity.Cart;
import pe.nico.jwt.entity.User;

import java.util.List;

@Repository
public interface CartRepository extends CrudRepository<Cart, Integer> {
    public List<Cart> findByUser(User user);
}
