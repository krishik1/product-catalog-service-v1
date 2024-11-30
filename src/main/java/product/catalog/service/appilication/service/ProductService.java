package product.catalog.service.appilication.service;

import product.catalog.service.appilication.DTO.ProductDto;
import product.catalog.service.appilication.model.Product;

import java.util.List;

public interface ProductService {
    List<ProductDto> getProducts();

    Product getProductById(Long productId);

    Product createProduct(Product product);
}
