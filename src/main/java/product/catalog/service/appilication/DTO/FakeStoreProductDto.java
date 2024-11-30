package product.catalog.service.appilication.DTO;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class FakeStoreProductDto {
    Long id;
    String title;
    Double price;
    String description;
    String image;
    String category;
}
