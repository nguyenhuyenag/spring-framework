package com.crud.controller;

import com.crud.entity.Product;
import com.crud.service.ProductService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;


@Slf4j
@AutoConfigureMockMvc
@SpringBootTest // Tìm kiếm class có đánh dấu @SpringBootApplication -> nạp toàn bộ bean vào context
public class ProductControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    ProductService service;

    private Product productRequest;
    private Product productResponse;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    void setUp() {
        // @formatter:off
        productRequest = Product.builder()
                .name("Test Product")
                .quantity(10)
                .price(99.99)
                .build();
        productResponse = Product.builder()
                .id(1)
                .name("Test Product")
                .quantity(10)
                .price(99.99)
                .build();
        // @formatter:on
    }

    @Test
    void addProduct_success() throws Exception {
        log.info("Test addProduct");

        // Given
        String body = objectMapper.writeValueAsString(productRequest);

        // When: Giả lập, khi gọi service.saveProduct() thì sẽ trả về -> productResponse thay vì gọi vào service.saveProduct() thật
        Mockito.when(service.saveProduct(ArgumentMatchers.any())) //
                .thenReturn(productResponse);

        // When: Giả lập request vào /addProduct
        ResultActions callRequest = mockMvc.perform(MockMvcRequestBuilders //
                .post("/addProduct") //
                .contentType(MediaType.APPLICATION_JSON) //
                .content(body));

        // Then
        callRequest.andDo(print())   // in toàn bộ request/response ra console
                // expect status = 200
                .andExpect(MockMvcResultMatchers.status().isOk())
                // expect error_code = 0
                .andExpect(MockMvcResultMatchers.jsonPath("$.error_code").value(0))
                // expect message = "success"
                .andExpect(MockMvcResultMatchers.jsonPath("$.message").value("success"))
                // expect result.name = "Test Product"
                .andExpect(MockMvcResultMatchers.jsonPath("$.result.name").value(productResponse.getName()))
        ;
    }

}
