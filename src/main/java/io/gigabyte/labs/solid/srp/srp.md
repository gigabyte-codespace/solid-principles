# SRP - Single Responsability Principle

## **SRP - Principio de Responsabilidad Única (Single Responsibility Principle)**
*   **Definición:** Una clase debe tener una, y solo una, razón para cambiar, lo que significa que una clase debe tener solo una tarea o responsabilidad.
*   **Objetivo:** Minimizar la interdependencia y aumentar la cohesión entre los módulos del software.


### Ejemplo
Supongamos que estamos trabajando en un sistema empresarial para la gestión de inventarios que no solo maneja productos,
sino también las categorías de los productos y su relación con los proveedores.
La gestión del inventario es crítica y necesita integrarse con otros sistemas, como sistemas de contabilidad y CRM.

#### Clase Product Manager
```java
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

```
### Problemas con Este Diseño
1. Violación de SRP: La clase ProductManager maneja la lógica de negocio de productos, la interacción con la base de datos, logging, y notificaciones por correo electrónico.
2. Alta dependencia: Cualquier cambio en las dependencias (como EmailService o LogService) puede requerir cambios en ProductManager.
3. Pruebas difíciles: Mockear todas las dependencias y asegurar que interactúan correctamente entre sí complica las pruebas.

### Refactorización Avanzada Utilizando SRP y Patrones de Diseño
1. Dividir `ProductManager` en múltiples clases:
- `ProductService`: Maneja solo la lógica de negocio relacionada con productos.
- `ProductRepository`: Encargado exclusivamente de la interacción con la base de datos para productos.
- `LoggingService`: Servicio independiente para manejar el logging.
- `NotificationService`: Servicio independiente para manejar notificaciones, como correos electrónicos.

```java
public class ProductService {
    private ProductRepository repository;
    private LoggingService logger;
    private NotificationService notifier;

    public ProductService(ProductRepository repository, LoggingService logger, NotificationService notifier) {
        this.repository = repository;
        this.logger = logger;
        this.notifier = notifier;
    }

    public void addProduct(Product product, User createdBy) {
        if (repository.validateProduct(product)) {
            repository.saveProduct(product);
            logger.log("Product added: " + product.getId());
            notifier.sendNotification(createdBy.getEmail(), "Product Added", "A new product has been added: " + product.getName());
        }
    }
}

```

### Beneficios de Esta Refactorización

*   **Cohesión Mejorada:** Cada servicio o clase tiene una única responsabilidad.
*   **Facilidad de Mantenimiento y Escalabilidad:** Los cambios en un área específica del código afectan solo a esa área.
*   **Mejor capacidad de prueba:** Cada componente puede ser probado de forma aislada.
*   **Flexibilidad:** Los servicios pueden ser reemplazados o modificados independientemente unos de otros.


Este enfoque no solo cumple con SRP, sino que también introduce un diseño más modular y escalable, ideal para sistemas complejos y entornos de desarrollo que involucran múltiples equipos o servicios.