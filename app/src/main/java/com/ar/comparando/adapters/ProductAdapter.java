package com.ar.comparando.adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.ar.comparando.R;
import com.ar.comparando.models.Product;
import com.ar.comparando.services.DownloadImageTask;
import com.squareup.picasso.Picasso;

import java.net.URL;
import java.util.ArrayList;

public class ProductAdapter  extends ArrayAdapter<Product> {
    private static final String TAG = "PersonListAdapter";

    private Context mContext;
    private int mResource;
    private int lastPosition = -1;

    /**
     * Holds variables in a View
     */
    private static class ViewHolder {
        TextView name;
        TextView barcode;
        ImageView picture;
        TextView price;
    }

    public ProductAdapter(@NonNull Context context, int resource, @NonNull ArrayList<Product> objects) {
        super(context, resource, objects);
        mContext = context;
        mResource = resource;
    }




    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        ProductAdapter.ViewHolder holder;
        Double priceRange= getItem(position).getPriceMin();

        if(convertView == null){
            LayoutInflater inflater = LayoutInflater.from(mContext);
            convertView = inflater.inflate(mResource, parent, false);
            holder= new ProductAdapter.ViewHolder();
            holder.name = convertView.findViewById(R.id.tvName);
            holder.price = convertView.findViewById(R.id.tvPrice);
            holder.barcode = convertView.findViewById(R.id.tvBarcode);
            holder.picture = convertView.findViewById(R.id.picture);

            convertView.setTag(holder);
        }else{
            holder = (ProductAdapter.ViewHolder) convertView.getTag();
        }

        holder.name.setText(getItem(position).getName());
        holder.barcode.setText(getItem(position).getBarcode());
        holder.price.setText("$ "+priceRange);
        Picasso.with(getContext()).load("https://imagenes.preciosclaros.gob.ar/productos/"+getItem(position).getBarcode()+"." +
                "jpg").into(holder.picture);
//        new DownloadImageTask( holder.picture)
//                .execute("http://java.sogeti.nl/JavaBlog/wp-content/uploads/2009/04/android_icon_256.png");

        return convertView;
    }
}

