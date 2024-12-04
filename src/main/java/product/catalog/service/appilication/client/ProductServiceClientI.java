package product.catalog.service.appilication.client;

import product.catalog.service.appilication.DTO.FakeStoreProductDto;
import product.catalog.service.appilication.model.Product;

import java.util.List;

public interface ProductServiceClientI {
    FakeStoreProductDto getProductById(Long productId) ;
    List<FakeStoreProductDto> getProducts();
    FakeStoreProductDto addProduct(FakeStoreProductDto product);
    FakeStoreProductDto replaceProduct(Long productId,FakeStoreProductDto product);
}
