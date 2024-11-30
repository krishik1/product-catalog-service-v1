package product.catalog.service.appilication.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import product.catalog.service.appilication.DTO.FakeStoreProductDto;
import product.catalog.service.appilication.DTO.ProductDto;
import product.catalog.service.appilication.model.Category;
import product.catalog.service.appilication.model.Product;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService{
    @Autowired
    private RestTemplateBuilder restTemplateBuilder;

    @Override
    public List<ProductDto> getProducts() {
        return List.of();
    }

    @Override
    public Product getProductById(Long productId) {
        RestTemplate restTemplate = restTemplateBuilder.build();
        FakeStoreProductDto fakestoreDto = restTemplate.getForEntity("http://fakestoreapi.com/products/{productId}", FakeStoreProductDto.class, productId).getBody();
        return from(fakestoreDto);
    }

    private Product from(FakeStoreProductDto fakestoreDto) {
        Category cat = Optional.ofNullable(fakestoreDto.getCategory()).map(catName -> Category.builder().name(catName).build()).orElse(null);
        return Product.builder().id(fakestoreDto.getId()).name(fakestoreDto.getTitle()).
                price(fakestoreDto.getPrice()).description(fakestoreDto.getDescription()).imageUrl(fakestoreDto.getImage())
        .category(cat).build();
    }

    @Override
    public Product createProduct(Product product) {
        return null;
    }
}
