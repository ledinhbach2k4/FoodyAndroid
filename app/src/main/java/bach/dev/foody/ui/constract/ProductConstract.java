package bach.dev.foody.ui.constract;

import bach.dev.foody.data.dto.OrderDto;
import bach.dev.foody.data.dto.OrderItemDto;
import bach.dev.foody.data.dto.ProductDto;
import bach.dev.foody.data.dto.UserDto;
import bach.dev.foody.data.model.ProductModel;

public interface ProductConstract {
    interface View {
        void showLoading();
        void hideLoading();
        void showError(String message);
        void showProduct(ProductDto product);
        void setFavourite(boolean isFavourite);
    }

    interface Presenter {
        void setView(View view);
        void getProduct(int productId);
        void setFavourite(ProductModel product);
        void checkFavourite(int productId);
        void addToCart(OrderItemDto orderItemDto);
        void getOrderByStatus(int userId, String status, OrderItemDto orderItemDto);
    }
}
