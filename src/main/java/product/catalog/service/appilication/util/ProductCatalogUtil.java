package product.catalog.service.appilication.util;

import product.catalog.service.appilication.DTO.CategoryDto;
import product.catalog.service.appilication.DTO.ProductDto;
import product.catalog.service.appilication.model.Product;

import java.util.Optional;

public class ProductCatalogUtil {
    public static ProductDto toProductDto(Product product) {
        CategoryDto categoryDto = Optional.ofNullable(product.getCategory()).map(category ->
                CategoryDto.builder().id(category.getId()).name(category.getName()).description(category.getDescription()).build()).orElse(null);
        return ProductDto.builder().id(product.getId()).name(product.getName())
                .price(product.getPrice()).description(product.getDescription())
                .imageUrl(product.getImageUrl()).category(categoryDto).build();
    }

}
