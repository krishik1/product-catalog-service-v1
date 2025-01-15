package product.catalog.service.appilication.controller;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import product.catalog.service.appilication.DTO.CategoryDto;
import product.catalog.service.appilication.DTO.ProductDto;
import product.catalog.service.appilication.model.Category;
import product.catalog.service.appilication.model.Product;
import product.catalog.service.appilication.service.ProductService;
import product.catalog.service.appilication.util.ProductCatalogUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/v1/cape-stone")
public class ProductController {
    private final ProductService productService;

    public ProductController(@Qualifier("db_ProductService") ProductService productService) {
        this.productService=productService;
    }
    @GetMapping("/products")
    public ResponseEntity<List<ProductDto>> getProducts() {
        List<Product> productList = productService.getProducts();
        List<ProductDto> productDtoList = new ArrayList<>();
        for(Product product:productList) {
            productDtoList.add(ProductCatalogUtil.toProductDto(product));
        }
        return ResponseEntity.ok(productDtoList);
    }

    /** private List<ProductDto> fromList(List<Product> productList) {
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
    } **/

    @GetMapping("/products/{id}")
    public ResponseEntity<ProductDto> getProductById(@PathVariable("id") Long productId) {
        try {
            if(productId <= 0) {
                throw new IllegalArgumentException("productId is invalid");
            }
            Product product = productService.getProductById(productId);
            ProductDto productDto = ProductCatalogUtil.toProductDto(product);
            return ResponseEntity.ok(productDto);
        }catch (IllegalArgumentException exception) {
            return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);
        }
    }



    @PostMapping("/products")
    public ResponseEntity<ProductDto> createProduct(@RequestBody ProductDto productDto) {
        Product product= toProduct(productDto);
        Product respProduct = productService.createProduct(product);
        return ResponseEntity.ok(ProductCatalogUtil.toProductDto(respProduct));
    }

    @PutMapping("/products/{id}")
    public ResponseEntity<ProductDto> replaceProduct(@PathVariable("id") Long productId,@RequestBody ProductDto productDto) {
        Product product= toProduct(productDto);
        Product respProduct = productService.replaceProduct(productId,product);
        return ResponseEntity.ok(ProductCatalogUtil.toProductDto(respProduct));
    }

    private Product toProduct(ProductDto productDto) {
        CategoryDto cat = productDto.getCategory();
        return Product.builder().id(productDto.getId()).name(productDto.getName()).price(productDto.getPrice())
                .imageUrl(productDto.getImageUrl()).description(productDto.getDescription())
                .category(Category.builder().id(cat.getId()).name(cat.getName())
                        .description(cat.getDescription()).build()).build();
    }

    @PostMapping("/productsList")
    public ResponseEntity<List<ProductDto>> addProducts(@RequestBody List<ProductDto> productDtos) {
        List<Product> products = new ArrayList<>();
        for(ProductDto productDto:productDtos) {
            Product product= toProduct(productDto);
            products.add(product);
        }
        List<Product> productList = productService.addProducts(products);
        List<ProductDto> respProducts = new ArrayList<>();
        for(Product product:productList) {
            ProductDto productDto = ProductCatalogUtil.toProductDto(product);
            respProducts.add(productDto);
        }
        return ResponseEntity.ok(respProducts);
    }
}
