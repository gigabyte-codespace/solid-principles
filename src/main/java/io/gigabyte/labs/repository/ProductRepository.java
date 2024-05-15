package io.gigabyte.labs.repository;

import io.gigabyte.labs.model.Product;

public class ProductRepository {
    public boolean validateProduct(Product product) {
        return false;
    }

    public void saveProduct(Product product) {
        System.out.println("Saving product: " + product);
    }
}
