package io.gigabyte.labs.solid.ocp;

import io.gigabyte.labs.model.Payment;
import io.gigabyte.labs.model.PaymentType;

interface PaymentProcessorIfc {
    void processPayment(Payment payment);
}

class CreditCardPaymentProcessor implements PaymentProcessorIfc {
    public void processPayment(Payment payment) {
        // Lógica específica de tarjeta de crédito
    }
}

class PayPalPaymentProcessor implements PaymentProcessorIfc {
    public void processPayment(Payment payment) {
        // Lógica específica de PayPal
    }
}

class PaymentProcessorFactory {
    public static PaymentProcessorIfc getPaymentProcessor(PaymentType type) {
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

class PaymentService {
    public void processPayment(Payment payment) {
        PaymentProcessorIfc processor = PaymentProcessorFactory.getPaymentProcessor(payment.getType());
        processor.processPayment(payment);
    }
}


public class OCPNonViolationOCP {
}
