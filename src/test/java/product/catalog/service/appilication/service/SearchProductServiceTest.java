package product.catalog.service.appilication.service;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.*;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import product.catalog.service.appilication.DTO.SortParam;
import product.catalog.service.appilication.DTO.SortType;
import product.catalog.service.appilication.model.Product;
import product.catalog.service.appilication.repository.ProductRepo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class SearchProductServiceTest {
    @Autowired
    SearchProductService searchProductService;

    @MockitoBean
    ProductRepo productRepo;

    @Test
    public void search_Products_SUCCESS_Test() {
        List<Product> productList = new ArrayList<>();
        productList.add(Product.builder().name("Track Suit").price(500.0).description("Cotton Fabric").build());
        Page<Product> productPage = new PageImpl<>(productList);
        List<SortParam> sortParams = Arrays.asList(
                new SortParam(SortType.ASC, "name"),
                new SortParam(SortType.DESC, "price")
        );

        Mockito.when(productRepo.findAll(ArgumentMatchers.any(Pageable.class))).thenReturn(productPage);
        Page<Product> products = searchProductService.searchProducts("Track Suit",3,0,sortParams);
        assertNotNull(products);
        assertEquals(1, products.getTotalElements());
    }

}