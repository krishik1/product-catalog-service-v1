package product.catalog.service.appilication.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import product.catalog.service.appilication.model.Product;

@Repository
public interface ProductRepo extends JpaRepository<Product,Long> {
    Product save(Product product);
}
