package product.catalog.service.appilication.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import product.catalog.service.appilication.DTO.CategoryDto;
import product.catalog.service.appilication.DTO.ProductDto;
import product.catalog.service.appilication.DTO.SearchDto;
import product.catalog.service.appilication.model.Product;
import product.catalog.service.appilication.service.ISearchService;
import product.catalog.service.appilication.service.SearchProductService;
import product.catalog.service.appilication.util.ProductCatalogUtil;

@RestController
@RequestMapping("/v1/cape-stone/search")
public class SearchController {
    @Autowired
    private ISearchService productService;

    @PostMapping("/searchProducts")
    public Page<ProductDto> searchProducts(@RequestBody SearchDto searchDto) {
        Page<Product> products = productService.searchProducts(searchDto.getQuery(),searchDto.getPageSize(),searchDto.getPageNumber(),searchDto.getSortParams());

        return toProductDto(products);

    }

    public Page<ProductDto> toProductDto(Page<Product> products) {
        return products.map(product -> ProductCatalogUtil.toProductDto(product));
    }

}
