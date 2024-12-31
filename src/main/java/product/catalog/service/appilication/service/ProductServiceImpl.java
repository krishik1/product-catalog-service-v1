package product.catalog.service.appilication.service;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import product.catalog.service.appilication.DTO.FakeStoreProductDto;
import product.catalog.service.appilication.client.ProductServiceClientI;
import product.catalog.service.appilication.model.Category;
import product.catalog.service.appilication.model.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service("fakestore_ProductService")
@AllArgsConstructor
public class ProductServiceImpl implements ProductService{

    private ProductServiceClientI productServiceClient;

    @Override
    public List<Product> getProducts() {
        List<Product> products = new ArrayList<>();
        List<FakeStoreProductDto> fakeStoreProductDtoList = productServiceClient.getProducts();
        for(FakeStoreProductDto fakeStoreProductDto : fakeStoreProductDtoList) {
            products.add(from(fakeStoreProductDto));
        }
        return products;
    }

    /** private List<Product> fromList(List<FakeStoreProductDto> fakeStoreProductDtoList) {

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
    } **/

    @Override
    public Product getProductById(Long productId) {
        FakeStoreProductDto fakestoreDto = productServiceClient.getProductById(productId);
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
        FakeStoreProductDto fakestoreDtoToAdd = toFakeStoreProductDto(product);
        FakeStoreProductDto fakestoreDto = productServiceClient.addProduct(fakestoreDtoToAdd);
        return from(fakestoreDto);
    }

    @Override
    public Product replaceProduct(Long productId, Product product) {
        FakeStoreProductDto fakestoreDtoToReplace = toFakeStoreProductDto(product);
        FakeStoreProductDto fakestoreDto = productServiceClient.replaceProduct(productId,fakestoreDtoToReplace);
        return from(fakestoreDto);
    }

    private FakeStoreProductDto toFakeStoreProductDto(Product product) {
        return FakeStoreProductDto.builder().id(product.getId()).title(product.getName()).price(product.getPrice()).image(product.getImageUrl()).category(product.getCategory().getName()).build();
    }
}
