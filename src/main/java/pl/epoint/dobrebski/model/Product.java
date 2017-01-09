package pl.epoint.dobrebski.model;

import lombok.Data;

import java.math.BigDecimal;

/**
 * Created by dobrebski on 04.01.17.
 */
@Data
public class Product {
    private Integer PK;
    private String name;
    private Integer quantity;
    private BigDecimal price;
}
