package pe.nico.jwt.dao;

import org.springframework.data.domain.Pageable;
import pe.nico.jwt.entity.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends CrudRepository<Product, Integer> {
    public List<Product> findAll(Pageable pageable);
    public List<Product> findByProductNameContainingIgnoreCaseOrProductDescriptionContainingIgnoreCase(
            String key1, String key2, Pageable pageable
    );
}
