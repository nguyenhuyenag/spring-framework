package com.feignclient;

import java.net.URI;
import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import com.model.Product;

@FeignClient(value = "productsBlocking", url = "http://localhost:8080")
public interface ProductsFeignClient {

	@GetMapping(value = "/slow-service-products", produces = "application/json")
	List<Product> getProductsBlocking(URI baseUrl);

}
