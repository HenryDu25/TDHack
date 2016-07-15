package com.google.android.gms.samples.vision.barcodereader;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by tariqaziz on 2016-07-15.
 */
public class ListAdapter extends ArrayAdapter<Product> {

    public ListAdapter(Context context, int resource) {
        super(context, resource);
    }

    public ListAdapter(Context context, int resource, List<Product> items) {
        super(context, resource, items);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v = convertView;

        if (v == null) {
            LayoutInflater vi;
            vi = LayoutInflater.from(getContext());
            v = vi.inflate(R.layout.product_list_item, null);
        }

        Product p = getItem(position);

        if (p != null) {
            TextView storeNameText = (TextView) v.findViewById(R.id.store_name_text);
            TextView itemPriceText = (TextView) v.findViewById(R.id.item_price_text);

            storeNameText.setText(p.storeName);

            itemPriceText.setText(p.price);

        }

        return v;
    }
}
