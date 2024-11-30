package product.catalog.service.appilication.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import product.catalog.service.appilication.DTO.FakeStoreProductDto;
import product.catalog.service.appilication.model.Category;
import product.catalog.service.appilication.model.Product;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService{
    @Autowired
    private RestTemplateBuilder restTemplateBuilder;

    @Override
    public List<Product> getProducts() {
        RestTemplate restTemplate = restTemplateBuilder.build();
        FakeStoreProductDto[]  fakeStoreProductArray = restTemplate.getForEntity("http://fakestoreapi.com/products", FakeStoreProductDto[].class).getBody();
        List<FakeStoreProductDto> fakeStoreProductDtoList = Arrays.stream(fakeStoreProductArray
        ).collect(Collectors.toList());
        return fromList(fakeStoreProductDtoList);
    }

    private List<Product> fromList(List<FakeStoreProductDto> fakeStoreProductDtoList) {
        return fakeStoreProductDtoList.stream().map(prod ->
                Product.builder().id(prod.getId()).name(prod.getTitle()).
                        price(prod.getPrice()).description(prod.getDescription())
                        .imageUrl(prod.getImage()).category(
                                Optional.ofNullable(
                                                prod.getCategory())
                                        .map(catName -> Category.builder().name(catName).build()
                                        ).orElse(null)
                        ).build()
        ).collect(Collectors.toList());
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
