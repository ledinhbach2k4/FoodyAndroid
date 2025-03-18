package bach.dev.foody.data.api;

import java.util.List;

import bach.dev.foody.data.dto.CategoryDto;
import bach.dev.foody.data.dto.OrderItemDto;
import bach.dev.foody.data.dto.ProductDto;
import bach.dev.foody.data.dto.UserDto;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiService {
    @GET("categories")
    Call<List<CategoryDto>> getCategories();
    @GET("products")
    Call<List<ProductDto>> getProducts();
    @GET("products/search")
    Call<List<ProductDto>> searchProducts(@Query("keyword") String keyword);
    @GET("products/category/{id}")
    Call<List<ProductDto>> getProductsByCategory(@Path("id") int categoryId);


    @GET("products/{id}")
    Call<ProductDto> getProduct(@Path("id") int id);

    /**
     * Api for users
     */
    @POST("auth/login")
    Call<UserDto> login(@Body UserDto userDto);

    /**
     * Api cart
     */

    @POST("/cart/add-to-cart")
    Call<OrderItemDto> addToCart(
            @Header("User-Id") int userId,
            @Body OrderItemDto orderItemDto);
}
