package bach.dev.foody.ui.presenter;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import bach.dev.foody.data.api.ApiService;
import bach.dev.foody.data.api.RetrofitClient;
import bach.dev.foody.data.dao.ProductDao;
import bach.dev.foody.data.entities.ProductDto;
import bach.dev.foody.data.model.ProductModel;
import bach.dev.foody.data.room.RoomHelper;
import bach.dev.foody.ui.constract.ProductConstract;
import retrofit2.Call;

public class ProductPresenter implements ProductConstract.Presenter {
    private ProductConstract.View view;
    ApiService apiService = RetrofitClient.getApiService();
    private Context context;

    public ProductPresenter(Context context){
        this.context = context;
    }

    @Override
    public void setView(ProductConstract.View view) {
        this.view = view;
    }

    @Override
    public void getProduct(int productId) {
        // Get product by id
        Call<ProductDto> call = apiService.getProduct(productId);
        call.enqueue(new retrofit2.Callback<ProductDto>() {
            @Override
            public void onResponse(Call<ProductDto> call, retrofit2.Response<ProductDto> response) {
                if (response.isSuccessful()) {
                    view.showProduct(response.body());
                } else {
                    view.showError("Error: " + response.message());
                }
            }

            @Override
            public void onFailure(Call<ProductDto> call, Throwable t) {
                view.showError("Error: " + t.getMessage());
            }
        });
    }

    @Override
    public void setFavourite(ProductModel product) {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Handler handler = new Handler(Looper.getMainLooper());

        executor.execute(() -> {
            // Xử lý nền
            ProductDao productDao = RoomHelper.getDatabase(context).productDao();
            ProductModel find = productDao.findById(product.id);
            if(find != null){
                productDao.delete(find);
            } else {
                productDao.insert(product);
            }

            handler.post(() -> {
                // Cập nhật UI
                checkFavourite(product.id);
            });
        });

    }

    @Override
    public void checkFavourite(int productId) {

        ExecutorService executor = Executors.newSingleThreadExecutor();
        Handler handler = new Handler(Looper.getMainLooper());

        executor.execute(() -> {
            // Xử lý nền
            ProductDao productDao = RoomHelper.getDatabase(context).productDao();
            ProductModel product = productDao.findById(productId);

            handler.post(() -> {
                // Cập nhật UI
                if(product != null){
                    // Đã thích
                    view.setFavourite(true);
                } else {
                    // Chưa thích
                    view.setFavourite(false);
                }
            });
        });
    }
}
