package product.catalog.service.appilication.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import product.catalog.service.appilication.DTO.CategoryDto;
import product.catalog.service.appilication.DTO.ProductDto;
import product.catalog.service.appilication.model.Product;
import product.catalog.service.appilication.service.ProductService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
@RequestMapping("/v1/cape-stone")
public class ProductController {
    private ProductService productService;
    @GetMapping("/products")
    public ResponseEntity<List<ProductDto>> getProducts() {
        List<Product> productList = productService.getProducts();
        List<ProductDto> productDtoList = fromList(productList);
        return ResponseEntity.ok(productDtoList);
    }

    private List<ProductDto> fromList(List<Product> productList) {
        return productList.stream().map(prod -> ProductDto.builder().id(prod.getId())
                .name(prod.getName())
                .price(prod.getPrice())
                .imageUrl(prod.getImageUrl())
                .description(prod.getDescription())
                .category(Optional.ofNullable(prod.getCategory())
                        .map(cat -> CategoryDto.builder().id(cat.getId())
                                .name(cat.getName())
                                .description(cat.getDescription())
                                .build()).orElse(null))
                .build()).collect(Collectors.toList());
    }

    @GetMapping("/products/{id}")
    public ResponseEntity<ProductDto> getProductById(@PathVariable("id") Long productId) {
        try {
            if(productId <= 0) {
                throw new IllegalArgumentException("productId is invalid");
            }
            Product product = productService.getProductById(productId);
            ProductDto productDto = from(product);
            return ResponseEntity.ok(productDto);
        }catch (IllegalArgumentException exception) {
            return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);
        }
    }

    private ProductDto from(Product product) {
        CategoryDto categoryDto = Optional.ofNullable(product.getCategory()).map(category ->
                CategoryDto.builder().id(category.getId()).name(category.getName()).description(category.getDescription()).build()).orElse(null);
        return ProductDto.builder().id(product.getId()).name(product.getName())
                .price(product.getPrice()).description(product.getDescription())
                .imageUrl(product.getImageUrl()).category(categoryDto).build();
    }

    @PostMapping("/products")
    public ProductDto createProduct(@RequestBody ProductDto productDto) {
        return productDto;
    }
}
