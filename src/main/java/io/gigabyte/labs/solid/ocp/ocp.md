# **OCP - Principio de Abierto/Cerrado (Open/Closed Principle)**
*   **Definición:** Las entidades de software (clases, módulos, funciones, etc.) deben estar abiertas para extensión, pero cerradas para modificación.
*   **Objetivo:** Permitir que el comportamiento de una entidad pueda ser extendido sin modificar su código fuente.

Principio de Abierto/Cerrado (Open/Closed Principle, OCP) con un ejemplo más complejo, adecuado para desarrolladores de nivel mid-senior a senior. Este principio es fundamental en diseño de software y sostiene que las entidades de software (como clases, módulos, funciones, etc.) deben estar abiertas para la extensión, pero cerradas para la modificación. Esto significa que deberías poder agregar nuevas funcionalidades sin cambiar el código existente.

## Contexto

Consideremos un sistema de procesamiento de pagos donde diferentes tipos de pagos deben ser procesados, tales como tarjetas de crédito, PayPal, y transferencias bancarias. Inicialmente, se podría tener una implementación que verifica el tipo de pago y ejecuta el código correspondiente basado en esa verificación. Sin embargo, este diseño viola el OCP porque cada vez que se añade un nuevo método de pago, el código existente debe modificarse.

## Ejemplo Original Violando OCP

Aquí tienes cómo podría verse el código inicial que viola OCP:
```java
public class PaymentProcessor {
    public void processPayment(Payment payment) {
        if (payment.getType() == PaymentType.CREDIT_CARD) {
            processCreditCardPayment(payment);
        } else if (payment.getType() == PaymentType.PAYPAL) {
            processPayPalPayment(payment);
        }
        // Cada vez que se añade un nuevo método de pago, se modifica este método.
    }

    private void processCreditCardPayment(Payment payment) {
        // Lógica específica de tarjeta de crédito
    }

    private void processPayPalPayment(Payment payment) {
        // Lógica específica de PayPal
    }
}

```
## Refactorización Cumpliendo OCP

Para adherir al OCP, podemos refactorizar este diseño utilizando el patrón de estrategia, que permite cambiar el comportamiento de un algoritmo en tiempo de ejecución. Vamos a definir una interfaz de procesamiento de pago y clases concretas para cada tipo de pago.

```java
public interface PaymentProcessor {
    void processPayment(Payment payment);
}

public class CreditCardPaymentProcessor implements PaymentProcessor {
    public void processPayment(Payment payment) {
        // Lógica específica de tarjeta de crédito
    }
}

public class PayPalPaymentProcessor implements PaymentProcessor {
    public void processPayment(Payment payment) {
        // Lógica específica de PayPal
    }
}

public class PaymentProcessorFactory {
    public static PaymentProcessor getPaymentProcessor(PaymentType type) {
        switch (type) {
            case CREDIT_CARD:
                return new CreditCardPaymentProcessor();
            case PAYPAL:
                return new PayPalPaymentProcessor();
            default:
                throw new IllegalArgumentException("Unsupported payment type");
        }
    }
}

```

```java
public class PaymentService {
    public void processPayment(Payment payment) {
        PaymentProcessor processor = PaymentProcessorFactory.getPaymentProcessor(payment.getType());
        processor.processPayment(payment);
    }
}

```
## Beneficios de esta Refactorización

*   **Extensibilidad:** Se pueden agregar nuevos procesadores de pagos sin modificar el código existente, solo añadiendo nuevas clases que implementen **PaymentProcessor**.
*   **Cumplimiento de OCP:** La clase **PaymentService** y los procesadores de pagos ahora cumplen con el OCP, ya que las modificaciones futuras no requieren cambios en estas clases.
*   **Separación de preocupaciones:** Cada clase tiene una sola responsabilidad, facilitando la mantenibilidad y las pruebas.
