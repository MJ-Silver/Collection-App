package com.iqcollections;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class grid_adapter extends BaseAdapter {

    Context context;
    String[] collectionsname;
    int[] image;
    LayoutInflater inflater;
    ImageView imageCollect;
    TextView txtGrid;

    public grid_adapter(Context context, String[] collectionsname, int[] image) {
        this.context = context;
        this.collectionsname = collectionsname;
        this.image = image;
    }

    @Override
    public int getCount() {
        return collectionsname.length;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if(inflater==null){
            inflater = (LayoutInflater)  context.getSystemService(context.LAYOUT_INFLATER_SERVICE);

        }

        if(view==null){
            view = inflater.inflate(R.layout.grid_item,null);

        }

        imageCollect = view.findViewById(R.id.grid_image);
        txtGrid = view.findViewById(R.id.item_name);
        imageCollect.setImageResource(image[i]);
        txtGrid.setText(collectionsname[i]);


        return view;
    }
}
