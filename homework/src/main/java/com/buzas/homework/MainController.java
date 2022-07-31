package com.buzas.homework;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
public class MainController {

    ProductRepository repo;

    @Autowired
    public MainController(ProductRepository repo) {
        this.repo = repo;
    }

//    http://localhost:8080/RestController/
    @GetMapping
    public ModelAndView showMainPage() {
        return new ModelAndView("MainPage");
    }

    @GetMapping("/api/v1/products")
    public List<Product> showAllProducts() {
        return repo.getAllProducts();
    }

    @GetMapping("/api/v1/products/{id}")
    @ResponseBody
    public Product showProduct(@PathVariable Long id) {
        return repo.getAllProducts().stream().filter(p -> p.getId().equals(id)).findFirst().orElseThrow(() -> new RuntimeException("Product with this id is not exists"));
    }

//    http://localhost:8080/RestController/api/v1/products?name=test
    @PostMapping("/api/v1/products")
    public void createProduct(@RequestParam(defaultValue = "unknown product") String name) {
        repo.addProduct(name);
    }

    @PostMapping("/api/v1/products/stylized")
    public void createStylizedProduct() {
        repo.addStylizedProduct();
    }

    @DeleteMapping("/api/v1/products")
    public void deleteAllProducts() {
         repo.removeAllProduct();
    }

    @DeleteMapping("/api/v1/products/{id}")
    public void deleteProduct(@PathVariable Long id) {
        repo.removeProduct(id);
    }
}
