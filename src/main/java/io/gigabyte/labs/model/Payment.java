package io.gigabyte.labs.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
public class Payment {
    private int id;
    private PaymentType type;
    private String description;
    private double amount;
    private String currency;
    private String date;
}
