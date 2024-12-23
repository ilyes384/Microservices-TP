package tp.microservice.product_service.cqrs.commands;

import lombok.Getter;
import org.axonframework.modelling.command.TargetAggregateIdentifier;
@Getter
public class CreateProductCommand {
    @TargetAggregateIdentifier
    private final String id;
    private final String name;
    private final String description;
    private final double price;
    private final int stock;

    public CreateProductCommand(String id, String name, String description, double price, int stock) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.stock = stock;
    }

    // Getters
}
