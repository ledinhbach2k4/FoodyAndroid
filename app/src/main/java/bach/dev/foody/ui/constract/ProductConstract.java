package bach.dev.foody.ui.constract;

import bach.dev.foody.data.entities.Product;

public interface ProductConstract {
    interface View {
        void showLoading();
        void hideLoading();
        void showError(String message);
        void showProduct(Product product);
    }

    interface Presenter {
        void setView(View view);
        void getProduct(int productId);
    }
}
