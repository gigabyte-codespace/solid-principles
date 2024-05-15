package io.gigabyte.labs.solid.ocp.violation;

import io.gigabyte.labs.model.Payment;
import io.gigabyte.labs.model.PaymentType;

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