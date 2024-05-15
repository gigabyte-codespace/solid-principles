# **L - Principio de Sustitución de Liskov (Liskov Substitution Principle)**
*   **Definición:** Los objetos de una superclase deben ser sustituibles por objetos de sus subclases sin alterar la corrección del programa.
*   **Objetivo:** Promover la consistencia y evitar errores al reemplazar una instancia de superclase con una de sus subclases.

## Contexto

Imagina un sistema de gestión de contenido digital donde los usuarios pueden cargar, visualizar y modificar diferentes tipos de medios. Estos medios podrían incluir imágenes, videos y, potencialmente, archivos de audio.

## Diseño Original que Podría Violar LSP

En una primera implementación, podríamos tener una clase base **Media** con métodos comunes y algunas subclases que implementan comportamientos específicos de cada tipo de medio:
```java
public abstract class Media {
    public abstract void display();
    public abstract void resize(int width, int height);
}

public class Image extends Media {
    @Override
    public void display() {
        System.out.println("Displaying image");
    }

    @Override
    public void resize(int width, int height) {
        System.out.println("Resizing image to width: " + width + ", height: " + height);
    }
}

public class Video extends Media {
    @Override
    public void display() {
        System.out.println("Playing video");
    }

    @Override
    public void resize(int width, int height) {
        System.out.println("Changing video resolution to width: " + width + ", height: " + height);
    }
}
```
## Problema

Aunque a primera vista parece que no hay problema, si introducimos un tipo de medio que naturalmente no debería ser redimensionable, como un archivo de audio, podríamos enfrentar una violación del LSP si forzamos a **Audio** a implementar **resize**, lo que no tiene sentido para ese tipo de medio:

```java
public class Audio extends Media {
    @Override
    public void display() {
        System.out.println("Playing audio");
    }

    @Override
    public void resize(int width, int height) {
        throw new UnsupportedOperationException("Cannot resize audio");
    }
}

```

## Refactorización para Cumplir con LSP

Para adherir a LSP, necesitamos asegurarnos de que cada subclase pueda ser utilizada en lugar de la clase base sin requerir cambios en el comportamiento esperado. Podemos refactorizar utilizando interfaces para separar los comportamientos que no son comunes a todos los tipos de medios:

```java
public interface Displayable {
    void display();
}

public interface Resizable {
    void resize(int width, int height);
}

public class Image implements Displayable, Resizable {
    public void display() {
        System.out.println("Displaying image");
    }

    public void resize(int width, int height) {
        System.out.println("Resizing image to width: " + width + ", height: " + height);
    }
}

public class Video implements Displayable, Resizable {
    public void display() {
        System.out.println("Playing video");
    }

    public void resize(int width, int height) {
        System.out.println("Changing video resolution to width: " + width + ", height: " + height);
    }
}

public class Audio implements Displayable {
    public void display() {
        System.out.println("Playing audio");
    }
}

```

## Beneficios de la Refactorización

*   **Cumplimiento de LSP:** Cada clase ahora solo implementa las interfaces que corresponden a las operaciones que realmente puede realizar, evitando el problema de tener que implementar métodos que no tienen sentido para ciertos tipos de medios.
*   **Mayor Flexibilidad y Escalabilidad:** Nuevos tipos de medios pueden ser añadidos fácilmente, implementando solo las interfaces pertinentes sin afectar las expectativas de las operaciones de otros tipos de medios.
*   **Claridad y Mantenibilidad:** Separando las capacidades de visualización y redimensionamiento, el sistema se vuelve más claro y más fácil de mantener.
