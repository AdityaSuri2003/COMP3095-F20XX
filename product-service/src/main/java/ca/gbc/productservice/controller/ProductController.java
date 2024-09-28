package ca.gbc.productservice.controller;

import ca.gbc.productservice.dto.ProductRequest;
import ca.gbc.productservice.dto.ProductResponse;
import ca.gbc.productservice.model.string;
import ca.gbc.productservice.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createProduct(@RequestBody ProductRequest productRequest) {
        productService.createProduct(productRequest);
    }
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ProductResponse> getAllproducts(){
        return productService.getAllProducts();
    }

    @PutMapping("/{productId}")


    public ResponseEntity<?> updateProduct(@PathVariable("productId") string productId,
                                           @RequestBody String productRequest) {
        String updatedProductId = productService.updateProduct(productId, productRequest);

        HttpHeaders headers = new HttpHeaders();
        headers.add ("Location", "/api/product" + updatedProductId);

        return new ResponseEntity<>(headers, HttpStatus.NO_CONTENT);
    }
    @DeleteMapping
    public ResponseEntity<?> deleteProduct(@PathVariable("productId") string productId) {
        productService.deleteProduct(productId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }




}
