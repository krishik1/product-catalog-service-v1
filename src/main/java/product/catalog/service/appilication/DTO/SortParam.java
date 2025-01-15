package product.catalog.service.appilication.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class SortParam {
    private SortType sortType;
    private String sortCriteria;


}
