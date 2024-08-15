package exercise.repository;

import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import exercise.model.Product;

import org.springframework.data.domain.Sort;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    Optional<Product> findByPrice(Integer price);
    List<Product> findAllByPrice(Integer price);
    List<Product> findByPriceGreaterThanOrderByPrice(Integer price);

    List<Product> findByPriceLessThanOrderByPrice(Integer price);
    List<Product> findByPriceBetweenOrderByPrice(Integer minPrice, Integer maxPrice);
}
