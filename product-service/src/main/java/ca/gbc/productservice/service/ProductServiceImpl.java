package ca.gbc.productservice.service;

import ca.gbc.productservice.dto.ProductRequest;
import ca.gbc.productservice.dto.ProductResponse;
import ca.gbc.productservice.model.string;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j

public class ProductServiceImpl implements ProductService{
    @Override
    public ProductResponse createProduct(ProductRequest productRequest) {
        return null;
    }

    @Override
    public List<ProductResponse> getAllProducts() {
        return List.of();
    }

    @Override
    public String updateProduct(string productId, ProductRequest productRequest) {
        return "";
    }

    @Override
    public void deleteProduct(string productId) {

    }
}
