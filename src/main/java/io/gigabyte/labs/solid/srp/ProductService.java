package io.gigabyte.labs.solid.srp;

import io.gigabyte.labs.model.Product;
import io.gigabyte.labs.model.User;
import io.gigabyte.labs.repository.ProductRepository;
import io.gigabyte.labs.service.LoggingService;
import io.gigabyte.labs.service.NotificationService;

public class ProductService {
    private ProductRepository accesoBD;
    private LoggingService logger;
    private NotificationService notifier;

    public ProductService(ProductRepository repository, LoggingService logger, NotificationService notifier) {
        this.accesoBD = repository;
        this.logger = logger;
        this.notifier = notifier;
    }

    public void addProduct(Product product, User createdBy) {
        if (accesoBD.validateProduct(product)) {
            accesoBD.saveProduct(product);
            logger.log("Product added: " + product.getId());
            notifier.sendNotification(createdBy.getEmail(), "Product Added", "A new product has been added: " + product.getName());
        }
    }
}