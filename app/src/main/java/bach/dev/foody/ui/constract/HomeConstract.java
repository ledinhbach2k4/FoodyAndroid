package bach.dev.foody.ui.constract;

import java.util.List;

import bach.dev.foody.data.entities.CategoryDto;
import bach.dev.foody.data.entities.ProductDto;

public interface HomeConstract {
    interface View {
        void showLoading();
        void hideLoading();
        void showError(String message);
        void showProducts(List<ProductDto> productList);
        void showCategories(List<CategoryDto> categories);
    }

    interface Presenter {
        void setView(View view);
        void getProducts();
        void getCategories();
        void getProductsByCategory(int categoryId);
    }
}
