package product.catalog.service.appilication.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import product.catalog.service.appilication.DTO.SortParam;
import product.catalog.service.appilication.DTO.SortType;
import product.catalog.service.appilication.model.Product;
import product.catalog.service.appilication.repository.ProductRepo;

import java.util.List;
@Service
public class SearchProductService implements  ISearchService {
    @Autowired
    ProductRepo productRepo;

    @Override
    public Page<Product> searchProducts(String query, Integer pageSize, Integer pageNumber, List<SortParam> sortParams) {
        Sort sort=null;
        if(!sortParams.isEmpty()) {
            if(sortParams.get(0).getSortType().equals(SortType.ASC)) {
                sort = sort.by(sortParams.get(0).getSortCriteria());
            } else {
                sort = sort.by(sortParams.get(0).getSortCriteria()).descending();
            }
        }
        for(int i=1;i<sortParams.size();i++) {
            if(sortParams.get(i).getSortType().equals(SortType.ASC)) {
                sort = sort.and(Sort.by(sortParams.get(i).getSortCriteria()));
            }else {
                sort = sort.and(Sort.by(sortParams.get(i).getSortCriteria()).descending());
            }
        }
        if(sort!=null) {
            return productRepo.findAll(PageRequest.of(pageNumber,pageSize,sort));
        } else {
            return productRepo.findAll(PageRequest.of(pageNumber,pageSize));
        }

    }
}
