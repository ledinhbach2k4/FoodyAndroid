package bach.dev.foody.ui.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

import bach.dev.foody.R;
import bach.dev.foody.data.dto.CategoryDto;
import bach.dev.foody.ui.constract.HomeConstract;
import bach.dev.foody.util.CircleTransform;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder> {
    private List<CategoryDto> categoryList;
    private HomeConstract.Presenter mPresenter;

    public interface OnCategoryClickListener {
        void onCategoryClick(CategoryDto category);
    }

    OnCategoryClickListener clickListener;

    public CategoryAdapter(List<CategoryDto> categoryList, OnCategoryClickListener clickListener) {
        this.categoryList = categoryList;
        this.clickListener = clickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_category, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        CategoryDto category = categoryList.get(position);
        holder.tvName.setText(category.getName());
        Picasso.get()
                .load(category.getThumbnail())
                .transform(new CircleTransform())
                .into(holder.ivThumbnail);

        holder.itemView.setOnClickListener(
                v -> {
                    // Handle click event
                    CategoryDto cat = categoryList.get(position);
                    clickListener.onCategoryClick(cat);
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