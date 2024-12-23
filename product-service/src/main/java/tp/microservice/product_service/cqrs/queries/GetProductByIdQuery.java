package tp.microservice.product_service.cqrs.queries;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GetProductByIdQuery {
    private final String id;

    public GetProductByIdQuery(String id) {
        this.id = id;
    }

}

