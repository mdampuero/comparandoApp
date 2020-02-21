package com.ar.comparando.ui.home;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.SearchView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.ar.comparando.R;
import com.ar.comparando.responses.ResponsesProducts;
import com.ar.comparando.services.Api;
import com.ar.comparando.services.Utils;
import com.ar.comparando.adapters.ProductAdapter;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment {

    private Api mApi;
    private TextView tvTotal;
    private ListView mListView;
    private ProgressDialog progressDialog;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onPrepareOptionsMenu(menu);
        inflater.inflate(R.menu.menu_action_search, menu);
        final SearchView searchView = (SearchView) menu.findItem(R.id.action_search).getActionView();
        searchView.clearFocus();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                searchProduct(s);
                searchView.clearFocus();
                return true;
            }

            @Override
            public boolean onQueryTextChange(String s) {

                return true;
            }
        });
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        tvTotal = root.findViewById(R.id.tvTotal);
        mListView = root.findViewById(R.id.listView);

        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage((this.getResources().getString(R.string.loading)));
        progressDialog.setCancelable(false);

        return root;
    }

    public void searchProduct(String query){
        progressDialog.show();

        mApi = Utils.getApi();
        mApi.getProducts(query).enqueue(new Callback<ResponsesProducts>() {

            @Override
            public void onResponse(Call<ResponsesProducts> call, Response<ResponsesProducts> response) {
                progressDialog.hide();
                if(!response.isSuccessful()){
                    Toast.makeText(getActivity(), "Code: "+response.code(), Toast.LENGTH_SHORT).show();
                    return;
                }
                ResponsesProducts responsesProducts = response.body();
                ProductAdapter adapter = new ProductAdapter(getActivity(), R.layout.adapter_view_products_layout, responsesProducts.getResults());

                tvTotal.setText(getString(R.string.result_search,responsesProducts.getTotal()));
                mListView.setAdapter(adapter);
            }
            @Override
            public void onFailure(Call<ResponsesProducts> call, Throwable t) {
                Toast.makeText(getActivity(), "Fallo"+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

}