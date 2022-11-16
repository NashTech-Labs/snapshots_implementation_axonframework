package com.example.snapshotdemo.api.events;

import com.example.snapshotdemo.api.commands.CreateProductCommand;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductupdatedEvent {

    private String productId;
    private String name;
    private BigDecimal price;
    private Integer quantity;


    public ProductupdatedEvent(String productId) {
    }

    public ProductupdatedEvent(ProductCreatedEvent createProductCommand) {
        this.productId=createProductCommand.getProductId();
    }
}
