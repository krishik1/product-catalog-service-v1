package product.catalog.service.appilication.model;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.List;
@Getter
@Setter
@SuperBuilder
public class Category extends BaseModel {
    private String name;
    private String description;
    private List<Product> products;
}
