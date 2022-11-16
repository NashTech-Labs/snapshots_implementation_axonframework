package com.example.snapshotdemo.api.aggregate;



import com.example.snapshotdemo.api.commands.CreateProductCommand;
import com.example.snapshotdemo.api.events.ProductCreatedEvent;
import com.example.snapshotdemo.api.events.ProductupdatedEvent;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;


import java.math.BigDecimal;

@Aggregate(snapshotTriggerDefinition = "AggregateSnapshotTrigger")
public class ProductAggregate {

    @AggregateIdentifier
    private String productId;
    private String name;
    private BigDecimal price;
    private Integer quantity;

    public ProductAggregate() {
    }

    @CommandHandler
    public ProductAggregate(CreateProductCommand createProductCommand) {

        AggregateLifecycle.apply( new ProductCreatedEvent(createProductCommand
        ));
    }
    @EventSourcingHandler
    public void on(ProductCreatedEvent productCreatedEvent) {
        System.out.println("product created");
        this.quantity = productCreatedEvent.getQuantity();
        this.price = productCreatedEvent.getPrice();
        this.name = productCreatedEvent.getName();
        this.productId = productCreatedEvent.getProductId();
        AggregateLifecycle.apply(new ProductupdatedEvent(productCreatedEvent));
    }

    @EventSourcingHandler
    public void on(ProductupdatedEvent productUpdateEvent) {
        System.out.println("product created1");
        this.quantity = productUpdateEvent.getQuantity();
        this.price = productUpdateEvent.getPrice();
        this.name = productUpdateEvent.getName();
        this.productId = productUpdateEvent.getProductId();
    }

}
