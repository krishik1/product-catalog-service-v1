package product.catalog.service.appilication.client;

import lombok.AllArgsConstructor;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import product.catalog.service.appilication.DTO.FakeStoreProductDto;
import product.catalog.service.appilication.model.Product;

import java.util.Arrays;
import java.util.List;

@Component
@AllArgsConstructor
public class ProductServiceClient implements ProductServiceClientI {
    private RestTemplateBuilder restTemplateBuilder;
    private final static String URI = "http://fakestoreapi.com/products";
    @Override
    public FakeStoreProductDto getProductById(Long productId) {
        ResponseEntity<FakeStoreProductDto> responseEntity = requestForEntity(URI +"/{productId}", HttpMethod.GET, null, FakeStoreProductDto.class, productId);
        return validate(responseEntity);
    }

    private FakeStoreProductDto validate(ResponseEntity<FakeStoreProductDto> responseEntity) {
        if(responseEntity.getBody()==null || responseEntity.getStatusCode().equals(HttpStatusCode.valueOf(500))) {
            return null;
        }
        return responseEntity.getBody();
    }


    @Override
    public List<FakeStoreProductDto> getProducts() {
        ResponseEntity<FakeStoreProductDto[]> responseEntity = requestForEntity(URI, HttpMethod.GET, null, FakeStoreProductDto[].class);
        return validateALL(responseEntity);
    }

    @Override
    public FakeStoreProductDto addProduct(FakeStoreProductDto product) {
        ResponseEntity<FakeStoreProductDto> responseEntity = requestForEntity(URI, HttpMethod.POST, product, FakeStoreProductDto.class);
        return validate(responseEntity);
    }

    @Override
    public FakeStoreProductDto replaceProduct(Long productId, FakeStoreProductDto product) {
        ResponseEntity<FakeStoreProductDto> responseEntity = requestForEntity(URI + "/{productId}", HttpMethod.PUT, product, FakeStoreProductDto.class, productId);
        return validate(responseEntity);
    }

    private List<FakeStoreProductDto> validateALL(ResponseEntity<FakeStoreProductDto[]> responseEntity) {
        if(responseEntity.getBody()==null || responseEntity.getStatusCode().equals(HttpStatusCode.valueOf(500))) {
            return null;
        }
        return Arrays.asList(responseEntity.getBody());
    }

    private <T> ResponseEntity<T> requestForEntity(String url, HttpMethod httpMethod,@Nullable Object request, Class<T> responseType, Object... uriVariables) throws RestClientException {
        RestTemplate restTemplate = restTemplateBuilder.build();
        RequestCallback requestCallback = restTemplate.httpEntityCallback(request, responseType);
        ResponseExtractor<ResponseEntity<T>> responseExtractor = restTemplate.responseEntityExtractor(responseType);
        return restTemplate.execute(url, httpMethod, requestCallback, responseExtractor, uriVariables);
    }
}
