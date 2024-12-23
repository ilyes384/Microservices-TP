package tp.microservice.product_service.cqrs.queries;

import org.axonframework.queryhandling.QueryGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tp.microservice.product_service.entities.Product;

@RestController
@RequestMapping("/products")
public class ProductQueryController {
    @Autowired
    private QueryGateway queryGateway;

    @GetMapping("/{id}")
    public Product getProductById(@PathVariable String id) {
        return queryGateway.query(new GetProductByIdQuery(id), Product.class).join();
    }
}

