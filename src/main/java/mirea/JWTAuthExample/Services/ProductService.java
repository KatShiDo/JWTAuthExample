package mirea.JWTAuthExample.Services;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import mirea.JWTAuthExample.Domain.Product;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
@RequiredArgsConstructor
public class ProductService {

    List<Product> productList = null;

    @PostConstruct
    public void loadProductsFromDB() {
        productList = IntStream
                .rangeClosed(1, 100)
                .mapToObj(i -> Product
                        .builder()
                        .id(i)
                        .name("product" + i)
                        .qty(new Random().nextInt(10))
                        .price(new Random().nextInt(5000))
                        .build()
                ).collect(Collectors.toList());
    }

    public List<Product> getAll() {
        return productList;
    }

    public Product getById(int id) {
        return productList
                .stream()
                .filter(product -> product.getId() == id)
                .findAny()
                .orElseThrow(() -> new RuntimeException("product " + id + " not found"));
    }
}
