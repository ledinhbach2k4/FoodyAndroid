package bach.dev.foody.data.api;

import java.util.List;

import bach.dev.foody.data.entities.CategoryDto;
import bach.dev.foody.data.entities.ProductDto;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ApiService {
    @GET("categories")
    Call<List<CategoryDto>> getCategories();
    @GET("products")
    Call<List<ProductDto>> getProducts();
    @GET("products/category/{id}")
    Call<List<ProductDto>> getProductsByCategory(@Path("id") int categoryId);


    @GET("products/{id}")
    Call<ProductDto> getProduct(@Path("id") int id);
}
