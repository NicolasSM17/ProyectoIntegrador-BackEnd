package pe.nico.jwt.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pe.nico.jwt.entity.OrderDetail;
import pe.nico.jwt.entity.User;

import java.util.List;

@Repository
public interface OrderDetailRepository extends CrudRepository<OrderDetail, Integer> {
    public List<OrderDetail> findByUser(User user);
    public List<OrderDetail> findByOrderStatus(String status);
}
