package com.my.eshop.web.rest;

import com.my.eshop.domain.Product;
import com.my.eshop.service.ProductService;
import com.my.eshop.transfer.ProductDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/product")
public class ProductController {
    Logger logger = LoggerFactory.getLogger(ProductController.class);
    @Autowired
    private ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public ResponseEntity<List<Product>> getProducts() {
        logger.info("Get list");
        List<Product> list = productService.getProducts();
        if (list == null || list.isEmpty()) {
            return new ResponseEntity<List<Product>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<Product>>(list, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity add(@RequestBody ProductDto product) {
        logger.info("Create new");
        productService.add(product);
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getOneById(@PathVariable("id") Long id) throws Exception {
        logger.info("Get By id {}", id);
        return ResponseEntity.ok(productService.getOneById(id));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Product> update(@PathVariable("id") Long id, @RequestBody ProductDto product) throws Exception {
        logger.info("Update new");
        return ResponseEntity.ok(productService.update(id, product));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable("id") Long id) throws Exception {
        logger.info("Delete new");
        productService.delete(id);
        return new ResponseEntity(HttpStatus.OK);
    }
}
