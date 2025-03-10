package bach.dev.foody;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.squareup.picasso.Picasso;

import bach.dev.foody.data.entities.Product;
import bach.dev.foody.ui.constract.ProductConstract;
import bach.dev.foody.ui.presenter.ProductPresenter;
import bach.dev.foody.util.Constants;

public class ProductActivity extends AppCompatActivity implements ProductConstract.View {
    private ImageView ivThumbnail;
    private TextView tvName;
    private TextView tvPrice;

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
        mPresenter = new ProductPresenter();
        mPresenter.setView(this);
        int productId = getIntent().getIntExtra(Constants.PRODUCT_ID, 0);
        mPresenter.getProduct(productId);
    }

    private void initGUI() {
        ivThumbnail = findViewById(R.id.iv_product_thumbnail);
        tvName = findViewById(R.id.tv_product_name);
        tvPrice = findViewById(R.id.tv_product_price);
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
    public void showProduct(Product product) {
        tvName.setText(product.getName());
        tvPrice.setText(String.valueOf(product.getPrice()));
        Picasso.get().load(product.getThumbnail()).into(ivThumbnail);
    }
}