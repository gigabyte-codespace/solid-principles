package io.gigabyte.labs.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Product {
    private Color color;
    private Size size;
    private String id;
    private String name;
    private String description;
    private String image;
    private String category;
    private float price;
    private short quantity;

    public Product(String name, Color color, Size size) {
        this.name = name;
        this.color = color;
        this.size = size;
    }
}
