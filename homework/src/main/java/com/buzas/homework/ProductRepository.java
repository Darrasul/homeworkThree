package com.buzas.homework;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Component
public class ProductRepository {
        List<Product> products;

        @PostConstruct
        public void initialize() {
            this.products = new ArrayList<>();
            fillingList();
        }

        private void fillingList() {
            for (int i = 0; i < 5; i++) {
                products.add(new Product((long) i, "Product № " + (i + 1)));
            }
        }

        public void addProduct(Product product){
            products.add(product);
        }

        public void addProduct(String name){
            if (products.size() != 0){
                long lastNumber = products.get(products.size() - 1).getId();
                Product product = new Product(lastNumber + 1, name);
                products.add(product);
            } else {
                Product product = new Product(0L, name);
                products.add(product);
            }
        }

        public void addStylizedProduct(){
            if (products.size() != 0){
                long lastNumber = products.get(products.size() - 1).getId();
                Product product = new Product(lastNumber + 1, "Product № " + ((int)lastNumber + 2));
                products.add(product);
            } else {
                Product product = new Product(0L, "Product № " + 1);
                products.add(product);
            }
        }

        public void removeProduct(Long id){
            products.removeIf(n -> (n.getId().equals(id)));
        }

        public void removeAllProduct() {
            products.clear();
        }

        public List<Product> getAllProducts() {
            return Collections.unmodifiableList(products);
        }
}
