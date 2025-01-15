package product.catalog.service.appilication.DTO;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class SearchDto {
    private String query;
    private Integer pageSize;
    private Integer pageNumber;
    private List<SortParam> sortParams = new ArrayList<>();
}
