package ca.gbc.productservice.service;

import ca.gbc.productservice.dto.ProductRequest;
import ca.gbc.productservice.dto.ProductResponse;
import ca.gbc.productservice.model.string;

import java.util.List;

public interface ProductService {
    void createProduct(ProductRequest productRequest);
    List<ProductResponse> getAllProducts();
    String updateProduct(string productId, String productRequest);
    void deleteProduct(string productId);
}
