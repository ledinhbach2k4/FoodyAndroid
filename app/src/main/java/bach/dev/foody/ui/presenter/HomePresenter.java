package bach.dev.foody.ui.presenter;

import java.util.List;

import bach.dev.foody.data.api.ApiService;
import bach.dev.foody.data.api.RetrofitClient;
import bach.dev.foody.data.entities.Category;
import bach.dev.foody.data.entities.Product;
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
        Call<List<Product>> call = apiService.getProducts();
        call.enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, retrofit2.Response<List<Product>> response) {
                mView.hideLoading();
                if (response.isSuccessful()){
                    mView.showProducts(response.body());
                } else {
                    mView.showError("Error: " + response.message());
                }
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {
                mView.hideLoading();
                mView.showError("Error: " + t.getMessage());
            }
        });
    }

    @Override
    public void getCategories() {
        // Call API to get categories
        Call<List<Category>> call = apiService.getCategories();
        call.enqueue(new Callback<List<Category>>() {
            @Override
            public void onResponse(Call<List<Category>> call, retrofit2.Response<List<Category>> response) {
                if (response.isSuccessful()){
                    mView.showCategories(response.body());
                } else {
                    mView.showError("Error: " + response.message());
                }
            }

            @Override
            public void onFailure(Call<List<Category>> call, Throwable t) {
                mView.showError("Error: " + t.getMessage());
            }
        });
    }

    @Override
    public void getProductsByCategory(int categoryId) {
        // Call API to get products by category
        Call<List<Product>> call = apiService.getProductsByCategory(categoryId);
        call.enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, retrofit2.Response<List<Product>> response) {
                if (response.isSuccessful()){
                    mView.showProducts(response.body());
                } else {
                    mView.showError("Error: " + response.message());
                }
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {
                mView.showError("Error: " + t.getMessage());
            }
        });
    }
}
