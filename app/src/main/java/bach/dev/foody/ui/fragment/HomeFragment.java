package bach.dev.foody.ui.fragment;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import bach.dev.foody.R;
import bach.dev.foody.data.entities.Category;
import bach.dev.foody.data.entities.Product;
import bach.dev.foody.ui.adapter.CategoryAdapter;
import bach.dev.foody.ui.adapter.ProductAdapter;
import bach.dev.foody.ui.constract.HomeConstract;
import bach.dev.foody.ui.presenter.HomePresenter;

public class HomeFragment extends Fragment implements HomeConstract.View {
    RecyclerView rvCategory;
    RecyclerView rvProduct;
    HomeConstract.Presenter mPresenter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);
        initGUI(rootView);
        initData();

        return rootView;
    }

    private void initGUI(View rootView) {
        rvCategory = rootView.findViewById(R.id.rv_category);
        rvProduct = rootView.findViewById(R.id.rv_product);
        rvCategory.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL, false));
        rvProduct.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false));
    }
    private void initData() {
        mPresenter = new HomePresenter();
        mPresenter.setView(this);
        mPresenter.getCategories();
        mPresenter.getProducts();
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
    public void showProducts(List<Product> productList) {
        ProductAdapter adapter = new ProductAdapter(productList);
        rvProduct.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void showCategories(List<Category> categories) {
        CategoryAdapter adapter = new CategoryAdapter(categories, mPresenter);
        rvCategory.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }
}
