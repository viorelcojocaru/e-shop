package com.my.eshop.service;

import com.my.eshop.domain.Product;
import com.my.eshop.repository.ProductRepository;
import com.my.eshop.transfer.ProductDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    private ProductRepository repository;
    @Autowired
    private ModelMapper modelMapper;

    public ProductService(ProductRepository repository, ModelMapper modelMapper) {
        this.repository = repository;
        this.modelMapper = modelMapper;
    }

    public List<Product> getProducts() {
        return repository.findAll();
    }

    public void add(ProductDto dto) {
        Product product = modelMapper.map(dto, Product.class);
        repository.save(product);
    }

    public Product update(Long id, ProductDto dto) throws Exception {
        Optional<Product> optional = repository.findById(id);
        if (!optional.isPresent()) {
            throw new Exception("Not found product");
        } else {
            Product product = modelMapper.map(dto, Product.class);
            product.setId(id);
            return repository.save(product);
        }
    }

    public void delete(Long id) throws Exception {
        Optional<Product> optional = repository.findById(id);
        if (optional.isPresent()) {
            repository.deleteById(id);
        } else
            throw new Exception("Not found product");
    }

    public Product getOneById(Long id) throws Exception {
        Optional<Product> optional = repository.findById(id);
        if (!optional.isPresent()) {
            throw new Exception("Not found product");
        } else
            return optional.get();
    }
}
