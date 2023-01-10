package com.iqcollections;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class WListList extends ArrayAdapter<wishClass> {
    private Activity context;
    private List<wishClass> wListList;

    public WListList(Activity context, List<wishClass> wListList)
    {
        super(context, R.layout.wishlist_item, wListList);
        this.context = context;
        this.wListList = wListList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();

        View listViewItem = inflater.inflate(R.layout.wishlist_item, null, true);
        TextView txtWishlistItem = (TextView) listViewItem.findViewById(R.id.txtWishListItem);

        wishClass wish = wListList.get(position);
        String txt = "Wish Item: " + wish.getWishName() +
                " \nWish Description: " + wish.getWishDesc() +
                " \nExpected Price: R" + wish.getWishPrice();
        txtWishlistItem.setText(txt);

        return listViewItem;
    }
}
