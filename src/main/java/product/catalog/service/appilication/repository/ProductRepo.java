package product.catalog.service.appilication.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import product.catalog.service.appilication.model.Product;

import java.util.List;

@Repository
public interface ProductRepo extends JpaRepository<Product,Long> {
    Product save(Product product);
    Page<Product> findAll(Pageable pageable);
}
