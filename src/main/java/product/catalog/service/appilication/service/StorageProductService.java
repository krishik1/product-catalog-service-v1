package product.catalog.service.appilication.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import product.catalog.service.appilication.model.Product;
import product.catalog.service.appilication.repository.ProductRepo;

import java.util.List;
@Service("db_ProductService")
@AllArgsConstructor
public class StorageProductService implements ProductService{
    private ProductRepo productRepo;

    @Override
    public List<Product> getProducts() {
        return List.of();
    }

    @Override
    public Product getProductById(Long productId) {
        return null;
    }

    @Override
    public Product createProduct(Product product) {
        return productRepo.save(product);
    }

    @Override
    public Product replaceProduct(Long productId, Product product) {
        return null;
    }
}
