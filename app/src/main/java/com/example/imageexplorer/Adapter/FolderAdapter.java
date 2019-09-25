package com.example.imageexplorer.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.imageexplorer.ImgVActivity;
import com.example.imageexplorer.R;

import java.util.ArrayList;

public class FolderAdapter extends BaseAdapter {
    Context context;
    ArrayList<String> arrayList;

    public FolderAdapter(Context context, ArrayList<String> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return arrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView==null){
            viewHolder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.custom_folder,null);
            viewHolder.imgFol = convertView.findViewById(R.id.imgFol);
            viewHolder.tvFol = convertView.findViewById(R.id.tvFol);

           convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        String tempB  = arrayList.get(position).substring(0, arrayList.get(position).lastIndexOf('/'));
        String  nameB = tempB.substring(tempB.lastIndexOf('/')).replace('/', ' ').trim();
        viewHolder.tvFol.setText(nameB);

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ImgVActivity.class);
                intent.putExtra("display",arrayList.get(position));
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent); }
        });


        return convertView;
    }

    public class ViewHolder {
        ImageView imgFol;
        TextView tvFol;
    }
}
