package tp.microservice.product_service.cqrs.commands;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tp.microservice.product_service.entities.Product;

import java.util.UUID;

@RestController
@RequestMapping("/products")
public class ProductCommandController {
    @Autowired
    private CommandGateway commandGateway;

    @PostMapping
    public String createProduct(@RequestBody Product product) {
        String id = UUID.randomUUID().toString();
        CreateProductCommand command = new CreateProductCommand(
                id, product.getName(), product.getDescription(), product.getPrice(), product.getStock());
        commandGateway.sendAndWait(command);
        return id;
    }
}

