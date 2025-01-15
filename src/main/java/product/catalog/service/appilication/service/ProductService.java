package product.catalog.service.appilication.service;

import product.catalog.service.appilication.DTO.ProductDto;
import product.catalog.service.appilication.model.Product;

import java.util.List;

public interface ProductService {
    List<Product> getProducts();

    Product getProductById(Long productId);

    Product createProduct(Product product);

    Product replaceProduct(Long productId,Product product);

    List<Product> addProducts(List<Product> products);

}
