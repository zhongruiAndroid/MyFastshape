package com.test.fastshape.test;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.test.fastshape.R;

import java.util.ArrayList;
import java.util.List;

/***
 *   created by zhongrui on 2019/1/3
 */
public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder> {
    private List<Class> actList=new ArrayList<>();
    private List<String> itemList=new ArrayList<>();
    private Activity context;

    public ListAdapter(Activity context) {
        this.context = context;
    }

    public void setActList(List<Class> actList) {
        this.actList = actList;
    }

    public void setItemList(List<String> itemList) {
        this.itemList = itemList;
    }

    @Override
    public ListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ViewHolder holder=new ViewHolder(LayoutInflater.from(context).inflate(R.layout.activity_test_shape_list_item,parent,false));
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ListAdapter.ViewHolder holder, final int position) {
        holder.textView.setText(itemList.get(position));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.startActivity(new Intent(context,actList.get(position)));
            }
        });
    }

    @Override
    public int getItemCount() {
        return actList==null?0:actList.size();
    }

    public static class  ViewHolder extends RecyclerView.ViewHolder{
        TextView textView;
        public ViewHolder(View itemView) {
            super(itemView);
            textView=itemView.findViewById(R.id.tv_title);
        }
    }
}
