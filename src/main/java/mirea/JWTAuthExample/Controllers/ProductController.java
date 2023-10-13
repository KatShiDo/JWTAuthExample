package mirea.JWTAuthExample.Controllers;

import lombok.RequiredArgsConstructor;
import mirea.JWTAuthExample.Domain.Product;
import mirea.JWTAuthExample.Domain.User;
import mirea.JWTAuthExample.Services.ProductService;
import mirea.JWTAuthExample.Services.UserService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping("/welcome")
    public String welcome() {
        return "Welcome this endpoint is not secure";
    }

    @GetMapping("/all")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public List<Product> getAllProducts() {
        return productService.getAll();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public Product getProductById(@PathVariable int id) {
        return productService.getById(id);
    }
}
