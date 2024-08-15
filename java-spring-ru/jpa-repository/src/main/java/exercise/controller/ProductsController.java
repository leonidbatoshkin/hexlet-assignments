package exercise.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.data.domain.Sort;

import java.util.List;

import exercise.model.Product;
import exercise.repository.ProductRepository;
import exercise.exception.ResourceNotFoundException;

@RestController
@RequestMapping("/products")
public class ProductsController {

    @Autowired
    private ProductRepository productRepository;

    @GetMapping(path = "")
    @ResponseStatus(HttpStatus.OK)
    public List<Product> index(@RequestParam(required = false) Integer min, @RequestParam(required = false) Integer max) {
        if (min != null && max != null) {
            return productRepository.findByPriceBetweenOrderByPrice(min, max);
        } else if (min != null) {
            return productRepository.findByPriceGreaterThanOrderByPrice(min);
        } else if (max != null) {
            return productRepository.findByPriceLessThanOrderByPrice(max);
        }
        return productRepository.findAll();
    }


    @GetMapping(path = "/{id}")
    public Product show(@PathVariable long id) {

        var product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product with id " + id + " not found"));

        return product;
    }
}
