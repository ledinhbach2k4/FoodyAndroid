package bach.dev.foody.ui.adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.squareup.picasso.Picasso;

import java.util.List;

import bach.dev.foody.ProductActivity;
import bach.dev.foody.R;
import bach.dev.foody.data.dto.ProductDto;
import bach.dev.foody.util.Constants;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder> {
    private List<ProductDto> productList;

    public ProductAdapter(List<ProductDto> productList) {
        this.productList = productList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_product, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ProductDto product = productList.get(position);
        holder.tvName.setText(product.getName());
        holder.tvPrice.setText(String.valueOf(product.getPrice()));
        Picasso.get().load(product.getThumbnail()).into(holder.ivThumbnail);

        holder.ivThumbnail.setOnClickListener(
                v -> {
                    // Handle click event
                    Intent intent = new Intent(v.getContext(), ProductActivity.class);
                    intent.putExtra(Constants.PRODUCT_ID, product.getId());
                    v.getContext().startActivity(intent);
                }
        );
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView ivThumbnail;
        public TextView tvName;
        public TextView tvPrice;

        public ViewHolder(View itemView) {
            super(itemView);
            // Dòng đã sửa: Cập nhật ID từ iv_product_thumbnail thành iv_product_item_thumbnail
            ivThumbnail = itemView.findViewById(R.id.iv_product_item_thumbnail); // Đã sửa
            // Dòng đã sửa: Cập nhật ID từ tv_product_name thành tv_product_item_name
            tvName = itemView.findViewById(R.id.tv_product_item_name); // Đã sửa
            // Dòng đã sửa: Cập nhật ID từ tv_product_price thành tv_product_item_price
            tvPrice = itemView.findViewById(R.id.tv_product_item_price); // Đã sửa
        }
    }
}