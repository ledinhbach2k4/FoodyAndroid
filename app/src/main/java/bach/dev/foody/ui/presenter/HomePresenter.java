package bach.dev.foody.ui.presenter;

import java.util.List;

import bach.dev.foody.data.api.ApiService;
import bach.dev.foody.data.api.RetrofitClient;
import bach.dev.foody.data.dto.CategoryDto;
import bach.dev.foody.data.dto.ProductDto;
import bach.dev.foody.ui.constract.HomeConstract;
import retrofit2.Call;
import retrofit2.Callback;

public class HomePresenter implements HomeConstract.Presenter {
    private HomeConstract.View mView;
    ApiService apiService = RetrofitClient.getApiService();
    @Override
    public void setView(HomeConstract.View view) {
        mView = view;
    }

    @Override
    public void getProducts() {
        mView.showLoading();
        // Call API to get products
        Call<List<ProductDto>> call = apiService.getProducts();
        call.enqueue(new Callback<List<ProductDto>>() {
            @Override
            public void onResponse(Call<List<ProductDto>> call, retrofit2.Response<List<ProductDto>> response) {
                mView.hideLoading();
                if (response.isSuccessful()){
                    mView.showProducts(response.body());
                } else {
                    mView.showError("Error: " + response.message());
                }
            }

            @Override
            public void onFailure(Call<List<ProductDto>> call, Throwable t) {
                mView.hideLoading();
                mView.showError("Error: " + t.getMessage());
            }
        });
    }

    @Override
    public void getProductsByProperty(String property, String order) {
        // Call API to get products by property
        mView.showLoading();
        Call<List<ProductDto>> call = apiService.getProductsByProperty(property, order);
        call.enqueue(new Callback<List<ProductDto>>() {
            @Override
            public void onResponse(Call<List<ProductDto>> call, retrofit2.Response<List<ProductDto>> response) {
                mView.hideLoading();
                if (response.isSuccessful()){
                    mView.showProducts(response.body());
                } else {
                    mView.showError("Error: " + response.message());
                }
            }

            @Override
            public void onFailure(Call<List<ProductDto>> call, Throwable t) {
                mView.hideLoading();
                mView.showError("Error: " + t.getMessage());
            }
        });
    }

    @Override
    public void getCategories() {
        // Call API to get categories
        Call<List<CategoryDto>> call = apiService.getCategories();
        call.enqueue(new Callback<List<CategoryDto>>() {
            @Override
            public void onResponse(Call<List<CategoryDto>> call, retrofit2.Response<List<CategoryDto>> response) {
                if (response.isSuccessful()){
                    mView.showCategories(response.body());
                } else {
                    mView.showError("Error: " + response.message());
                }
            }

            @Override
            public void onFailure(Call<List<CategoryDto>> call, Throwable t) {
                mView.showError("Error: " + t.getMessage());
            }
        });
    }

    @Override
    public void getProductsByCategory(int categoryId) {
        // Call API to get products by category
        Call<List<ProductDto>> call = apiService.getProductsByCategory(categoryId);
        call.enqueue(new Callback<List<ProductDto>>() {
            @Override
            public void onResponse(Call<List<ProductDto>> call, retrofit2.Response<List<ProductDto>> response) {
                if (response.isSuccessful()){
                    mView.showProducts(response.body());
                } else {
                    mView.showError("Error: " + response.message());
                }
            }

            @Override
            public void onFailure(Call<List<ProductDto>> call, Throwable t) {
                mView.showError("Error: " + t.getMessage());
            }
        });
    }

    @Override
    public void searchProduct(String keyword) {
        // Call API to search products
        mView.showLoading();
        Call<List<ProductDto>> call = apiService.searchProducts(keyword);
        call.enqueue(new Callback<>() {
            @Override
            public void onResponse(Call<List<ProductDto>> call, retrofit2.Response<List<ProductDto>> response) {
                if (response.isSuccessful()) {
                    mView.showProducts(response.body());
                } else {
                    mView.showError("Error: " + response.message());
                }
                mView.hideLoading();
            }

            @Override
            public void onFailure(Call<List<ProductDto>> call, Throwable t) {
                mView.showError("Error: " + t.getMessage());
                mView.hideLoading();
            }
        });
    }
}
