package com.ar.comparando;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.ar.comparando.adapters.ProductAdapter;
import com.ar.comparando.models.Product;
import com.ar.comparando.responses.ResponsesProducts;
import com.ar.comparando.services.Api;
import com.ar.comparando.services.Utils;
import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductActivity extends AppCompatActivity {

    private ProgressDialog progressDialog;
    private Api mApi;

    TextView tvName,tvBrand,tvBarcode,tvPriceMin,tvPriceMax;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);
        /* BackButton */
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        /* Configure */
        tvName      = findViewById(R.id.tvName);
        tvBarcode   = findViewById(R.id.tvBarcode);
        tvPriceMin     = findViewById(R.id.tvPriceMin);
        tvPriceMax     = findViewById(R.id.tvPriceMax);
        tvBrand     = findViewById(R.id.tvBrand);

        /* Parameters */
        Intent intent = this.getIntent();
        String barcode = intent.getStringExtra("barcode");

        /* LoadData */
        loadData(barcode);

//        /* Progress Dialog */
//        progressDialog = new ProgressDialog(ProductActivity.this);
//        progressDialog.setIndeterminate(true);
//        progressDialog.setMessage((this.getResources().getString(R.string.loading)));
//        progressDialog.setCancelable(false);

    }

    public void loadData(String barcode){

        /* Load Image */
        String imageUri = "https://imagenes.preciosclaros.gob.ar/productos/"+barcode+"." + "jpg";
        Picasso.with(this).load(imageUri).into((ImageView) findViewById(R.id.picture));
        tvBarcode.setText(barcode);
        tvName.setText(null);
        tvBrand.setText(null);
        tvPriceMin.setText(null);
        tvPriceMax.setText(null);

        /* Api */
//        progressDialog.show();

        mApi = Utils.getApi();
        mApi.getProduct(barcode).enqueue(new Callback<Product>() {

            @Override
            public void onResponse(Call<Product> call, Response<Product> response) {
//                progressDialog.hide();
                if(!response.isSuccessful()){
                    Toast.makeText(ProductActivity.this, "Code: "+response.code(), Toast.LENGTH_SHORT).show();
                    return;
                }

                Product product = response.body();
                tvName.setText(product.getName());
                tvBrand.setText(product.getBrand());
                tvPriceMin.setText("$ "+product.getPriceMin().toString());
                tvPriceMax.setText("$ "+product.getPriceMax().toString());

                //Toast.makeText(ProductActivity.this, "Code: "+response.code(), Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onFailure(Call<Product> call, Throwable t) {
//                progressDialog.hide();
                Toast.makeText(ProductActivity.this, "Fail: "+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    /* BackButton */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
