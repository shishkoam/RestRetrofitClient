package com.yo.shishkoam.restclienttest.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.yo.shishkoam.restclienttest.R;
import com.yo.shishkoam.restclienttest.api.models.history.Item;

import java.util.List;

/**
 * Created by User on 23.02.2017
 */

public class HistoryAdapter extends ArrayAdapter {

    private Context ctx;
    private LayoutInflater lInflater;
    private List<Item> items;

    public HistoryAdapter(Context context, List<Item> items) {
        super(context, R.layout.history_item);
        ctx = context;
        this.items = items;
        lInflater = (LayoutInflater) ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Item getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            view = lInflater.inflate(R.layout.history_item, parent, false);
        }

        Item item = getItem(position);
        if (item != null) {
            String date = item.getDatePost();
            date = date.replace('T', '\n');
            date = date.substring(0, date.indexOf('.'));
            ((TextView) view.findViewById(R.id.date)).setText(date);
            ((TextView) view.findViewById(R.id.description)).setText(item.getDescription());
            ((TextView) view.findViewById(R.id.amount)).setText(ctx.getString(R.string.uan, item.getAmount()));
        }
        return view;
    }
}
