package product.catalog.service.appilication.service;

import org.springframework.data.domain.Page;
import product.catalog.service.appilication.DTO.SortParam;
import product.catalog.service.appilication.model.Product;

import java.util.List;

public interface ISearchService {
    Page<Product> searchProducts(String query, Integer pageSize, Integer pageNumber, List<SortParam> sortParams);
}
