package product.catalog.service.appilication.service;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import product.catalog.service.appilication.DTO.ProductDto;
import product.catalog.service.appilication.controller.ProductController;
import product.catalog.service.appilication.model.Product;
import product.catalog.service.appilication.service.ProductService;

import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@WebMvcTest(ProductController.class)
public class ProductControllerMVCTest {

    @Autowired
    MockMvc mockMvc;

    @MockitoBean
    ProductService productService;

    @Autowired
    ObjectMapper objectMapper;

//    @Test
//    public void TestCreateProduct_RunsSuccessfully() throws Exception {
//        mockMvc.perform(post("/products")).andExpect(status().isOk());
//    }

//    @Test
//    public void TestCreateProduct() throws Exception {
//        Product product = new Product();
//        product.setName("Iphone12");
//        product.setId(1L);
//        ProductDto productdto = ProductDto.builder().id(1L).name("Iphone12").build();
//
//
//        when(productService.createProduct(ArgumentMatchers.any())).thenReturn(product);
//
//        mockMvc.perform(post("/products")
//                        .content(objectMapper.writeValueAsString(productdto))
//                        .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andExpect(content().string(objectMapper.writeValueAsString(productdto)))
//                .andExpect(jsonPath("$.id").value(product.getId()))
//                .andExpect(jsonPath("$.length()").value(2));
//
//    }
}
