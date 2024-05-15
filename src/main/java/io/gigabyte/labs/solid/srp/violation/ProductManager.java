package io.gigabyte.labs.solid.srp.violation;

import io.gigabyte.labs.model.Product;
import io.gigabyte.labs.model.User;
import io.gigabyte.labs.repository.DatabaseConnection;
import io.gigabyte.labs.service.EmailService;
import io.gigabyte.labs.service.LogService;

public class ProductManager {
    private DatabaseConnection database;
    private EmailService emailService;
    private LogService logService;

    public ProductManager(DatabaseConnection database, EmailService emailService, LogService logService) {
        this.database = database;
        this.emailService = emailService;
        this.logService = logService;
    }

    public void addProduct(Product product, User createdBy) {
        if (validateProduct(product)) {
            saveProductToDatabase(product);
            logService.log("Product added: " + product.getId());
            emailService.sendEmail(createdBy.getEmail(), "Product Added", "A new product has been added: " + product.getName());
        }
    }

    private boolean validateProduct(Product product) {
        return product.getPrice() > 0 && product.getQuantity() >= 0 && product.getCategory() != null;
    }

    private void saveProductToDatabase(Product product) {
        database.execute("INSERT INTO products ...");
        // Assume more complex SQL handling, possibly involving transactions or multiple tables
    }

    // Additional methods for removing products, updating products, etc.
}