package bach.dev.foody.ui.presenter;

import bach.dev.foody.data.api.ApiService;
import bach.dev.foody.data.api.RetrofitClient;
import bach.dev.foody.data.entities.Product;
import bach.dev.foody.ui.constract.ProductConstract;
import retrofit2.Call;

public class ProductPresenter implements ProductConstract.Presenter {
    private ProductConstract.View view;
    ApiService apiService = RetrofitClient.getApiService();

    @Override
    public void setView(ProductConstract.View view) {
        this.view = view;
    }

    @Override
    public void getProduct(int productId) {
        // Get product by id
        Call<Product> call = apiService.getProduct(productId);
        call.enqueue(new retrofit2.Callback<Product>() {
            @Override
            public void onResponse(Call<Product> call, retrofit2.Response<Product> response) {
                if (response.isSuccessful()) {
                    view.showProduct(response.body());
                } else {
                    view.showError("Error: " + response.message());
                }
            }

            @Override
            public void onFailure(Call<Product> call, Throwable t) {
                view.showError("Error: " + t.getMessage());
            }
        });
    }
}
