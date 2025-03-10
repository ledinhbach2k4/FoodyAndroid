package bach.dev.foody.ui.constract;

import java.util.List;

import bach.dev.foody.data.entities.Category;
import bach.dev.foody.data.entities.Product;

public interface HomeConstract {
    interface View {
        void showLoading();
        void hideLoading();
        void showError(String message);
        void showProducts(List<Product> productList);
        void showCategories(List<Category> categories);
    }

    interface Presenter {
        void setView(View view);
        void getProducts();
        void getCategories();
        void getProductsByCategory(int categoryId);
    }
}
