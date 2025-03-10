package bach.dev.foody.data.api;

import java.util.List;

import bach.dev.foody.data.entities.Category;
import bach.dev.foody.data.entities.Product;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ApiService {
    @GET("categories")
    Call<List<Category>> getCategories();
    @GET("products")
    Call<List<Product>> getProducts();
    @GET("products/category/{id}")
    Call<List<Product>> getProductsByCategory(@Path("id") int categoryId);


    @GET("products/{id}")
    Call<Product> getProduct(@Path("id") int id);
}
