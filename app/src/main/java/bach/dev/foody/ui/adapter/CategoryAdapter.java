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
import bach.dev.foody.data.entities.Category;
import bach.dev.foody.data.entities.Product;
import bach.dev.foody.ui.constract.HomeConstract;
import bach.dev.foody.util.CircleTransform;
import bach.dev.foody.util.Constants;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder> {
    private List<Category> categoryList;
    private HomeConstract.Presenter mPresenter;

    public CategoryAdapter(List<Category> categoryList, HomeConstract.Presenter presenter) {
        this.categoryList = categoryList;
        this.mPresenter = presenter;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_category, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Category category = categoryList.get(position);
        holder.tvName.setText(category.getName());
        Picasso.get()
                .load(category.getThumbnail())
                .transform(new CircleTransform())
                .into(holder.ivThumbnail);

        holder.itemView.setOnClickListener(
                v -> {
                    // Handle click event
                    Category cat = categoryList.get(position);
                    mPresenter.getProductsByCategory(cat.getId());
                }
        );
    }

    @Override
    public int getItemCount() {
        return categoryList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView ivThumbnail;
        private TextView tvName;

        public ViewHolder(View itemView) {
            super(itemView);
            ivThumbnail = itemView.findViewById(R.id.iv_category_thumbnail); // Đã sửa
            tvName = itemView.findViewById(R.id.tv_category_name); // Đã sửa
        }
    }
}