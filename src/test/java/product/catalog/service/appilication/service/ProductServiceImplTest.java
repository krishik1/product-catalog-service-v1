package product.catalog.service.appilication.service;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import product.catalog.service.appilication.DTO.FakeStoreProductDto;
import product.catalog.service.appilication.client.ProductServiceClientI;
import product.catalog.service.appilication.model.Category;
import product.catalog.service.appilication.model.Product;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class ProductServiceImplTest {
    @Autowired
    @Qualifier("fakestore_ProductService")
    ProductService productService;

    @MockitoBean
    ProductServiceClientI productServiceClientI;

    FakeStoreProductDto mockResponse = resp();


    @Test
    public void Product_List_Success() {
        Mockito.when(productServiceClientI.getProducts()).thenReturn(new ArrayList<>());
        List<Product> resultList = productService.getProducts();
        assertNotNull(resultList);
    }

    @Test
    public void ProductByIdSuccess() {
        Long id = 2L;
        Mockito.when(productServiceClientI.getProductById(ArgumentMatchers.anyLong())).thenReturn(mockResponse);
        Product product = productService.getProductById(id);
        assertNotNull(product);
        assertEquals(id,product.getId());

    }

    @Test
    public void ProductCreationSuccess() {
        Long id = 2L;
        Mockito.when(productServiceClientI.addProduct(ArgumentMatchers.any())).thenReturn(
                mockResponse);
        Product input =Product.builder().id(2L).name("T-shirt").category(Category.builder().name("Clothes").build()).build();
        Product product = productService.createProduct(input);
        assertNotNull(product);
        assertEquals(id,product.getId());
    }

    @Test
    public void ProductReplaceSuccess() {
        Long id = 2L;
        Mockito.when(productServiceClientI.replaceProduct(ArgumentMatchers.any(),ArgumentMatchers.any())).thenReturn(
                mockResponse);
        Product input =Product.builder().id(2L).name("T-shirt").category(Category.builder().name("Clothes").build()).build();
        Product product = productService.replaceProduct(id,input);
        assertNotNull(product);
        assertEquals(id,product.getId());
    }

    private FakeStoreProductDto resp() {
        return FakeStoreProductDto.builder().id(2L).description("Mens Ware")
                        .category("Clothes").title("T-shirt").price(800.0).build();
    }
}