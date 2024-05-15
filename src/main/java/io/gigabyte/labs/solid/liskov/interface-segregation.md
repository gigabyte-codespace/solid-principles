# **I - Principio de Segregación de la Interfaz (Interface Segregation Principle)**
*   **Definición:** Los clientes no deben ser forzados a depender de interfaces que no usan.
*   **Objetivo:** Promover interfaces delgadas y específicas que no sobrecarguen al implementador con métodos que no necesitan.


El Principio de Segregación de Interfaces (ISP) es uno de los cinco principios SOLID de diseño de software orientado a objetos y establece que ninguna clase debería verse forzada a implementar interfaces que no utiliza. Este principio promueve interfaces más pequeñas y específicas en lugar de una interfaz grande que sirve múltiples propósitos. Veamos cómo aplicar ISP a través de un ejemplo práctico.

## Contexto

Imaginemos que estamos desarrollando un sistema para un centro educativo que maneja tanto empleados como recursos didácticos. En este sistema, necesitamos tanto gestionar las actividades de los empleados (profesores y personal administrativo) como los recursos didácticos (libros, equipos de laboratorio, etc.).

## Diseño Original que Podría Violar ISP

Inicialmente, podríamos tener una interfaz grande que incluya métodos tanto para la gestión de empleados como para la gestión de recursos didácticos:
```java
public interface SchoolManagement {
    void addEmployee(Employee employee);
    void removeEmployee(String employeeId);
    void addResource(Resource resource);
    void removeResource(String resourceId);
    void printEmployeeDetails(String employeeId);
    void printResourceDetails(String resourceId);
}
```

En este diseño, cualquier clase que implemente **SchoolManagement** necesitaría implementar todos estos métodos, incluso si solo necesita manejar empleados o solo recursos, lo que viola ISP.

## Problema

Un objeto que solo necesita gestionar recursos, como **ResourceCoordinator**, no debería tener que implementar métodos relacionados con empleados, y viceversa para un **HumanResources** que solo gestiona empleados.

## Refactorización para Cumplir con ISP

Para adherir a ISP, debemos dividir esta interfaz grande en varias interfaces más pequeñas y especializadas:
```java
public interface EmployeeManagement {
    void addEmployee(Employee employee);
    void removeEmployee(String employeeId);
    void printEmployeeDetails(String employeeId);
}

public interface ResourceManagement {
    void addResource(Resource resource);
    void removeResource(String resourceId);
    void printResourceDetails(String resourceId);
}

```

## Implementación de las Interfaces

Con las interfaces segregadas, diferentes clases pueden implementar solo las interfaces que necesitan:

```java
public class HumanResources implements EmployeeManagement {
    @Override
    public void addEmployee(Employee employee) {
        System.out.println("Adding employee");
    }

    @Override
    public void removeEmployee(String employeeId) {
        System.out.println("Removing employee");
    }

    @Override
    public void printEmployeeDetails(String employeeId) {
        System.out.println("Printing employee details");
    }
}

public class ResourceCoordinator implements ResourceManagement {
    @Override
    public void addResource(Resource resource) {
        System.out.println("Adding resource");
    }

    @Override
    public void removeResource(String resourceId) {
        System.out.println("Removing resource");
    }

    @Override
    public void printResourceDetails(String resourceId) {
        System.out.println("Printing resource details");
    }
}

```

## Beneficios de la Refactorización

*   **Adherencia a ISP:** Las clases ahora solo necesitan implementar las interfaces que utilizan, evitando la necesidad de implementar métodos irrelevantes.
*   **Claridad y Mantenibilidad Mejoradas:** Cada interfaz tiene un propósito claro y bien definido, lo que mejora la legibilidad y la mantenibilidad del código.
*   **Flexibilidad y Escalabilidad:** Con interfaces más pequeñas, es más fácil gestionar cambios y extensiones futuras, ya que los cambios en una interfaz no afectan a las clases que dependen de otras interfaces.
