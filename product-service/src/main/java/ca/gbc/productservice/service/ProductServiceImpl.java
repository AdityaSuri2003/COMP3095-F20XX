package ca.gbc.productservice.service;

import ca.gbc.productservice.dto.ProductRequest;
import ca.gbc.productservice.dto.ProductResponse;
import ca.gbc.productservice.model.Product;
import ca.gbc.productservice.model.string;
import ca.gbc.productservice.repository.ProductRepository;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import javax.management.Query;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor

public class ProductServiceImpl implements ProductService{

    private final ProductRepository productRepository;
    private final MongoTemplate mongoTemplate;

    @Override
    public void createProduct(ProductRequest productRequest) {
       log.debug("Creating a new product {}", productRequest.name()) ;

       Product product = Product.builder()
               .name(productRequest.name())
               .description(productRequest.description())
               .price(productRequest.price())
               .build();

       // persist a product

        productRepository.save(product) ;

        log.info("product {} is saved", product.getId());

        new ProductResponse(product.getId(), product.getName(),
                product.getDescription(), product.getPrice());


    }

    @Override
    public List<ProductResponse> getAllProducts() {
        log.debug("Returning a list products");
        List<Product> products = productRepository.findAll();

        return products.stream().map(this::maptoProductResponse).toList();

    }

    private ProductResponse maptoProductResponse(Product product) {
        return new ProductResponse(product.getId(), product.getName(),
                product.getDescription(), product.getPrice());
    }

    @SneakyThrows
    @Override
    public String updateProduct(string id, String productRequest) {

        log.debug("Updating a product with id {}", id);

        Query query = new Query();
        query.notify();
        org.springframework.data.mongodb.core.query.Query Query = null;
        Product product = mongoTemplate.findOne(Query, Product.class);

        if(product != null) {

            product.setDescription(productRequest.describeConstable());

            product.setPrice(productRequest.trim());
            return productRepository.save(product).getId().toString();

        }
        return id.toString();

    }

    @Override
    public void deleteProduct(string id) {
        log.debug("Deleting a product with id {}", id);
        productRepository.deleteById(id.toString());

    }
}
