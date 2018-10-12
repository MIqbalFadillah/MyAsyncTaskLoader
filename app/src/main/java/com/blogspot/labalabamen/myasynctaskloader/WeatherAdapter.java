package com.blogspot.labalabamen.myasynctaskloader;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by iqbal on 10/9/2018.
 */

public class WeatherAdapter extends BaseAdapter {

    private ArrayList< WeatherItems > mData =  new ArrayList<>();
    private LayoutInflater mInflater;
    private Context context;


    public WeatherAdapter(Context context){
        this.context = context;
        mInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public void setData(ArrayList<WeatherItems> items){
//        ??mData = items;
        mData.clear();
        mData.addAll(items);
        notifyDataSetChanged();
    }

    public void addItem(final WeatherItems item){
        mData.add(item);
        notifyDataSetChanged();
    }

    public void clearData(){
        mData.clear();
    }

    @Override
    public int getItemViewType(int position){
        return 0;
    }

    public int getViewTypeCount(){
        return 1;
    }

    @Override
    public int getCount() {
        if(mData == null) return 0;
        return mData. size();
    }

    @Override
    public WeatherItems getItem(int position) {
        return mData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        ViewHolder holder;
        if (convertView == null){
            holder = new ViewHolder();
            convertView = mInflater.inflate(R.layout.weather_items, null);
            holder.textViewNamaKota = (TextView)convertView.findViewById(R.id.textkota);
            holder.textViewDescription = (TextView)convertView.findViewById(R.id.textDesc);
            holder.textViewTemperature = (TextView)convertView.findViewById(R.id.textTemp);
            convertView.setTag(holder);

        } else {
            holder = (ViewHolder)convertView.getTag();

        }
        holder.textViewNamaKota.setText(mData.get(position).getNama());
        holder.textViewDescription.setText(mData.get(position).getDescription());
        holder.textViewTemperature.setText(mData.get(position).getTemperature());
        return convertView;
    }

    private static class ViewHolder {
        TextView textViewNamaKota;
        TextView textViewTemperature;
        TextView textViewDescription;
    }

}

