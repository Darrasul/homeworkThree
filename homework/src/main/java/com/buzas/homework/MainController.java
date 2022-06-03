package com.buzas.homework;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class MainController {

    ProductRepository repo;

    @Autowired
    public MainController(ProductRepository repo) {
        this.repo = repo;
    }

    @GetMapping
//    @ResponseBody
    public String showMainPage() {
        return "RestController";
    }

    @GetMapping("/api/v1/products")
    public String showAllProducts(Model model) {

        model.addAttribute("products", repo.getAllProducts());
        return "ProductsTab";
    }

    @GetMapping("/api/v1/products/{id}")
    @ResponseBody
    public Product showProduct(@PathVariable Long id) {
        return repo.getProduct(id);
    }

//    http://localhost:8080/RestController/api/v1/products?name=test
    @PostMapping("/api/v1/products")
    @ResponseBody
    public void createProduct(@RequestParam(defaultValue = "unknown product") String name) {
        repo.addProduct(name);
    }

    @PostMapping("/api/v1/products/stylized")
    @ResponseBody
    public void createStylizedProduct() {
        repo.addStylizedProduct();
    }

    @DeleteMapping("/api/v1/products")
    @ResponseBody
    public void deleteAllProducts() {
         repo.removeAllProduct();
    }

    @DeleteMapping("/api/v1/products/{id}")
    @ResponseBody
    public void deleteProduct(@PathVariable Long id) {
        repo.removeProduct(id);
    }
}
