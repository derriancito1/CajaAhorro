package com.ahorro.specalpha.caja_ahorro.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.ahorro.specalpha.caja_ahorro.Models.MisPrestamos;
import com.ahorro.specalpha.caja_ahorro.R;

import java.util.List;

public class MyAdapter extends BaseAdapter {

    private Context context;
    private int layout;
    private List<MisPrestamos> list;

    public MyAdapter(Context context, int layout, List<MisPrestamos> list){
        this.context = context;
        this.layout = layout;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int id) {
        return id;
    }

    @Override
    public View getView(int i, View converView, ViewGroup viewGroup) {

        ViewHolder holder;

        if (converView == null){
            //LayoutInflater layoutInflater = ;
            converView = LayoutInflater.from(this.context).inflate(R.layout.list_item, null);
            holder = new ViewHolder();
            holder.nameTextView = (TextView) converView.findViewById(R.id.textView);
            holder.fechaPrestamo = (TextView) converView.findViewById(R.id.fecha_prestamo);
            holder.intereses = (TextView) converView.findViewById(R.id.intereses);
            converView.setTag(holder);
        }else{
            holder = (ViewHolder) converView.getTag();
        }

        final MisPrestamos currentName = (MisPrestamos) getItem(i);
        holder.nameTextView.setText("Prestamo $"+currentName.getPrestamo());
        holder.fechaPrestamo.setText(currentName.getFechaPrestamo());
        holder.intereses.setText("Intereses "+currentName.getInteresDeuda());

        return converView;

    }

    static class ViewHolder{
        private  TextView nameTextView;
        private  TextView fechaPrestamo;
        private  TextView intereses;
    }
}