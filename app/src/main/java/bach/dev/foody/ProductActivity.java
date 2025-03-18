package bach.dev.foody;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.squareup.picasso.Picasso;

import bach.dev.foody.data.dto.ProductDto;
import bach.dev.foody.data.model.ProductModel;
import bach.dev.foody.ui.constract.ProductConstract;
import bach.dev.foody.ui.presenter.ProductPresenter;
import bach.dev.foody.util.Constants;

public class ProductActivity extends AppCompatActivity implements ProductConstract.View {
    private ImageView ivThumbnail;
    private TextView tvName;
    private TextView tvPrice;
    private ImageView ivFavourite;
    private TextView tvAddToCart;

    private ProductDto productDto;

    ProductConstract.Presenter mPresenter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_product);

        initGUI();
        initData();
    }

    private void initData() {
        mPresenter = new ProductPresenter(this);
        mPresenter.setView(this);
        int productId = getIntent().getIntExtra(Constants.PRODUCT_ID, 0);
        mPresenter.getProduct(productId);
    }

    private void initGUI() {
        ivThumbnail = findViewById(R.id.iv_product_thumbnail);
        tvName = findViewById(R.id.tv_product_name);
        tvPrice = findViewById(R.id.tv_product_price);
        ivFavourite = findViewById(R.id.iv_favourite);
        ivFavourite.setOnClickListener(listener);

        tvAddToCart = findViewById(R.id.tv_add_to_cart);
        tvAddToCart.setOnClickListener(listener);
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showError(String message) {

    }

    @Override
    public void showProduct(ProductDto product) {
        mPresenter.checkFavourite(product.getId());
        productDto = product;
        tvName.setText(product.getName());
        tvPrice.setText(String.valueOf(product.getPrice()));
        Picasso.get().load(product.getThumbnail()).into(ivThumbnail);
    }

    @Override
    public void setFavourite(boolean isFavourite) {
        if(isFavourite) {
            ivFavourite.setImageResource(R.drawable.ic_favourite);
        } else {
            ivFavourite.setImageResource(R.drawable.ic_not_favourite);
        }
    }

    private View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if(view.getId() == R.id.iv_favourite){
                actionFavourite();
            }
            if(view.getId() == R.id.tv_add_to_cart){
                addToCart();
            }
        }

        private void actionFavourite() {
            ProductModel product = new ProductModel(
                    productDto.getName(),
                    productDto.getDescription(),
                    productDto.getThumbnail(),
                    productDto.getPrice(),
                    productDto.getQuantity(),
                    productDto.getCategoryId()
            );
            mPresenter.setFavourite(product);
        }

        private void addToCart() {
            // Add product to cart
            //Check authentication
        }
    };
}